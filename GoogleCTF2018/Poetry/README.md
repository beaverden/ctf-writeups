# Poetry 

[Inspiration writeup](https://github.com/BOAKGP/CTF-Writeups/tree/master/Google%20CTF%202018%20Quals%20Beginners%20Quest/Poetry)

We have a dynamically linked binary, that on the first run adds `LD_BIND_NOW = 1` to the env and runs `execvp` with `/proc/self/exe`.

But there's one thing, it doesn't get `/proc/self/exe`, it resolves the symlink with `readlink("/proc/self/exe")`, which is strange.

What happens if we delete or move the file while it's still running? Where will the link point? 
If we have an executable `a.bin`, after deleting, the symlink points to `a.bin (deleted)`, a name we can control and use a executable that we control.

So we need to execute `/home/poetry/poetry` and the move or delete it. How can we do it? With `ln` that allows us to keep `SUID` permissions along the same device.

1. `ln /home/poetry/poetry /home/user/poetry`
2. `ln -s /bin/cat '/home/user/poetry (deleted)'`
3. `( ./poetry /home/poetry/flag & ); rm /home/user/poetry`

This should execute the current executable with the `/home/poetry/flag` parameter, then, while it's running, the original binary is deleted and the link `/proc/self/exe` now points to `/home/user/poetry (deleted)` which again is a link to `/bin/cat`. So the program will `execvp("/bin/cat", "/home/poetry/flag")`.

Obviously, since this is a race condition, we need to run the script multiple times to make this work.
After a few tries, we get
```
ln -s /bin/cat '/home/user/poetry (deleted)'
ln /home/poetry/poetry /home/user/poetry
(/home/user/poetry /home/poetry/flag &); rm /home/user/poetry
CTF{CV3-2009-1894}
```

Also, visit some of the posts from when the exploit was uncovered: [here](https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2009-1894), [here](https://www.securityfocus.com/archive/1/archive/1/505052/100/0/threaded)