import random,string
 
encflag = "BNZQ:449xg472190mwx6869b8pt10rwo92624" # encrypted flag
flag = "" # plaintext flag
lalphabet = "abcdefghijklmnopqrstuvwxyz" # lowercase alphabet
ualphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" # uppercase alphabet
digits = "0123456789"
 
i = 0
letter_count = 0
digit_count = 0
 
# We loop until we find the flag
while (len(flag) != len(encflag)):
	for c in encflag:
		print "[+] Breaking char %s.." % c
		state = True
		if c.isupper():
			while state:
                # We reset the seed each time we try to decipher a new char
				random.seed("random")
                # We set the number generator in the same state
				for x in xrange(letter_count):
					random.randrange(0,26)
				for x in xrange(digit_count):
					random.randrange(0,10)
				char = chr((ord(ualphabet[i])-65+random.randrange(0,26))%26 + 65)
				if char==c:
					flag += ualphabet[i]
					letter_count += 1 # used for the state of the number generator
					state = False	
					print "\t[+] Partial cleartext : %s.." % flag
					i = 0			
				else:
					i += 1
		elif c.islower():
			while state:
				random.seed("random")
				for x in xrange(letter_count):
					random.randrange(0,26)
				for x in xrange(digit_count):
					random.randrange(0,10)
				char = chr((ord(lalphabet[i])-97+random.randrange(0,26))%26 + 97)
				if char==c:
					flag += lalphabet[i]
					letter_count += 1
					state = False	
					print "\t[+] Partial cleartext : %s.." % flag
					i = 0			
				else:
					i += 1
		elif c.isdigit():
			while state:
				random.seed("random")
				for x in xrange(letter_count):
					random.randrange(0,26)
				for x in xrange(digit_count):
					random.randrange(0,10)
				char = chr((ord(digits[i])-48+random.randrange(0,10))%10 + 48)
				if char==c:
					flag += digits[i]
					digit_count += 1
					state = False	
					print "\t[+] Partial cleartext : %s.." % flag
					i = 0			
				else:
					i += 1
		else:
				flag += c
 
print "[+] Cleartext : %s" % flag