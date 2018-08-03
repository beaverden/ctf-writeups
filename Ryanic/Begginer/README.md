# Ryan CTF: Beginner

### Hidden Web Flag

Nagivate to `http://ctf.ryanic.com:8080/secret.html`, press F12 to open developer tools and find `<!-- FLAG: hydrognosy -->` in the page.

### FTP File Transfer

Find the line with the ftp command `RETR Flag.txt` and then the response `FTP Data: 28 bytes (PASV) (RETR Flag.txt)` where the flag is `haptometer`

### Crack That Hash

Go to an md5 rainbow table to find that `6119442a08276dbb22e918c3d85c1c6e = incorrect`

### Encoded Credentials

The line `Authorization: Basic YWRtaW46dW5pY29ybmlj`, contains the Base64 encoded `user:password`, which is `admin:unicornic`