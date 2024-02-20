/*
Your CPU simulator should implement the basic registers, the basic instruction set,
a simple ROM Loader, and the elements necessary to execute the basic instruction set.

You will need a ROM that contains the simple loader. When you press the IPL button on the console,
the ROM contents are read into memory and control is transferred to the first instruction of the ROM Loader program.
The ROM can be either a file on your computer or just an array of instructions in your program.

Your ROM Loader should read the boot program from the ROM and place it into memory in a location you designated.
The ROM Loader then transfers control to the program which executes until completion or error.

If your program completes normally, it returns to the boot program to read the next program (at this point your
simulation should stop with PC having the value of the first address of the boot program). Returning to the boot
program means that it prompts the user to either run the currently loaded program again or to load a new program
and run it.

NOTE: Thus, the first address of your program should be greater than the length of your program.

I suggest you load the boot program b= +eginning at location octal 10 and the first address of your program at
octal address = octal 10 + length of boot program + octal 10.

If the program encounters an error, your program should display an error message on the console printer and stop.

If an internal error is detected, display an error code in the console lights and stop. But you should consider
handling the error in your system by generating a machine fault.
 */

//Readme ^

package group1.mips_simulator.components.cpuParts;

public class CPU {
    RegisterFile registers = new RegisterFile();
    RomLoader romloader = new RomLoader();


}
