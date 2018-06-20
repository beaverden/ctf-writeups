data=open('geo.txt', 'r').read()
out = open('geo.csv', 'w')
for line in data.split('\n'):
	print line
	out.write(', '.join(line.split())+'\n')
out.close