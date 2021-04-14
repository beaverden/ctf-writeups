#http://www.pythonchallenge.com/pc/def/linkedlist.php
#http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing=66831

import urllib
base = 'http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing='
nothing = '8022'
for i in range(404):
	content = urllib.urlopen(base+nothing).read()
	nothing = content.split()[-1]
	open('wr','a').write(content+" "+nothing+'\n')
