import sys
start = int(sys.argv[1], 16)
align = int(sys.argv[2])
for line in open('code.txt'):
	bytes = line[:align]
	off = len(bytes.replace(' ', ''))/2
	print hex(start), line,
	start += off