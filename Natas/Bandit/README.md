# Bandit

### Level00
The password is simply inside a readme file: `boJ9jbbUNNfktd78OOpsqOltutMc3MY1`

### Level01
The password is inside a file named "-", but we can't use _cat_ on it, so we'll just redirect the input with `cat < -`, where `-` is the file name.
The flag is `CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9`

### Level02
The password is inside "spaces in this filename" so just use double quotes to get the name as the first parameter, `cat "spaces in this filename"`, `UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK`

### Level03
The password is inside a hidden file (name starting with a dot) `pIwrPrtPN36QITSp3EQaw936yaFoFgAB`

### Level04
The password is inside the file `-file07` `koReBOKuIDDepwhWk7jZC0RTdopnAYKh`

### Level05
The file should have 1033 bytes, use `find . -size 1033c`, `DXjZPULLxYr17uwoI01bNLQbtFemEgo7`.

### Level06
Use global find. `find / -size 33c -group bandit06 -user bandit07` will lead to only one fie `/var/lib/dpkg/info/bandit7.password` with the data `HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs`.

### Level07
`cat data.txt | grep millionth` -> `millionth       cvX2JJa4CFALtqS87jk27qwqGhBM9plV`

### Level08
`sort data.txt | uniq -c` will sort and count each line. The one that exists only once is `UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR`

### Level09
`xxd data.txt` until you find `truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk`

### Level10
`cat data.txt | base64 -d` will give `The password is IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR`

### Level11
We can solve this ROT13 decryption with `tr '[A-Za-z]' '[N-ZA-Mn-za-m]'`, so `cat data.txt | tr '[A-Za-z]' '[N-ZA-Mn-za-m]'` -> `The password is 5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu`

### Level12
`xxd -r data.txt > data.gz` will make it binary. `GZIP`.
`gunzip data.gz` will drop `data` file, that is compressed with `bzip2`.
`mv data data.bz2; bzip2 -d data.bz2` will give another `data` file. `GZIP`.
`mv data data.gz; gunzip data.gz` will drop `data` file, `POSIX tar archive`.
`mv data data.tar; tar xf data.tar` will drop `data5.bin`, `POSIX tar archive`.
`mv data5.bin data.tar; tar xf data.tar` will drop `data6.bin` `bzip2`.
`mv data6.bin data6.bz2; bzip2 -d data6.bz2` will drop `data6` `POSIX tar archive`.

After a few more tries we get a file: `The password is 8ZjyCRiBWFYkneahHwxCv3wb2a1ORpYL`.

### Level13 
We're given a private RSA key, so let's `ssh -i "sshkey.private" bandit14@localhost` and then `cat /etc/bandit_pass/bandit14` -> `4wcYUJFw0k0XLShlDzztnTBHiqxU3b3e`

### Level14
Let's use netcat to send data to the open network socket at port `30000`, `echo "4wcYUJFw0k0XLShlDzztnTBHiqxU3b3e" | nc localhost 30000` 

```
Correct!
BfMYroe26WYalil77FoDi9qh59eK5xNr
```

### Level15
Connecting via SSL is required, so let's do that `echo "BfMYroe26WYalil77FoDi9qh59eK5xNr" | openssl s_client -connect localhost:30001 -ign_eof`, just like with `nc`.

```
Correct!
cluFn7wTiGryunymYOu4RcffSxQluehd
```

### Level16
Use `nmap -p 31000-32000 localhost` to find out open ports, then send the previous level code with the password changed, we get a private key.
Copy the private key to `/tmp/tempdir/ssh.key`, then `chmod 700 ssh.key` and then `ssh -i "ssh.key" bandit17@localhost`, then `cat /etc/bandit_pass/bandit17` to get the password `xLYVMN9WE5zQ5vHacb0sZEVqbrp7nBTn`

### Level17
Run `diff passwords.old passwords.new` to get `kfBf3eYk5BPBRzwjqutbbfE887SVc5Yd`

### Level18
We have to avoid `bashrc` file, so we should run `/bin/sh` without any config `ssh bandit18@bandit.labs.overthewire.org -p 2220 /bin/sh`. Then `cat readme` to get `IueksS7Ubh8G3DCwVzrTd8rAVOwq3M5x`

### Level19
We don't have access to `/etc/bandit_pass/bandit20`, but the `SUID` binary allows us to execute commands as `bandit20`, so lets `./bandit20-do cat /etc/bandit_pass/bandit20` which gives us `GbKksEFF4yrVs6il55v6gwY5aVje5f0j`

### Level20
We need to set up our own server, here is the code:
It will send the password to whoever connects and the print what it recieved.

```
import sys
import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
addr = ('localhost', '31337')
sock.bind(addr)

sock.listen(1)

while True:
    conn, client = sock.accept()
    conn.sendall('GbKksEFF4yrVs6il55v6gwY5aVje5f0j')
	print conn.recv(1024)
    conn.close()
```

Then let's run `/home/bandit20/suconnect 31337` to get 

```
Read: GbKksEFF4yrVs6il55v6gwY5aVje5f0j
Password matches, sending next password
gE269g2h3mw3pwgrj0Ha9Uoqen1c9DGr
```

### Level21
Let's cat `cat /etc/cron.d/cronjob_bandit22`.
We get `@reboot bandit22 /usr/bin/cronjob_bandit22.sh &> /dev/null`, let's see what the shell script contains `cat /usr/bin/cronjob_bandit22.sh`
```
#!/bin/bash
chmod 644 /tmp/t7O6lds9S0RqQh9aMcz6ShpAoZKF7fgv
cat /etc/bandit_pass/bandit22 > /tmp/t7O6lds9S0RqQh9aMcz6ShpAoZKF7fgv
```

So it prints the contents of the password file to some temp file. Let's `cat` the file -> `Yk7owGAcWjwMVRwrTesJEwB7WVOiILLI`

### Level22
The shell script does `mytarget=$(echo I am user $myname | md5sum | cut -d ' ' -f 1)`, which means it takes the md5 of the string "I am user bandit23" and then `cat /etc/bandit_pass/bandit23 > /tmp/<resulting_md5>`, so we firstly need to get the md5 of the string. `echo "I am user bandit23" | md5sum`, then let's look at the file with this name `cat /tmp/8ca319486bfbbc3663ea0fbe81326349` -> `jc1udXuA1tiHqjIsL8yaapX5XIAI6i0n`

### Level23
The script runs all the scripts from `/var/spool/bandit24` and the deletes them. Let's make script `cat "/etc/bandit_pass/bandit24" > "/tmp/<tempdir>/result.txt"`, git it executable permissions and the copy it to `/var/spool/bandit24/script.sh`, let it run and get `UoMYTrfrBFHyQXmg6gzctqAwOmw1IohZ`

### Level24
Let's make a simple script to brute-force the PIN

```
from pwn import *
conn = remote('localhost', 30002)
for i in range(0, 9999):
    s = '%04d' % (i)
    conn.sendline('UoMYTrfrBFHyQXmg6gzctqAwOmw1IohZ ' + s)
    print i, conn.recvline(),
```

We get 
```
5441 5441 Correct!
5442 5442 The password of user bandit25 is uNG9O58gUE7snukf3bvZ0rxhtnjzSGzG
```

### Level25
How do we find which terminal is being used? Let's `cat /etc/shells`. Probably it's using `/usr/bin/showtext`, then contains 
```
#!/bin/sh

export TERM=linux

more ~/text.txt
exit 0
```

So it's using more, it means to freeze it from disconnecting, we have to make it output more than one screen, so let's make the window smaller.
After it triggers `more`, type `v` to run `vi` and then `:e /etc/bandit_pass/bandit26`, which is the command to open a new file for editing (`e`).
The opened file contains `5czgV9L3Xx8JPOyRbXh6lQbmIOWvPT6Z`

### Level 26