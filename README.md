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

docker run --publish=7474:7474 --publish=7687:7687 --volume=$HOME/neo4j/data:/data --volume=$HOME/neo4j/logs:/logs neo4j:3.2

Initialize the graph with resources/init_graph.cypher