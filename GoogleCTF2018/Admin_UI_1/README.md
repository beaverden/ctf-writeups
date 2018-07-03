# Admin UI

It says the interface here is pretty buggy..., let's `nc mngmnt-iface.ctfcompetition.com 1337`.

We have the following options:

1. `Service access`, it shows us a nice joke `Please enter the backdoo^Wservice password:`, `CTRL+W` was supposed to delete the last word, which is `backdoor` :)
2. `Read EULA/patch notes`, allows us to read the patch notes, 
	```
	# Release 0.2
	 - Updated library X to version 0.Y
	 - Fixed path traversal bug
	 - Improved the UX
	```

	But I don't care, let's still try some path traversal. Type `../../../../../../etc/passwd`. *BOOM*. 
	Now, the hard part is to see what files can we access.
	It got me thinking for a while if there's a live file containing all the FS names, but then I just tried my luck with `flag` and `../flag` and it got me the answer. `CTF{I_luv_buggy_sOFtware}`