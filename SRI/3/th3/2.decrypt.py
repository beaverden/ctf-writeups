from base64 import b64decode

def decrypt(a, b):
	res = []
	for i, ch in enumerate(a):
		res.append(chr(ord(ch) ^ ord(b[i%len(b)])))
	return ''.join(res)
		
print('Argument', b64decode(decrypt(b64decode('BXQMRxx7GDwXHgAAF2QLCzMQNWUIaAgABx8yERMBZlI='), 
    'Q0BwVHVyZVRoZUZsQGc=')))
print('Deny', b64decode(decrypt(b64decode('BnQPIQRiCF4eYiIdGVh8JzdIDAAcYHRV='), 
    'U1Ih')))
print('Key', b64decode(decrypt(b64decode('AF0mKz41cQArKgNEMGRdMz4fHBwAAQRaC3QqcwAPMQQrKwNRGHQiNTg2GwA7XwBaM3QpJAMPGxE4NB9VGHUiFQ4yIT02KABFHmYuKT8PAwQBAT1INXBZOjghCxIDPxNXMwFZczsPfFk7PxNXNWAucjk1eAAGK2hENWQbJDg1JVo4OyZI='), 
    'Q3lCZXIhblQ=')))
