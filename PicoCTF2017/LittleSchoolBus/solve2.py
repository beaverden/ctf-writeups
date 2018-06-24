from PIL import Image

data = open('littleschoolbus.bmp', 'rb').read()[0x36:]

msg = ''
byte = 0
cnt = 0
for data_byte in data:
    bit = ord(data_byte) & 0x1
    if bit == 1:
        byte |= 1 << 7-cnt
    cnt += 1
    if cnt == 8:
        ch = chr(byte)
        if ord(ch) > 32 and ord(ch) < 126:
            msg += ch
        byte = 0
        cnt = 0

print msg