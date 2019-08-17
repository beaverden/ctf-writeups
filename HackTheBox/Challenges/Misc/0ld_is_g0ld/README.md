# 0ld is g0ld

The following file is a PDF 1.6, meaning it has AES128 encryption.
Let's use `pdf2john` to get the pdf password hash and the remove the CSV fields so just the hash remains

`$pdf$4*4*128*-1060*1*16*5c8f37d2a45eb64e9dbbf71ca3e86861*32*9cba5cfb1c536f1384bba7458aae3f8100000000000000000000000000000000*32*702cc7ced92b595274b7918dcb6dc74bedef6ef851b4b4b5b8c88732ba4dac0c`

Then, let's run `hashcat` to crack this has using `rockyou` wordlist

`hashcat64.exe -m 10500 -a 0 "pdf.hashes" rockyou.lst`

```
-m 10500 is the parameter for hash type PDF 1.6
-a 0 is attack mode "straight", just take words from the wordlist
rockyou.lst is the wordlist
```

We get the password `jumanji69` and a pdf with some very little morse code that thanslates to `R1PSAMU3LM0RS3`. That is the key
