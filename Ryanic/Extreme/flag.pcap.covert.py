#import binascii
#open('flag.drop.bin','wb').write(binascii.unhexlify(open('flag.pcap.drop','r').read()))
data = open('flag.drop.bin', 'rb').read()
bins = ''
for x in data:
# bins += bin(ord(x))[2:].rjust(8,'0')
 print chr(ord(x)/2)
for i in range(0, len(bins), 6):
	print chr(int(bins[i:i+6],2))