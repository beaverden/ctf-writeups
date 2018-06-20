data=bytearray(open('out.crypted', 'rb').read())
fin=[data[-1]]
data.reverse()
for x in data[1:]:
	fin = [x^fin[0]] + fin
open('res.bin','wb').write(''.join([chr(x) for x in fin]))		