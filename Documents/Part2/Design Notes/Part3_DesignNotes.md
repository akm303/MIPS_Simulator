# Design Notes

The project source code can be found at the location `src/main/java/group1/mips_simulator/*.java`.

This project is designed with the Model-View-Controller design pattern. 
The majority of the code is dedicated to the model, which represents how
the MIPS computer operates, holds data, executes instructions, etc.

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

Along with our instructions from Part 1 and Part 2, our Project now includes the second assembly program.



## Program 2

Specific instructions for loading and preparing the program are listed in `INSTRUCTIONS.md`. 
Our program is structured in memory in the following format:
1. Static Data and Pointers:
   - assigned from locations 6 through 32. 
   - This includes pointers to all our strings, useful constants, bitmasks, and buffers allocated in memory
2. String Arrays and Buffers:
   - Prompt Strings: Strings to request that the user enter paragraphs and a word 
   - Paragraph will be entered through console. You may copy and paste text and select `Done`
   - total paragraph may be up to 923 char long ( Paragraph Buffer: MEM [100-1023] )
   - maximum word size may be up to 11 char long ( Word Search Buffer: MEM [87-98] )
3. Subroutines:
   - The following functions are implemented as subroutines:
     + print string
     + read from keyboard
     + find word in paragraph
     + print found
       - additional strings defined here for output
     + print not found
4. `main` method begin at octal 3000


A few additional notes regarding the program, and how it works:
- User will need to type the paragraph into the console keyboard.
- You can either directly type 6 sentences, or copy and paste text in.
- We have supplied a text file called `paragraph.txt` at the project root, containing an example paragraph.
   Simply copy and paste the paragraph, load the octal key into PC (3000 for program 2) and click run.
- Follow the on-screen directions
- To submit the paragraph or text, user will need to click the `Done` button.
- Typing `enter` will neither submit the text, nor add a newline character. It does nothing.
