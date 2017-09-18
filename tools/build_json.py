import random

json = "{'regionNumber': '%d', 'hubNumber': '%d', 'binType': '%s', 'widgetId': '%d'}\n"

for regId in range(1,2):
    fd = open("./resources/region_%s_widgets.json" % regId,"w")
    for hubId in range(1,101):
        for widgetId in range(1,100001):
            binId = random.randrange(1,8001)
            binType = ('type%d' % binId) if (binId <= 6000) else ""
            fd.write(json % (regId, hubId, binType, widgetId))
    fd.close()
