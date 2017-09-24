# AcmeWidgets
Inventory ingestion system for Acme Widgets

This system tracks the Acme widgets accross it's various divisions and stores.

Acme is made up of the following:
- 25 Regions
- Each Region has 100 Hubs (2500 total). Each hub is unique to one region.
- Each Hub has access to 8000 unique bin types
- Each Hub uses 65-80% of the available bin types
- Each hub has between 125,000-150,000 unique widgets in it's inventory
- Of those widgets, 50% are contain in some type of bin used by it's hub.
- The other 50% of the widgets are not stored in bins at all, they just lay on the floor
- Each bin type used by the hub usually contains around 200 widgets
- Some widgets are moved from one bin type to another for various reasons, so we should 

The Acme Inventory ingestion system uses a combination of Kafka Producers/Consumers to stream data into Neo4j.

Launch Docker:
docker run --publish=7474:7474 --publish=7687:7687 --volume=$HOME/neo4j/data:/data \
--volume=$HOME/neo4j/import:/var/lib/neo4j/import --volume=$HOME/neo4j/logs:/logs neo4j:3.2

Create the needed indexes:
CREATE CONSTRAINT ON (r:Region) ASSERT r.regId IS UNIQUE\
CREATE CONSTRAINT ON (h:Hub) ASSERT h.hubId IS UNIQUE
CREATE CONSTRAINT ON (h:Hub) ASSERT h.regHub IS UNIQUE
CREATE CONSTRAINT ON (b:Bin) ASSERT b.binType IS UNIQUE
CREATE CONSTRAINT ON (b:Bin) ASSERT b.regHubBin IS UNIQUE
CREATE CONSTRAINT ON (w:Widget) ASSERT w.id IS UNIQUE
CREATE CONSTRAINT ON (w:Widget) ASSERT w.regHubWidget IS UNIQUE

Initialize the graph with resources/init_graph.csv:
- copying init_graph.csv to neo4j/import directory
- Run the following:
LOAD CSV WITH HEADERS FROM "file:///init_graph.csv" AS row
MERGE (r:Region {regId: row.regId, regName: row.regName})
MERGE (h:Hub {hubId: row.hubId, hubName: row.hubName}) on CREATE set h.regHub = row.regHub
MERGE (r)-[:REGION_HUB]->(h)