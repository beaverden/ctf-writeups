import string
import random

def o(x):
    if x <= 47 or x > 57:
        return x - 55
    else:
        return x - 48


space = [ord(x) for x in string.digits + string.uppercase]
final = [o(x) for x in space]

while True:
    s = [random.choice(space) for x in range(15)]
    checksum = 0
    for i, x in enumerate(s):
        checksum += (o(x) + 1) * (i + 1)

    if checksum % 36 in final:
        result = ''.join([chr(x) for x in s])
        result += chr(space[final.index(checksum % 36)])
        print result