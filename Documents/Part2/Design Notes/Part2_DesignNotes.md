# Design Notes

The project source code can be found at the location `src/main/java/group1/mips_simulator/*.java`.

This project is designed with the Model-View-Controller design pattern. The majority of the code is dedicated to the
model, which represents how the MIPS computer operates, holds data, executes instructions, etc.

The main workflow for the simulator is as follows:

- Load the .bi file into memory via the rom loader (See the Rom Loader section below)
- Set the PC to the location of the first instruction
- Run the program

> For more details on new features, see `README.md` in the project root <br>
> For more details on how to run programs, see `INSTRUCTIONS.md` in the project root

## Model

For design notes from Part 1 elements, see [Part 1 Design Notes](Part1_DesignNotes.md).

The majority of the code that represents the MIPS computer can be found
in `src/main/java/group1/mips_simulator/components/*.java`.
UI elements can be found in `src/main/java/group1/mips_simulator/FrontEnd/*.java`.

In addition to our Project Part 1, we have implemented all non-vector instructions, 
a Console Keyboard, Console Display, and Cache Display.


## Instructions

Along with our instructions from Part 1, our Project Part 2 now includes every instruction's implementation except 
Vector instructions.
That includes the following:
- All load/store instructions
- All conditional and branch instructions
- All arithmetic and logical instructions
- All input/output instructions



## Cache

In our simulation, we made a 16 line, 8 word per block, fully associative, word addressable, write-through `cache`.
We implemented our `cache` as more of an interface than a direct form of `Memory`, since technically the actual unit of 
memory in a cache is just the cache `block`.
The cache creates, removes, and manages a set of cache `lines`, which are a key:value pair with a `line tag` key, and a
`cache block` value.

We replaced all our `memory` accesses throughout the simulator with `cache` accesses, as now are our cache will handle 
any memory access. When the simulator attempts to read data from memory, it will follow this flow:
1. `cpu` decodes an `instruction`, and calculates the memory `address` from which to access data
2. `cpu` passes the `address` for that data to our `cache`
3. `cache` masks and extracts two fields from the `address`: a calculated line `tag` and word `offset`
4. `cache` compares the calculated `tag` to every line `tag` currently in cache 
5. if a match is found, the calculated `offset` is used to index into the cache block. The data at that block is either
retrieved or written 
6. if data is being written to that `block`, the `cache` then writes that same data to the `memory` at the right `address`
  (Write-Through)
7. If a match is not found, the `cache` checks its current `capacity` to confirm it holds fewer than 16 `lines`
8. If so, it `enqueues` a `new line`, created by assigning the calculated `tag` to a new `block` 
associated with the `address`' location in `memory`
9. Otherwise, it pops the oldest `line` from the cache `queue`, and then creates the new `line`, applying step 8.
10. After adjusting the cache, it then does a guaranteed-hit cache access by applying steps 4 through 6.

Once the `cpu` has calculated an `effective address` and passed that `word` to our `cpu`, we extract the least 
significant 3 bits of that word to be our word `offset`, and then the next least significant 9 bits as a line `tag`.

*Note:* Technically, our tag could be all the remaining non-offset bits, but in order to match the screenshot in
the project description (which only displays three octals for a tag) we extract the next 9 bits. This should have 
no effect on our simulator's operation, since it has a maximum memory of only 2048 words, meaning our addresses will all 
calculate to values that fit within 11 bits (2048 = 2^11), so including every non-offset bit would mean all our tags 
will have the same first 5 bits of all 0s (b0000). Overall, we're saving about 10 Bytes of space in a full cache.



## Console Keyboard and Display

The console keyboard is simply a string buffer that automatically runs the same function as Run whenever a
user clicks "Done".
The display is where our assembly programs print out string prompts or display output values and text to the user.

