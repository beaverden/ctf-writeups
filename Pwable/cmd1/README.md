# cmd1

The challenge idea is that the path is set to an unreachable directory, where no executables a present and flag cannot be obtained.

With all that, we still can execute commands with `cmd1_pwn` permissions, meaning we can control `env` variables with the command `export`

First, we need to restore the previous path: `/home/cmd1/cmd1 "export PATH=$PATH;"` the `$PATH` will be expanded *BEFORE* running, so the real command will look something like that `/home/cmd1/cmd1 "export PATH=/usr/bin;/bin"`

Now, when we have all the binaries, let's `cat` the flag. If we run `/home/cmd1/cmd1 "export PATH=$PATH;cat /home/cmd1/flag` the command will not be executed, because the filter denies anything containing `flag`.

Let's, instead, run `export f=flag`, which will set the environmental variable `f` to the value `flag`, now we can access it with `$f`. And since my env will be passed to the executable I'm running, the binary can use it too, as well
as other processes that it spawns. But the command won't work, because the variable `$f` will be expanded before running and `argv[1]` will still contain `flag`, which is not ok. We need to somehow delay the expanding of this variable 
to the point where it's executed in `system(argv[1]);`, that's why we'll use `\$f`, which escapes the dollar and allows the `system` command to expand the `$f` variable.

We now got the flag by running `/home/cmd1/cmd1 "export PATH=$PATH;cat /home/cmd1/\$f"`