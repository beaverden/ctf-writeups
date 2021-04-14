#http://www.pythonchallenge.com/pc/def/oxygen.html

from PIL import Image

im = Image.open("oxygen.png")
n, m = im.size
pixels = im.load()
data = [ [pixels[x, y] for x in range(n)]  for y in range(m)]
for arr in data[43]:
	for tup in range(0, len(arr)-1, 7):
		open("w", "a").write(chr(arr[tup]))