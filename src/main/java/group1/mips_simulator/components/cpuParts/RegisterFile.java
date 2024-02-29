package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.components.Config;

/**
 * A register file is a collection of all registers accessible and usable by a CPU
 * <p>
 * Design note: when hardware is taped out and manufactured, there's no way to add/remove registers.
 * That's why we're using a fixed array for the GPR and IXR representations.
 */
public class RegisterFile {


    public Register[] gpr = new Register[]{ //General Purpose Reg: R0-R3
            new Register(), // GPR0
            new Register(), // GPR1
            new Register(), // GPR2
            new Register()  // GPR3
    };
    public Register[] ixr = new Register[]{ //Index Reg: X1-X3.
            new Register(), // IXR0
            new Register(), // IXR1
            new Register()  // IXR2
    };

    Register pc = new Register();       //Prog Counter Reg : address of next instruction to be executed
    //Condition Code Reg : set when arith/logical operations executed
    // ONZE (Overflow, uNderflow, divZero, Equalornot)

    ConditionCode cc = new ConditionCode();
    Register ir = new Register();       //Instruction Reg : holds instruction to be exe
    Register mar = new Register();      //Mem Addr Reg : holds address of word to be fetched from memory
    Register mbr = new Register();      //Mem Buffer Reg : holds word just-fetched-from/to-be-writen-to memory
    Register mfr = new Register();      //Mem Fault Reg : contains ID code if machine fault after it occurs
    //Register[] fpr = new Register[2]; //float point register placeholder <<DO NOT IMPLEMENT TILL PART 4>>

    public RegisterFile() {
        //initialize all reg w/ null

        for (int i = 0; i < gpr.length; i++) {
            Register newReg = new Register();
            newReg.write(0);
            newReg.setBitWidth(Config.WORD_SIZE);
            gpr[i] = newReg;
        }

        for (int i = 0; i < ixr.length; i++) {
            Register newReg = new Register();
            newReg.write(0);
            newReg.setBitWidth(Config.WORD_SIZE);
            ixr[i] = newReg;
        }
        //for (Register reg : FPR){ 
        //    //todo <<DO NOT IMPLEMENT TILL PART 4>>
        //    reg.write(nullValue);
        //}

        Register[] allOtherReg = new Register[]{pc, cc, ir, mar, mbr, mfr};
        //todo Note* add fpr and other registers as you update Reg File implementation
        for (Register reg : allOtherReg) {
            reg.write(0);
        }

        pc.setBitWidth((short) 12);
        mar.setBitWidth((short) 12);
        cc.setBitWidth((short) 4);

        mbr.setBitWidth(Config.WORD_SIZE);
        ir.setBitWidth(Config.WORD_SIZE);
        mbr.setBitWidth(Config.WORD_SIZE);
        ir.setBitWidth(Config.WORD_SIZE);
        mfr.setBitWidth((short) 4);

    }

    public Register getGPR(int i) {
        /* get a pointer from reg file to general purpose register w/ which to operate
         * i.e. to access R1, call ~.getGPR(1)
         * */
        assert ((i >= 0) && (i <= 3));
        return gpr[i];
    }

    public Register getIXR(int i) {
        /* get a pointer from reg file to the index register w/ which to operate
         * i.e. to access I1, call ~.getGPR(1)
         * */
        assert ((i >= 1) && (i <= 3));
        return ixr[i - 1];
    }

    public Register getPC() {
        return this.pc;
    }

    public ConditionCode getCC() {
        return this.cc;
    }

    public Register getIR() {
        return this.ir;
    }

    public Register getMAR() {
        return this.mar;
    }

    public Register getMBR() {
        return this.mbr;
    }

    public Register getMFR() {
        return this.mfr;
    }

    //  public Register getFPR() {
    //  todo : <Dont implement until part 4>
    //  return this.FPR; 
    //}

}
