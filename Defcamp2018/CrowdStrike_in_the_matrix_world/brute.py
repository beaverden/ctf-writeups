import os, sys
import struct

ar = [
				518503,
				518511,
				518499,
				518514,
				518511,
				518519,
				518500,
				518515,
				518516,
				518514,
				518505,
				518507,
				518501,
				518511,
				518514,
				518503,
				518511,
				518504,
				518511,
				518509,
				518501,
				518450,
				518448,
				518449,
				518456
]


for i in range(950, 1020):
	for j in range(500, 550):
		t = [x ^ (i*j) for x in ar]
		try:
			print ''.join([unichr(x) for x in t]).encode('utf-8')
		except: pass
		#if u'CROWD' in s: print s

