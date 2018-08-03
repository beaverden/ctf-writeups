# Ryan CTF: Intermediate

### Database password

Connect to the database with the given login and password.
Then we need to connect to mysql `mysql -u root`, it's a surprise it let us join as root, but it has the rights to all databases, so let's list them.
`SHOW DATABASES`.

```
information_schema
mysql  
performance_schema	
sys 
```

When we `use mysql` database and list all the available tables, `SHOW TABLES`, we find out the there is an interesting table `user`, that contains a lot of fields, but it's a place where the hashed passwords to users are stored.
Let's `select user, authentication_string from user;`

We find out the hashed password to flag
`flag | *25A3DA0F4740480EE31E435E03CE4B58A4F1263B`, which, after using a rainbow table, is `periwinkle`. Nice!

### Hacker

If you look at the image with an appropiate hex viewer you'll find that it's prepended with a base64 string `RkxBRzogIG9ub21hc3RpY29uCg==`, that is `FLAG:  onomasticon`

### What's the password?
One way to get the program is to Base64 encode it. 
`cat program | base64`, copy it to local, then run `base64 -d` on it and open the binary to find the password in the dissasembly. Then run the binary with the correct password to gain the flag. The flag is `transumption`.

