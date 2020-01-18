## Nivel 1

The first level has an encrypted text that is being decrypted correctly only if the program has the password as the first argument.

Fortunately for us, the password is being verified in plaintext in the code `sub_401300` and it is `cyb3r!nt'5_#ooo1`

After running the binary with the password, we get


```
Felicitari, trimite-ne datele de contact la adresa de email WR58XWE475Q@cyberint.ro si te vom contacta noi.
Daca doresti sa iti testezi in continuare abilitatile, continua cu fisierul Nivel-2.exe.
Parola arhivei este flag-ul gasit.
```

## Nivel 2

The second level is much more interesting. The file is a C# compiled binary packed and obfuscated by MSIL Confuser.
There is no way to obtain the original binary, but there is a way to debug it and try to find what's happening. 

I'm going to use `dnSpy` to debug the binary. Usually, packed and obfuscated assemblies use the .cctor() function to decrypt
the contents and sure enough, after a few function calls, a wild `main` function appears, with a pseudocode similar to this one

```
private static void main(args)
{
	string argXor = "BXQMRxx7GDwXHgAAF2QLCzMQNWUIaAgABx8yERMBZlI="
	string success = "AF0mKz41cQArKgNEMGRdMz4fHBwAAQRaC3QqcwAPMQQrKwNRGHQiNTg2GwA7XwBaM3QpJAMPGxE4NB9VGHUiFQ4yIT02KABFHmYuKT8PAwQBAT1INXBZOjghCxIDPxNXMwFZczsPfFk7PxNXNWAucjk1eAAGK2hENWQbJDg1JVo4OyZI"
	string fail = "BnQPIQRiCF4eYiIdGVh8JzdIDAAcYHRV"
	string failKey = "U1Ih"
	string cyberint = "Q3lCZXIhblQ="
	string argKey = "Q0BwVHVyZVRoZUZsQGc="
	if (nrArgs < 1)
	{
		return;
	}
	if (nrArgs > 1)
	{
		Console.WriteLine(Decrypt(B64Decode(text2), text3); // SEFIQSA6KSkuLi5ObyEhIQ== -> HAHA :))...No!!!
		return;
	}
	if (Decrypt(Convert.ToBase64String(Encoding.UTF8.GetBytes(args[0]])), argKey).Equals(argXor))
	{
		Console.WriteLine(Encoding.UTF8.GetString(Convert.FromBase64String(Decrypt(success, cyberint))));
		return;
	} else {
		Console.WriteLine(Encoding.UTF8.GetString(Convert.FromBase64String(Decrypt(fail, failKey))));
	}

}
```

The decrypt function is a simple xor with a circular key `data[i] ^= key[i] % len(key)`.

It's easy to deduce from the code, that we need to run it with a special argument and then we'll get the reward. And the argument is `L3t'sD0th3T1meWarpAga!n`

The final message is `Bravo! Trimite-ne datele de contact la adresa SUN8TL419@cyberint.ro si continua cu urmatorul nivel.`

## Nivel 3

This is a UPX packed file. Let's extract it with `upx -d` and examine it. To get to the main function of visual studio compiled executabiles, just follow the first jump at the entry point
and then go down till you see `push 40000` and the call, or maybe `push [esi]` or something that could signal the main is called. Then just follow the next call afther that. 

From the file strings it's obvious that it doesn't want to be run in a virtual environment nor to be analyzed (checks for vbox files, ida processes, debugger presence). 
Still, a few `xor` operations can be observed in the function `4017D0`

`printf("%c", decrypted[v12] ^ key[v12 % strlen(key)]);` and `printf("%c", byte_41B420[v15] ^ v16[v15 % v22]);`

After decrypting the first string we get `1H@v3aR3dR0tr1nGP#n` and after using the first string as the file argument, we get `Ne poti trimite datele de contact la adresa Q201APRTZCFB@cyberint.ro`