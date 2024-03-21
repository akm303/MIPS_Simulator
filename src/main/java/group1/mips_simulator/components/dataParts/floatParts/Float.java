package group1.mips_simulator.components.dataParts.floatParts;
import group1.mips_simulator.components.dataParts.Value;


public class Float {
    protected Value value = new Value(0);
    //in general, float data has the following fields, each with a purpose in calculating the float
    boolean signBit; //MSB (most significant bit, always indicates sign. Floats dont have an unsigned form)
    short bias; // the bits in the second field are converted to a unsigned int, then subtracted from the exponent bias
                //  (b = 2^(e-1) - 1) //bias is b, exponent has e bits; this is how you calculate bias
                //

    public short read(){
        /* read value of this float as a short
        Note that this is a representation of the float,
        and not of the data directly stored in a Float
        */
        short rvalue = 0;
        short fdata = value.getShort();



        return rvalue;
    }

    public Value get() {
        return this.value;
    }

    public void set(Value newValue) {
        value = newValue;
    }




}
