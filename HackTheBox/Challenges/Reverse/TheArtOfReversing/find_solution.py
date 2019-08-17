orig = 'abcdefghklmno'
s = 'cbefaodnghlkm'

key = 'ihististmyets'
positions = []

for c in s:
	for i, x in enumerate(orig):
		if c == x: positions.append(i)

print(positions)
f = ''
for i in positions: f += key[i]
print(f)

