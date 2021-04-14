#http://www.pythonchallenge.com/pc/return/evil.html

f = open("evil2.gfx", "rb").read()


for i in range(5):
	imx = f[i::5]
	open(str(i)+".jpg", "wb").write(imx)