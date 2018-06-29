# Floppy

The downloaded ICO contains an embedded PK object, starting from `0x2FD`

Inside the zip we have driver.txt with the following content

```
This is the driver for the Aluminum-Key Hardware password storage device.
     CTF{qeY80sU6Ktko8BJW}

In case of emergency, run www.com
```

There we go, we have the flag. Now let's figure out how we run the com object.

Running it inside DosBox, we get the string `The Foobanizer9000 is no longer on the OffHub DMZ.` as expected. Nothing interesting.