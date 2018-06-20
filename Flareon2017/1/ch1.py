import string
s = 'PyvragFvqrYbtvafNerRnfl@syner-ba.pbz'
alphabet1 = string.letters
alphabet2 = 'nopqrstuvwxyzabcdefghijklmNOPQRSTUVWXYZABCDEFGHIJKLM'
trans = string.maketrans(alphabet2, alphabet1)
open('res.txt', 'w').write(s.translate(trans))