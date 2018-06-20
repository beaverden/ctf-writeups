
from pwn import *

conn = remote('127.0.0.1', 9007)
done = False
while not done:
    for line in conn.recv().split('\n'):
        if re.match('N=[0-9]+\sC=[0-9]+', line):
            data=line.split(' ')
            n, c = map(lambda x: int(x.split('=')[-1]), data[:2])
            print n,c
            start = 0
            end = n-1
            tries = 0
            while tries < c:
                tries+=1
                half = (start + end)/2
                indexes = ' '.join([str(x) for x in range(start, half+1)])
                conn.sendline(indexes)
                weight = int(conn.recv())
                print 'Start: {}, End: {}, Weight: {}'.format(start, end, weight)
                if (weight % 2 != 0) or weight % (half-start+1) != 0:
                    end = half
                    continue
                else:
                    start = half+1
            conn.sendline(str(start))
            res = conn.recv()
	    if '(99)' in res: 
		done = True
		break
print 'done!'
print conn.recv()

