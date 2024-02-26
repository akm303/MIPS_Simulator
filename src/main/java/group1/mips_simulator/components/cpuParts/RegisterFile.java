package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Value;

/**
 * A register file is a collection of all registers accessible and usable by a CPU
 * <p>
 * Design note: when hardware is taped out and manufactured, there's no way to add/remove registers.
 * That's why we're using a fixed array for the GPR and IXR representations.
 */
public class RegisterFile {

    public static final int GPR_COUNT = 4;
    public static final int IXR_COUNT = 3;

    public Register[] GPR = new Register[]{ //General Purpose Reg: R0-R3
            new Register(), // GPR0
            new Register(), // GPR1
            new Register(), // GPR2
            new Register()  // GPR3
    };
    public Register[] IXR = new Register[]{ //Index Reg: X1-X3.
            new Register(), // IXR0
            new Register(), // IXR1
            new Register()  // IXR2
    };
    public Register PC = new Register();       //Prog Counter Reg : address of next instruction to be executed

    // Condition Code Reg : set when arith/logical operations executed
    // VNZE (oVerflow, uNderflow, divZero, Equalornot)
    ConditionCode CC = new ConditionCode();
    Register IR = new Register();       //Instruction Reg : holds instruction to be exe
    Register MAR = new Register();      //Mem Addr Reg : holds address of word to be fetched from memory
    Register MBR = new Register();      //Mem Buffer Reg : holds word just-fetched-from/to-be-writen-to memory
    Register MFR = new Register();      //Mem Fault Reg : contains ID code if machine fault after it occurs
    //Register[] FPR = new Register[2]; //float point register placeholder <<DO NOT IMPLEMENT TILL PART 4>>
    //Other registers add here as we need them (prof said he hasnt given us all of them)

    public RegisterFile() {
        //initialize all reg w/ null
        for (int i = 0; i < GPR_COUNT; i++) {
            Register newReg = new Register();
            newReg.write(new Value(0));
            newReg.setBitWidth((short) Utility.WORD_SIZE);
            GPR[i] = newReg;
        }
        for (int i = 0; i < IXR_COUNT; i++) {
            Register newReg = new Register();
            newReg.write(new Value(0));
            newReg.setBitWidth((short) Utility.WORD_SIZE);
            IXR[i] = newReg;
        }
        //for (Register reg : FPR){ // <<DO NOT IMPLEMENT TILL PART 4>>
        //    reg.write(nullValue);
        //}

        Register[] allOtherReg = new Register[]{PC, CC, IR, MAR, MBR, MFR};
        //Note* add FPR and other registers as you update Reg File implementation
        for (Register reg : allOtherReg) {
            reg.write(new Value(0));
        }

        PC.setBitWidth((short) 12);
        MAR.setBitWidth((short) 12);
        CC.setBitWidth((short) 4);
        MBR.setBitWidth((short)Utility.WORD_SIZE);
        IR.setBitWidth((short)Utility.WORD_SIZE);
        MFR.setBitWidth((short) 4);

    }

    public Register getGPR(int i) {
        /* get a pointer from reg file to general purpose register w/ which to operate
         * i.e. to access R1, call ~.getGPR(1)
         * */
        assert ((i >= 0) && (i <= 3));
        return GPR[i];
    }

    public Register getIXR(int i) {
        /* get a pointer from reg file to the index register w/ which to operate
         * i.e. to access I1, call ~.getGPR(1)
         * */
        assert ((i >= 1) && (i <= 3));
        return IXR[i - 1];
    }

    public Register getPC() {
        return this.PC;
    }

    public Register getMAR() {
        return this.MAR;
    }

    public Register getMBR() {
        return this.MBR;
    }

    public Register getIR() {
        return this.IR;
    }

    public Register getMFR() {
        return this.MFR;
    }

    public ConditionCode getCC() {
        return this.CC;
    }
}
