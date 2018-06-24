# WierdRSA

The formula is simple, `dP, dQ` stand for `d mod p-1` and `d mod q-1` and are used in `RSA decryption` with `Chinese remainder Theorem`.

```
q_inv = q^-1 mod p
m1 = ciphertext^dp mod p 
m2 = ciphertext^dq mod q
h = (q_inv * (m1 - m2)) mod p
plaintext = m2 + h * q 
```