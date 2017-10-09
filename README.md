# AcmeWidgets
Inventory ingestion system for Acme Widgets

This system tracks the Acme widgets across it's various regions and hubs.

Acme is made up of the following:

- 25 Regions
- Each Region has 100 Hubs (2500 total). Each hub is unique to one region.
- Each Hub has access to 5000 unique bin types
- Each Hub uses up to 50% of the available bin types
- Each hub has 10,000 unique widgets in it's inventory
- Of those widgets, roughly 50% are contained in some type of bin used by it's hub.
- The other 50% of the widgets are not stored in bins at all, they just lay on the floor
- Each bin type used by the hub usually contains 100s of widgets

The Acme Inventory ingestion system uses a combination of Kafka Producers/Consumers to stream data into Neo4j.

## The following steps detail how to load the Acme Inventory system:

### Launch Neo4j:

- Either using a local install of neo4j
- or via docker: docker run --publish=7474:7474 --publish=7687:7687 --volume=$HOME/neo4j/data:/data \
--volume=$HOME/neo4j/import:/var/lib/neo4j/import --volume=$HOME/neo4j/logs:/logs neo4j:3.2

### Once Neo4j has started, create the needed indexes and constraints:

- CREATE CONSTRAINT ON (r:Region) ASSERT r.regId IS UNIQUE\
- CREATE CONSTRAINT ON (h:Hub) ASSERT h.hubId IS UNIQUE
- CREATE CONSTRAINT ON (h:Hub) ASSERT h.regHub IS UNIQUE
- CREATE CONSTRAINT ON (b:Bin) ASSERT b.binType IS UNIQUE
- CREATE CONSTRAINT ON (b:Bin) ASSERT b.regHubBin IS UNIQUE
- CREATE CONSTRAINT ON (w:Widget) ASSERT w.id IS UNIQUE
- CREATE CONSTRAINT ON (w:Widget) ASSERT w.regHubWidget IS UNIQUE

### Next, Initialize the graph with Regions and Hubs:

- copying resources/init_graph.csv to neo4j/import directory
- Run the following: cypher:
LOAD CSV WITH HEADERS FROM "file:///init_graph.csv" AS row
MERGE (r:Region {regId: row.regId, regName: row.regName})
MERGE (h:Hub {hubId: row.hubId, hubName: row.hubName}) on CREATE set h.regHub = row.regHub
MERGE (r)-[:REGION_HUB]->(h)

### Kafka:

- Start local instance of zookeeper/kafka using standard ports (or Dockerized Zookeeper/Kafka)
- Create the 'widget' topic

### Producing Data:

    The producer has a REST endpoint that can load data for one or more regions: 
      curl localhost:8000/producer/load?            // Load all regions
      curl localhost:8000/producer/load?regId=1,2,3 // Load regionds 1,2,3
      