import random

json = "{'regId': '%d', 'hubId': '%d', 'binType': '%s', 'widgetId': '%d'}\n"

for regId in range(1,26):
    fd = open("./resources/region_%s_widgets.json" % regId,"w")
    for hubId in range(1,101):
        for widgetId in range(1,10001):
            binId = random.randrange(1,8001)
            binType = ('type%d' % binId) if (binId <= 6000) else ""
            fd.write(json % (regId, hubId, binType, widgetId))
    fd.close()
    print("Region(%d) - complete" % regId)
