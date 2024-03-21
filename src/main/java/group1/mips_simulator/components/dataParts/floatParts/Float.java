package group1.mips_simulator.components.dataParts.floatParts;
import group1.mips_simulator.components.dataParts.Value;
import group1.mips_simulator.components.dataParts.FieldProcessors.Field;

import java.util.Vector;


public class Float {
    protected Value value = new Value(0);
    public Field sign; //sign bit
    public Field exp; //exponent
    public Field mant; //mantissa

    //in general, float data has the following fields, each with a purpose in calculating the float
    boolean signBit; //MSB (most significant bit, always indicates sign. Floats dont have an unsigned form)
    short bias; // the bits in the second field are converted to a unsigned int, then subtracted from the exponent bias
                //  (b = 2^(e-1) - 1) //bias is b, exponent has e bits; this is how you calculate bias
                //

    public Float(Vector<Field> fields){
        sign = fields.get(0);
        exp = fields.get(1);
        mant = fields.get(2);
    }

    public int read(){
        /* read the value of this float as an int
        Note that this is a representation of the float,
        and not of the data directly stored in this Float.
        This is why I use int, a short does not have the range to represent this value itself.
        */
        int rvalue = 0;





        return rvalue;
    }

    public Value get() {
        // get the value of data in float
        return this.value;
    }

    public void set(Value newValue) {
        // set the value of data in float
        value = newValue;
    }




}
