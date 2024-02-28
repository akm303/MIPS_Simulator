package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Value;

/**
 * A Register is a space that holds a value, and makes it available for
 * the CPU to read/ write quickly.
 *
 * All Registers are initialized with a Value of 0, and can be modified from there
 */
public class Register {
  
    //getter methods
    protected Value value = new Value(0);
    public short bitWidth = Utility.WORD_SIZE;

    public Value get() { // previously get()
        // get the Value in the register
        return this.value;
    }
    public short read() {
        // read the number in the register
        return this.value.get();
    }


    // setter methods
    public void write(Value newValue) {
        this.value = newValue.clone();
        this.value.setSize(this.bitWidth);
    }

    public void set(Value newValue) { //previously set()
        // set the Value of the register
        this.write(newValue);
    }

    public void write(short value_) {
        // write a short to the register
        this.set(new Value(value_));
    }
    public void write(int value_) {
        // write an int to the register
        this.write((short) value_);
    }


    public void increment() {
        this.value.set(this.value.get() + 1);
    }

    public void setBitWidth(short width) {
        this.bitWidth = width;
        this.value.setSize(width);
    }

    public void incrementBy(int n) { // may remove this at a future date if unused and/or we implement
        //increment by multiple
        // implemented using increment in case we want to add logic based on, for example,
        // an instruction PC might need to access as it increments
        for (int i = 0; i < n; i++) {
            this.increment();
        }
    }

    public String toString_Binary() {
        return this.value.toString_Binary();
    }

}
