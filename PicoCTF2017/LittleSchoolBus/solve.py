from PIL import Image

a = Image.open('littleschoolbus.bmp')
pixels = a.load()
print pixels[0,1]