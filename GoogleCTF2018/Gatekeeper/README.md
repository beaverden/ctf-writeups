# Gatekeeper

When we open the `main` function with IDA, we get the following code

```
if ( !strcmp(argv[1], "0n3_W4rM") )
```

So the username (a.k.a first argument) has to be `0n3_W4rM`

Then

```
for ( i = 0; i < strlen(dest)/2; ++i ) {
	temp = dest[i];
	dest[i] = dest[strlen(dest) - i - 1];
	dest[strlen(dest) - i - 1] = temp;
}
```

Which obviously reverses the string given by `argv[2]`.

And finally

```
...
if ( !strcmp(dest, "zLl1ks_d4m_T0g_I") ) {
	snprintf(&s, 0x80uLL, "CTF{%s}\n", argv[2], argv);
}
...
```

Verifies if the reversed string is `zLl1ks_d4m_T0g_I`, which is `I_g0T_m4d_sk1lLz` and the flag is `CTF{I_g0T_m4d_sk1lLz}`.