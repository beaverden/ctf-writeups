import binascii
def decrypt(a, b):
	res = []
	for i, ch in enumerate(a):
		res.append(chr(ord(ch) ^ ord(b[i%len(b)])))
	return ''.join(res)

s = binascii.unhexlify('70 2D D6 95 F0 AA D4 B1 49 41 71 11 E4 D2 AD 8C D6 A1 43'.replace(' ', ''))
k = binascii.unhexlify('41 65 96 E3 C3 CB 86 82 2D 13'.replace(' ',''))

success_key = decrypt(s, k)
msg = binascii.unhexlify('7F 2D 60 06 5C 15 3B 13 10 20 59 19 1B 45 0B 67 34 42 1A 54 24 25 56 57 04 72 50 0B 3C 44 15 11 45 4E 2B 31 03 0F 55 3A 25 05 52 41 03 01 54 63 71 24 20 65 34 04 16 61 2E 52 31 22 13 41 08 3C 47 4A 20 5F'.replace(' ',''))
print(decrypt(msg, success_key))