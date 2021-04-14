#http://www.pythonchallenge.com/pc/def/good.html
#huge:file

from PIL import Image, ImageDraw

im = Image.open("F:/good.jpg")
def drawPoints(file):
	global im
	arr = list(map(int, open(file, "r").read().split(",")))

	points = [(arr[i], arr[i+1]) for i in range(0, len(arr)-1, 2)]

	draw = ImageDraw.Draw(im)
	for i in range(0, len(points)-2):
		draw.line((points[i][0], points[i][1], points[i+1][0], points[i+1][1]), fill=128)
		
drawPoints("points.txt")
drawPoints("points2.txt")
im.show()
