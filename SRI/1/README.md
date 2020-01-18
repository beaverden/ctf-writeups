# Challenge 1

We are met with a `PCAP` file containing multiple types of communication. This file is going to be present
thoughout the whole challenge.

First, we need to analyze the WiFi protocol communications. To be able to find the password, we need a 
WPA2 handshake. We have that in the 4 `EAPOL` packets. We can use `aircrack-ng` to extract and convert the packets
into hashes. And the crack them using `hashcat` or `john the ripper`. Reference: `crack_pcap_wifi.sh`

We now have the password `vanatoare` to the encrypted wifi communications, where we find 2 pictures. 
1. `deschide-ma.png` this image contains a QR code that contains a message encrypted with the VIGENERE (tried caesar first and then an automatic tool to solve the vigenere cipher and got the key `EVIGENER`
   The message is inside `3.cipher.txt` and contains a link to a password protected dropbox file. The password is `cOm0@rA` from the second image.
2. `rot.png` - this image contains a message, encrypted with `rot13` `4~>_oCp` - `cOm0@rA` (deduced from its name) that is the password to the

The password protected file is a `pem` SSL private key. With this key, we can decrypt the TLS communications inside the first pcap (near the end), either by adding a key or by using tshark `decrypt_ssl.bat`

Inside the decrypted HTTP stream we find an HTML with the last flag.
