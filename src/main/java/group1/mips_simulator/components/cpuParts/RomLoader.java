package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.ROM;
import group1.mips_simulator.components.instructionParts.Instruction;
import group1.mips_simulator.components.memParts.Memory;

import group1.mips_simulator.components.instructionParts.Instruction;
import java.util.Vector;

public class RomLoader {
    Memory memory;
    /*
    read the boot program from the ROM and place it into memory in a location you designated.
    The ROM Loader then transfers control to the program which executes until completion or error
     */

    public RomLoader(ROM rom, Memory mem){
        memory = mem;
        Vector<Instruction> romData = rom.getInstructions();
        for(int i = 0; i < romData.size(); i++){
            writeToMemory((short) i, romData.get(i));
        }
    }

    public void writeToMemory(short address, Instruction i){
        short instruction = Utility.instructionToShort(i);
        this.memory.write(address, instruction);
    }
}
