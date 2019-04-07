import json

d = json.loads(open('incidents.json').read())

ips = set()
i = 0
ip = input()
fs = {}
for ticket in d['tickets']:
	#ips[ticket['src_ip']] = ips.get(ticket.get('src_ip'), 0) + 1
	if ticket['src_ip'] == ip: ips.add(ticket['dst_ip'])
	if ticket['file_hash'] in fs:
		fs[ticket['file_hash']].add(ticket['dst_ip'])
	else:
		fs[ticket['file_hash']] = set()



print(fs)
x =0 
for s in fs.values():
	x += len(s)
x /= len(fs)
print(x)
	            	
print(ips)