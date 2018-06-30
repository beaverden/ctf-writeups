# JS Safe 1

After putting a few useful calls `console.log` to console log, we find found that it's using three main components
1. An anonymous function that takes an array `x = [x0, x1]` and returns `x0 ^ x1`
2. An anonymous function that takes an array `x = [x0, x1]` and returns `x0 | x1`
3. A SHA256 digest function from `SubtleCrypto`.

We find out that it hashes the given passsword (that should have `CTF{}`) brachets for it to work, then performs operations simmilar to these 

I tried to make things much more clear with some logging

```
// This function will have x[0]|x[1], so we can identify it
if (env[fn].toString().indexOf('|') != -1) {
  console.log('OR', env[arg1][0], '|', env[arg1][1], '=>', env[lhs]);
}
else if (env[fn].toString().indexOf('^') != -1) {
  console.log('XOR', env[arg1][0], '^', env[arg1][1], '=>', env[lhs]);
  fin += env[arg1][1].toString() + ','
}
else if (env[fn].toString().indexOf('x[y]') != -1) {
  console.log('SHA[', env[arg2], '] =>', env[lhs]);
}
```

```
function solve(array, key) {
	var sum = 0;
	for (var i = 0; i < arr.length; i++) {
		sum |= (array[i] ^ key[i]);
	}
	if (sum == 0) return true;
	return false;
}
```

Probably the only way for the sum to be `0` si for the `array` to be equal to `key`.
So, let's find out the `key` then, which is the second parameter to the `xor` function.


Let's `console.log(env[arg1][1])`, because `env[arg1][0]` is the `sum` and `env[arg1][1]` is a part of the key.
We get some `32` numbers, let's format them `230,104,96,84,111,24,205,187,205,134,179,94,24,181,37,191,252,103,247,114,198,80,206,223,227,255,122,0,38,250,29,238`.

Since we've already encountered `SHA256` in this challenge, let's try to make a hash out of it `''.join(['%02x' % (x) for x in map(int, s.split(','))])`, returns `e66860546f18cdbbcd86b35e18b525bffc67f772c650cedfe3ff7a0026fa1dee`.

Notice the comment `// TODO: check if they can just use Google to get the password once they understand how this works.`
Does this mean we can just google the hash? **Yes** and it tells us that it's `Passw0rd!`. 

The resulting input is `CTF{Passw0rd!}` and the decrypted value is `router-ui.web.ctfcompetition.com`, which is the next level.
