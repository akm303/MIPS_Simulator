package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.ROM;
import group1.mips_simulator.components.instructionParts.Instruction;
import group1.mips_simulator.components.memParts.Memory;

import group1.mips_simulator.components.instructionParts.Instruction;
import java.util.Vector;

public class RomLoader {
    /*
    read the boot program from the ROM and place it into memory in a location you designated.
    The ROM Loader then transfers control to the program which executes until completion or error
     */

    public void load(ROM rom, Memory mem){
        int memWriteOffset = Config.INSTR_OFFSET;
        Vector<Instruction> romData = rom.getInstructions();
        for(int i = 0; i < romData.size(); i++){
            mem.write((short) (i + memWriteOffset), romData.get(i).toShort());
        }
    }

}

