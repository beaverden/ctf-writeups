# MicroCorruption: Algeirs

This introduces an implementation of "heap memory".
The zone starts at `0x2400` and has the `0x1000` size.
The algorithm is pretty straight forward:

1. Heap memory is divided in blocks. Free and Used
2. Used blocks have their size _not divisible_ by two (the first bit is set)
3. Free blocks, obviously, have even size
4. At first there is one free block of size `0x1000`.

Subsequent `malloc` calls will iterate through all the blocks and find one that fits the requested size and make it used

The sample code (not totally accurate):

```
struct block {
	block* prev;
	block* next;
	short size;
}

short malloc(short size) {
	unsigned int first = 2408;
	block* block = (block*)(first);
	
	//Multiplication by 2 makes any number even, to mark it as free
	//Init the first block of free 0x1000 memory
	if (first_block == true) {
		block->prev = (block*)first;
		block->next = (block*)first;
		block->size = (0x1000 - 6) * 2
		first_block = false;
	}

	block = first;
	//Search for a free block
	do {
		// Length divisible by 2 essentially means the block is free
		if ((block->size & 1) == 0) { 
			real_size = block->size / 2;
			if (len <= real_size) {
				if (len + 6 <= real_size) {
					block->size |= 1;
					return block + 6;
				}
				else {
					block->size = (len*2) | 1;
					free_block = block + 0x6 + len;
					free_block->prev = block;
					free_block->next = block->next;
					free_block->size = (r12 - len - 6) * 2;
					block->next = free_block ;
					return block + 6;
				}

			}		
		}
		if (block == block->next) break;
	}
	while (r11 != block);
	puts("Heap exausted; aborting.");
	exit;
}

void free(short addr) {
	void* block = addr - 6;
	block->size &= 0xFFFE;
	void* prev_block = block->prev;

	if (prev_block->size & 0x1 == 0) {
		prev_block->size = prev_block->size + 6 + block->size;
		prev_block->next = block->next;
		block->next->prev = prev_block;
		block = block->prev;
	}
	prov_block = block->next;
	if (block->next->size & 0x1 == 0) {
		block->size += 6;
		block->next = block->next->next;
		block->next->prev = block->next;
	}
}
```
Notice two facts.
1. _Blocks come one after the other_
2. _It uses data not only from the current block being fred, but also from prev and next blocks_

_Only if we could read more data that the block size, so we could overwrite the next block, so when it's fred, we could use that in our advantage_
Oh, wait. The program actually allocates `0x10` bytes and reads `0x30`. 
This way, we can write `0x16` bytes into the _first block_ (username) and then overwrite the _second block_, so when `free` is called on it, we'll have control over what it writes and where.

So, suppose `free` is called on a second block.
It immediately finds the corresponding header that contains `prev, next, size` (that we control)
Following the code, `prev_block = block->prev`

The code is not totally accurate, but notice the line `prev_block->size = prev_block->size + 6 + block->size`
**We want to overwrite the return address of the function** and we have control over `block` contents, since we're overwriting it with our input. 

1. We can set `block->prev` in way that `prev_block->size` points right to the return address. It's easy. The return address `4394 - 4`
2. Now, since we want to _overwrite_ it, we need an appropriate value. The current return value is `46A8` (from the `free` function) and we need to get to `4564 (unlock_door)`. Since we have to add stuff to it, the formula would be `block->size = 4564 - 6 - 46A8 = FEB6`
3. Because of the second check `block->next->size & 0x1`, if the value of `block->next->size` is even, it will enter the if branch and overwrite our return value again. So we need a valid `block->next`. Since we also control the first `0x10` chars and we know where they will be located, we can put `block->next` right at the beginning of our input, 6 bytes of header is all we need. Actually, we need only the _3rd word_ in there, the size. 

This way, the username input should be 
`[next block] AA AA AA AA AA AA AA AA AA AA [ overwritten block ]` 
Where next block is 
```
struct next_block {
    0x0 doesn't matter
    0x2 doesn't matter
    0x4 0x1 (odd size)
}
struct overwritten_block {
    0x0 4390 (function return addr position)
    0x2 260e (next_block addr)
    0x4 4564 - 6 - 46A8 = FEB6
}
```

## Solution
**Username**: `AA AA AA AA 01 01 AA AA AA AA AA AA AA AA AA AA 90 43 0e 24 B6 FE`
**Password**: doesn't matter, might be blank