import sys, os
start = '/problems/db39b5c002d8445dc6d2bbf49a8ccc37'
def func(path):
	arr = []
	#print path
	try:
		arr = os.listdir(path)
	except:
		return
	if len(arr) == 0:
		return
	else:
		for dd in arr:
			if 'flag' in dd: print path
			func(path+'/'+dd)
func(start)
