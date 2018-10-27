# UltimateMinesweeper

We are given a `MSIL` type executable (DotNet), meaning we can decompile it using ILSpy. 
Let's have a look at the `MainForm` code. 
We see a wierd function, that doesn't really do what it's supposed to.

```
private void AllocateMemory(MineField mf)
{
	for (uint num = 0u; num < MainForm.VALLOC_NODE_LIMIT; num += 1u)
	{
		for (uint num2 = 0u; num2 < MainForm.VALLOC_NODE_LIMIT; num2 += 1u)
		{
			bool flag = true;
			uint r = num + 1u;
			uint c = num2 + 1u;
			if (this.VALLOC_TYPES.Contains(this.DeriveVallocType(r, c)))
			{
				flag = false;
			}
			mf.GarbageCollect[(int)num2, (int)num] = flag;
		}
	}
}
``` 
There is no allocation, instead, it iterates through rows and columns and sets some values in a two-dimensional array as `true` or `false`. Ironocally, this array is called `GarbageCollect` (spoiler alert, it has nothing to do with garbage collection).

Let's investigate further! We see `DeriveVallocType` function called. What does it do?

```
private uint DeriveVallocType(uint r, uint c)
{
	return ~(r * MainForm.VALLOC_NODE_LIMIT + c);
}
```

So, it gets some sort of `hash` out of `row` and `column` and compares them with `this.VALLOC_TYPES`.

```
private uint[] VALLOC_TYPES = new uint[]
{
	MainForm.VALLOC_TYPE_HEADER_PAGE = 4294966400u,
	MainForm.VALLOC_TYPE_HEADER_POOL = 4294966657u,
	MainForm.VALLOC_TYPE_HEADER_RESERVED = 4294967026u
};
```

Obviously, if the hash is one of the following values, there will be no mine under `row = num+1` `column = num2 + 1`.
Let's replicate the following code with `VALLOC_NODE_LIMIT = 30` and test this idea!

Indeed. There are only `900-3` bombs, which matches up with our hashes number. 
After testing, we get the following `row column` combinations

```
8 29
21 8
29 25
```

After running and selecting these spots, we `win` and the flag is given to us `Ch3aters_Alw4ys_W1n@flare-on.com`


