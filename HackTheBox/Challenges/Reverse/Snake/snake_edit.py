#!/usr/bin/python2.7
import random 
def strify(arr):
    print(''.join([chr(x) for x in arr]))

chains = [0x74, 0x68, 0x69, 0x73, 0x20, 0x69, 0x73, 0x20, 0x61, 0x20, 0x74, 0x72, 0x6f, 0x6c, 0x6c]
chars = []
keys = [0x70, 0x61, 0x73, 0x73, 0x77, 0x6f, 0x72, 0x64, 0x21, 0x21]
# The formula doesn't matter
lock = 5
print(chr(keys[0]))
for key in keys:
    keys_encrypt = lock ^ key
    chars.append(keys_encrypt)
for chain in chains:
    chains_encrypt = chain + 0xA
    chars.append(chains_encrypt)
strify(chars)

login = 'anaconda'
pass_input = raw_input('Enter your password\n')

for passes in pass_input:
    for char in chars:
        if passes == str(chr(char)):
            print 'Good Job'
            break
        else:
            print 'Wrong password try harder'
            exit(0)
    break