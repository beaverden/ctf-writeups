#http://www.pythonchallenge.com/pc/def/peak.html

import pickle
arr = pickle.Unpickler(open("wr", "r")).load()

f = open("k", "w")
for a in arr:
	for b in a:
		c = [b[0]]*b[1]
		f.write("".join(c))