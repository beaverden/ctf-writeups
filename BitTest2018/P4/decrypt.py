from PIL import Image
import io
import sys
from copy import *

data=open('out.png.crypted', 'rb').read().split(',')
end=open('out.png', 'wb')
cnt=0
pos=0
arr=[]
sys.setrecursionlimit(9000)
for x in data:
	found=0
	arr.append([])
	for i in range(0, 255):
		k1 = (i % 9) + 2
		k2 = i
		s = ''
		while True:
			s += chr(ord('0') + k2%k1)
			k2 /= k1
			if k2 == 0: break	
		if s == x:
			arr[pos].append(i)
			if found == 0:
				found += 1
				end.write(chr(i))
	if len(arr[pos]) == 0:
		arr[pos].append(0)
	pos+=1

	#print cnt

fin = deepcopy(arr)
end.close()
def data_proc(pos):
	global arr
	if pos == 100:
		print 'End'
		try:
			img = Image.open(io.BytesIO(fin))
			img.show()
		except:
			pass
		return	
	if len(arr[pos]) > 1:
		for x in arr[pos]:
			fin[pos] = x
			data_proc(pos+1)
	else:
		fin[pos] = arr[pos][0]
		data_proc(pos+1)	
                                        
data_proc(0)				
