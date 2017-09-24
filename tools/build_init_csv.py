fd = open("./resources/init_graph.csv","w")

fd.write("regId,regName,hubId,hubName,regHub\n")
for reg in range(1,26):
    for hub in range(1,101):
        fd.write("%d,Region%d,%d,Hub%d,%d-%d\n" % (reg, reg, hub, hub, reg, hub))

fd.write("\n")
fd.close()