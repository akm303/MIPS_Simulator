# Design Notes

The project source code can be found at the location `src/main/java/group1/mips_simulator/*.java`.

This project is designed with the Model-View-Controller design pattern. The majority of the code is dedicated to the
model, which represents how the MIPS computer operates, holds data, executes instructions, etc.

The main workflow for the simulator is as follows:

- Load the .bi file into memory via the rom loader (See the Rom Loader section below)
- Set the PC to the location of the first instruction
- Run the program

## Model

For design notes from Part 1 elements, see [Part 1 Design Notes](Part1_DesignNotes.md).

The majority of the code that represents the MIPS computer can be found
in `src/main/java/group1/mips_simulator/components/*.java`.

In addition to our Part 1 requirements, we have implemented a Console Keyboard, Console Display, and Cache Display.
