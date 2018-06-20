import sys, subprocess, os, fcntl
import socket

port = '2022'
arg = ['./input'] + ['A'] * 99
arg[ord('A')] = ''
arg[ord('B')] = '\x20\x0a\x0d'
arg[ord('C')] = port

r_out, w_out = os.pipe()
r_in, w_in = os.pipe()
r_err, w_err = os.pipe()

#set env before starting
my_env = os.environ.copy()
my_env["\xde\xad\xbe\xef"] = "\xca\xfe\xba\xbe"

f=open('\x0A', 'wb')
f.write(b'\x00\x00\x00\x00')
f.close()

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)


#start subprocess
proc = subprocess.Popen(arg, stdout=w_out, stdin=r_in, stderr=r_err, env=my_env, cwd='/tmp/bden')

#close other ends
os.close(w_out)
os.close(r_err)
os.close(r_in)


os.write(w_in, b'\x00\x0a\x00\xff')
os.close(w_in)

os.write(w_err, b'\x00\x0a\x02\xff')
os.close(w_err)
try:
	s.connect(('localhost', int(port)))
	s.send(b'\xde\xad\xbe\xef')
	s.close()
except:
	pass

data = os.read(r_out, 2048)
print data

