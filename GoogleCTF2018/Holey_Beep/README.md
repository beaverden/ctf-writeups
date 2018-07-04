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
	if ( ioctl(device, 0x4B2F, duration) >= 0 )
	{
	  close(device);
	}
	else
	{
	  fprintf(stderr, "ioctl(%d, KIOCSOUND, %d) failed.", device, duration);
	  close(device);
	}
...
}
  
```

1. First it registers a `signal` handler, for `SIGTERM (15)`, which will be `handle_sigterm`
2. Second, it goes through all the command line parameters and tries to communicate with the device `dev/console`. But wait, devices path start with `/dev`, not `dev`, which means we got ourselves a **local directory**, where we can handcraft the path `dev/console` with whatever we want.
3. If the programm gets a `SIGTERM`, it will call `handle_sigterm` and print "debug" information from the device, by reading from it.

```
fprintf(stderr, "ioctl(%d, KIOCSOUND, 0) failed.", device);
memset(&buf, 0, 0x400);
read(device, &buf, 0x400 - 1);
fprintf(stderr, "debug_data: \"%s\"", &buf);
```

That's it with the understanding.
Now, obviously the thing we need to read from and don't have permissions is the file `/secret_cake_recipe`, so it will be safe to assume this needs to be our device.

Let's create a directory where we can dump whatever we want. `/tmp/exploit`
Now:
`mkdir /tmp/exploit/dev`
`ln -s /secret_cake_recipe /tmp/exploit/dev/console`.

Running the `holey_beep` binary from `/tmp/exploit` will read from `/secret_cake_recipe` if everything goes as needed.


So all we need is to get to the `handle_sigterm` function, to read from the device.
But it's not so easy, since the `device` is only open when processing arguments and closed afterwards, so the only window to call the sigterm is:

1. After `device = open("dev/console", 0);`
2. But before `if ( ioctl(device, 0x4B2FuLL, (unsigned int)duration) >= 0 )`

There we have the `atoi` function, that we can overload with some work (as parsing a very big integer) to make a difference.
Since calling the `holey_beep` binary manually would be a mess, lets control our execution with a program that 
1. Branches execution with a `fork`
2. The child runs `/home/user/holey_beep`
3. The parent waits (`usleep`) a set amout of time and then sends `SIGTERM` to the program. We would also want to control the time so let's pass it as a command argument.

I wrote a [C program](https://github.com/beaverden/ctf-writeups/blob/master/GoogleCTF2018/Holey_Beep/program.c) that does that. Also it intentionally slows down the `atoi` function to win some more time.

Now just run an pray with different times until you get it right 

```
$ ./exec 550
ioctl(4, KIOCSOUND, -1) failed.Trying...
Finished
$ ./exec 500
ioctl(4, KIOCSOUND, -1) failed.Trying...
Finished
ioctl(4, KIOCSOUND, 0) failed.debug_data: "== Secret recipe for the CTF{the_cake_wasnt_a_lie} cake ==
...
```

Full text [here](https://github.com/beaverden/ctf-writeups/blob/master/GoogleCTF2018/Holey_Beep/result.txt)
Full script to get access to server and run binary [here](https://github.com/beaverden/ctf-writeups/blob/master/GoogleCTF2018/Holey_Beep/solution.py)