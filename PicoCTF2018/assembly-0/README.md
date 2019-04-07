# assembly-0

This one is easy
In x86 assembly, the convention assumes that the first function parameter passed to the function is located at `DWORD PTR [ebp+0x8]`, and the second at `DWORD PTR [ebp+0xc]`

So if the parameters are `0xb6` and `0xc6`, then we have `eax = 0xb6` and `ebx = 0xc6`. the next instruction moves `ebx` into `eax`. Which, by default is the return value of the function.

The answer is `0xc6`, the initial value of `ebx`