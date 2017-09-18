fd = open("./resources/init_graph.cypher","w")

fd.write("CREATE CONSTRAINT ON (r:Region) ASSERT r.regionNumber IS UNIQUE\n")
fd.write("CREATE CONSTRAINT ON (h:Hub) ASSERT h.hubNumber IS UNIQUE\n")
fd.write("CREATE CONSTRAINT ON (h:Hub) ASSERT h.regionHub IS UNIQUE\n")
fd.write("CREATE CONSTRAINT ON (b:Bin) ASSERT b.binType IS UNIQUE\n")
fd.write("CREATE CONSTRAINT ON (b:Bin) ASSERT b.regionHubBin IS UNIQUE\n")
fd.write("CREATE CONSTRAINT ON (w:Widget) ASSERT w.id IS UNIQUE\n")
fd.write("CREATE CONSTRAINT ON (w:Widget) ASSERT w.regionHubWidget IS UNIQUE\n\n")

cypher = (
    "MERGE (r:Region {regionNumber: '%s', name: 'Region%s'})-[:REGION_HUB]->"
    "(h:Hub {hubNumber: %s, hubName: 'Hub%s', regionHub: '%s-%s'})\n"
)
for reg in range(1,26):
    for hub in range(1,101):
        fd.write(cypher % (reg,reg,hub,hub,reg,hub))

fd.write("\n")
fd.close()