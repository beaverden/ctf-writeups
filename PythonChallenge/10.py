#http://www.pythonchallenge.com/pc/return/bull.html

a = [1, 11, 21, 1211, 111221]
for i in range(30):
	s=''
	t=str(a[-1])
	while len(t)>0:
		for i in range(0, 10):
			count = 1
			while t.startswith("".join([str(i)]*count)):
				count+=1
			if count>1:
				count-=1
				s+=str(count)+str(i)
				t=t[count:]
				continue
	a.append(s)
print len(a[30])
