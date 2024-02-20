package group1.mips_simulator.components.cpuParts;
import group1.mips_simulator.components.Value;

public class RegisterFile {
    /*
    A register file is a collection of all registers accessible and usable by a CPU

    Design note: when hardware is taped out and manufactured, there's no way to add/remove registers.
    that's why im replacing the Vector (used in MipsComputer, which im renaming to Computer) with a preset array
     */
    Register[] GPR = new Register[4];   //R0-R3
    Register[] IXR = new Register[3];   //X1-X3.

    public RegisterFile(){
        //initialize all reg w/ null
        Value nullValue = new Value(0);
        for (Register reg : GPR){
            reg.set(nullValue);
        }
        for (Register reg : IXR){
            reg.set(nullValue);
        }
    }

    public Register getGPR(int i){
        /* get a pointer from reg file to general purpose register w/ which to operate
        * i.e. to access R1, call ~.getGPR(1)
        * */
        assert((i >= 0) && (i <= 3));
        return GPR[i];
    }

    public Register getIXR(int i){
        /* get a pointer from reg file to the index register w/ which to operate
         * i.e. to access I1, call ~.getGPR(1)
         * */
        assert((i >= 1) && (i <=3));
        return IXR[i-1];
    }
}
