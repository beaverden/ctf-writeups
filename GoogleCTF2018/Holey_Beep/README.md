# Holey Beep

So the challenge tells us about `/secret_cake_recipe` on the last server we've been (the Fridge TODO one) and the executable `holey_beep`.

Also it tells us it's susceptible to the `Holey Beep vulnerability`, which essentially conists of a race condition to call the `sigterm handler` before the current operation is finished, so we still have the needed parameters. 

More about the vulnerability: [description](https://sigint.sh/#/holeybeep), [code](https://gist.github.com/Arignir/0b9d45c56551af39969368396e27abe8)

In our case, the code inside `main` is as follows :

```
...
signal(SIGTERM, handle_sigterm)
for ( i = 1; i < argc; ++i )
{
	device = open("dev/console", 0);
	duration = atoi(argv[1])
	if ( ioctl(device, 0x4B2FuLL, (unsigned int)duration) >= 0 )
	{
	  close(device);
	}
	else
	{
	  fprintf(stderr, "ioctl(%d, KIOCSOUND, %d) failed.", (unsigned int)device, (unsigned int)v5);
	  close(device);
	}
...
}
  
```

1. First it registers a `signal` handler, for `SIGTERM (15)`, which will be `handle_sigterm`
2. Second, it goes through all the command line parameters and tries to communicate with the device `dev/console`. But wait, devices path start with `/dev`, not `dev`, 
which means we got ourselves a local directory, where we can handcraft the path `dev/console` with whatever we want.
3. If the programm gets a `SIGTERM`, it will print "debug" information from the device, by reading from it.

```
fprintf(stderr, "ioctl(%d, KIOCSOUND, 0) failed.", device);
memset(&buf, 0, 0x400);
read(device, &buf, 0x400 - 1);
fprintf(stderr, "debug_data: \"%s\"", &buf);
```

That's it with the understanding.
Now, obviously the thing we need to read from and don't have permissions is the file `/secret_cake_recipe`, so it will be safe to assume this needs to be our recipe.

Let's create a directory where we can dump whatever we want. `/tmp/exploit`
Now `mkdir /tmp/exploit/dev`, `ln -s /secret_cake_recipe /tmp/exploit/dev/console`.

Running the `holey_beep` binary from `/tmp/exploit` will read from `/secret_cake_recipe` if everything goes as needed.


So all we need is to get to the `handle_sigterm` function, to read from the device.
But it's not so easy, since the `device` is only open when processing arguments and closed afterwards, so the only window to call the sigterm is:

1. After `device = open("dev/console", 0);`
2. But before `if ( ioctl(device, 0x4B2FuLL, (unsigned int)duration) >= 0 )`