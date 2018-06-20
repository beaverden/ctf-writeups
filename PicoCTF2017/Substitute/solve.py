import sys

months = [
    'RTETDZTK', 
    'FGXTDZTK'
]
real = [
    'JANUARY', 
    'FEBRUARY', 
    'MARCH', 'APRIL', 
    'MAY', 'JUNE', 
    'JULY', 'AUGUST', 
    'SEPTEMBER', 
    'OCTOBER', 
    'NOVEMBER', 
    'DECEMBER'
]

cipher = {
'A' : '?',
'B' : 'Y',
'C' : 'W',
'D' : 'M',
'E' : 'C',
'F' : 'N',
'G' : 'O',
'H' : 'P',
'I' : 'H',
'J' : '?',
'K' : 'R',
'L' : 'S',
'M' : 'T',
'N' : 'P',
'O' : 'I',
'P' : '?',
'Q' : 'K',
'R' : 'D',
'S' : 'L',
'T' : 'E',
'U' : 'G',
'V' : '?',
'W' : 'U',
'X' : 'V',
'Y' : 'F',
'Z' : 'B',
}


def decrypt(str):
    res = ''
    for x in str:
        if x in cipher:
            if cipher[x] == '?':
                res+=x
            else:
                #res+='[{}]'.format(cipher[x])
                res += cipher[x]
        else: res+=x
    print res
decrypt(open('file.txt', 'rb').read())