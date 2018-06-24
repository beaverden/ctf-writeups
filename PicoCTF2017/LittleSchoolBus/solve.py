from PIL import Image
################################################
###
### PIL attempt. Not working. The information is
### hidden in the headers, so it can't be read 
### with pixels
###
################################################


image = Image.open('littleschoolbus.bmp')
pixels = image.load()
width, height = image.size
msg = ''
byte = 0
cnt = 0
for hy in range(0, height):
    for wx in range(0, width):
        r,g,b = pixels[wx, hy]
        cols = [r, g, b]
        for col in cols:
            bit = col & 0x1
            if bit == 1:
                byte |= 1 << (7-cnt)
            cnt += 1
            if cnt == 8:
                ch = chr(byte)
                if ord(ch) > 32 and ord(ch) < 126:
                    msg += ch
                byte = 0
                cnt = 0

print msg