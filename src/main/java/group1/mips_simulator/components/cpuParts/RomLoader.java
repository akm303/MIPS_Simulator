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
        Vector<String[]> romDataStrings = rom.getInstructions();
        Vector<short[]> romData = convertVectorOfOctalStringToShort(romDataStrings);

        for (int i = 0; i < romData.size(); i++){
            mem.write((romData.get(i)[0]), romData.get(i)[1]);
        }
    }

    private Vector<short[]> convertVectorOfOctalStringToShort(Vector<String[]> stringVector){
        Vector<short[]> rVector = new Vector<>();

        for(String[] octalString : stringVector){
            short val1 = Utility.binaryToShort(Utility.octalStringToBinaryString(octalString[0]));
            short val2 = Utility.binaryToShort(Utility.octalStringToBinaryString(octalString[1]));
            short[] line = new short[] {val1,val2};
            rVector.add(line);
        }
        return rVector;
    }

}

