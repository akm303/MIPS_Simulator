package group1.mips_simulator.components.cpuParts;
import group1.mips_simulator.components.Value;

public class RegisterFile {
    /*
    A register file is a collection of all registers accessible and usable by a CPU

    Design note: when hardware is taped out and manufactured, there's no way to add/remove registers.
    that's why im replacing the Vector (used in MipsComputer, which im renaming to Computer) with a preset array
     */
    Register[] gpr = new Register[4];   //General Purpose Reg: R0-R3
    Register[] ixr = new Register[3];   //Index Reg: X1-X3.
    Register pc = new Register();       //Prog Counter Reg : address of next instruction to be executed
    Register cc = new Register();       //Condition Code Reg : set when arith/logical operations executed
                                        // ONZE (Overflow, uNderflow, divZero, Equalornot)
                                // (only one i dont understand is "equal or not", is that saying, set flat to 1 if equal
                                // and set to 0 if not?)
    Register ir = new Register();       //Instruction Reg : holds instruction to be exe
    Register mar = new Register();      //Mem Addr Reg : holds address of word to be fetched from memory
    Register mbr = new Register();      //Mem Buffer Reg : holds word just-fetched-from/to-be-writen-to memory
    Register mfr = new Register();      //Mem Fault Reg : contains ID code if machine fault after it occurs
    //Register[] fpr = new Register[2]; //float point register placeholder <<DO NOT IMPLEMENT TILL PART 4>>
    //Other registers add here as we need them (prof said he hasnt given us all of them)

    public RegisterFile(){
        //initialize all reg w/ null
        Value nullValue = new Value(0);

        for (Register reg : gpr){
            reg.set(nullValue);
        }
        for (Register reg : ixr){
            reg.set(nullValue);
        }
        //for (Register reg : FPR){ // <<DO NOT IMPLEMENT TILL PART 4>>
        //    reg.write(nullValue);
        //}

        Register[] allOtherReg = new Register[]{pc,cc,ir,mar,mbr,mfr};
        //Note* add fpr and other registers as you update Reg File implementation
        for (Register reg : allOtherReg) {
            reg.set(nullValue);
        }

    }

    public Register getGPR(int i){
        /* get a pointer from reg file to general purpose register w/ which to operate
        * i.e. to access R1, call ~.getGPR(1)
        * */
        assert((i >= 0) && (i <= 3));
        return gpr[i];
    }

    public Register getIXR(int i){
        /* get a pointer from reg file to the index register w/ which to operate
         * i.e. to access I1, call ~.getGPR(1)
         * */
        assert((i >= 1) && (i <=3));
        return ixr[i-1];
    }

    public Register getPC(){
        return this.pc;
    }
    public Register getCC(){
        return this.cc;
    }
    public Register getIR(){
        return this.ir;
    }
    public Register getMAR(){
        return this.mar;
    }
    public Register getMBR(){
        return this.mbr;
    }
    public Register getMFR(){
        return this.mfr;
    }
//    public Register getFPR() { return this.FPR; }

}
