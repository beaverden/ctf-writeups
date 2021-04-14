#http://www.pythonchallenge.com/pc/def/channel.html
#http://www.pythonchallenge.com/pc/def/channel.zip

import zipfile

a = zipfile.ZipFile("F:/Downloads/channel.zip")

head = 'F:/channel/'
start = '90052'
while True:
	f = head+start+'.txt'
	text = open(f, "r").read()
	start = text.split()[-1]
	open("w.txt", "a").write(a.getinfo(start+'.txt').comment)