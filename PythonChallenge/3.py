#http://www.pythonchallenge.com/pc/def/equality.html
k = open("wr", "r").read()
big = [chr(x) for x in range(ord('A'), ord('Z')+1)]
print big
z = open("k", "w")
for i in range(4, len(k)-4):
	if k[i] not in big and k[i-4] not in big and k[i-3] in big and k[i-2] in big and k[i-1] in big and k[i+1] in big and k[i+2] in big and k[i+3] in big and k[i+4] not in big:
		z.write(k[i])
