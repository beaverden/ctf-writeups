import numpy as np
import matplotlib.pyplot as plt

f=open('geo.csv', 'r')
x=[]
y=[]
for line in f.read().split('\n'):
	line=line.strip().split(',')
	if len(line)!=2: continue
	a,b=list(map(float, line))
	x.append(a)
	y.append(b)

plt.scatter(x,y)
for i in range(len(x)-1):
	plt.plot([x[i], x[i+1]], [y[i],y[i+1]])
plt.show()