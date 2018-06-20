import subprocess
import string
final = 'tu1|\h+&g\OP7@% :BH7M6m3g='
s = 't'
last = ord('T')
pos = 1
while pos < len(final):
    for j in [ord(x) for x in string.printable]:
        if j == 0x20: continue
        ch = j
        ch -= 32
        tmp = ch
        ch += last
        ch %= 96 
        ch += 32
        if ord(final[pos]) == ch:
            s = chr(j) + s
            last = tmp
            pos += 1
            break

print s