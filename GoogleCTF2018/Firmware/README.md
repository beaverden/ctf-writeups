# Firmware

Run `file` on the extracted data and we get 
`Linux rev 1.0 ext4 filesystem data, UUID=00ed61e1-1230-4818-bffa-305e19e53758 (extents) (64bit) (large files) (huge files)`

So the challenge gave us an ext4 partition.

I had to start my Ubuntu VM (maybe it's time to go full linux?), because Windows 10 Ubuntu subsystem doesn't have loop devices :( 

The mounting of the partition is done with the following commands

```
losetup /dev/loop0 /path/to/data

mount /dev/loop0 /mnt
```

Firstly, I started looking though the partition with `Nautilus`, which was a huge mistake, because the answer was right under my nose, in the root, 
hidden from the viewer: `.mediapc_backdoor_password`.

The compressed file contains the flag. `CTF{I_kn0W_tH15_Fs}`