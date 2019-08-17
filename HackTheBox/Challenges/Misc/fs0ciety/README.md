# fs0ciety

The first zip's password is `hackthebox` and it contains another zip for which the password is unknown.

But let's used the well known password cracker `john the ripper`.

1. Get the password hash from the zip using zip2john
2. Use the generated hash file to crack the password and obtain the password `justdoit`


The archive contains a file named `sshcreds_datacenter.txt` which has a very long string of "encrypted" data. But the `=` at the end blew it's cover and 
turns out it's simple `base64` encoding containing a binary encoded ascii string, which is the flag `if_y0u_c@n_$m3ll_wh@t_th3_r0ck_is_c00king`