#http://www.pythonchallenge.com/pc/return/5808.html

from PIL import Image

im = Image.open("F:/cave.jpg")

pixels = im.load()
width, height = im.size
print width, height

full = []
for x in range(height):
	for y in range(width):
		if (x+y)%2 == 0:
			full.append(pixels[y, x])
	
im2 = Image.new('RGB', (width//2, height))
im2.putdata(full)
im2.show()


