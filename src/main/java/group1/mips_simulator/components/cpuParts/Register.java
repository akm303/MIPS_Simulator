package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Value;

/**
 * A Register is a space that holds a value, and makes it available for
 * the CPU to read/ write quickly.
 * <p>
 * All Registers are initialized with a Value of 0, and can be modified from there
 */
public class Register {

    //getter methods
    protected Value value = new Value(0);
    protected short bitWidth = Utility.WORD_SIZE;

    public Register() {
        this((short) 0);
    }

    public Register(short v) {
        this(new Value(v));
    }

    public Register(Value v) {
        this.value = v.clone();
    }

    public Value read() { // previously get()
        // get the Value in the register
        return this.value;
    }

    // setter methods
    public void write(Value newValue) {
        this.value = newValue.clone();
        this.value.setSize(this.bitWidth);
    }

    public void write(short newValue) {
        // set the Value of the register
        this.write(new Value(newValue));
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

    /**
     * increment by multiple
     * implemented using increment in case we want to add logic based on, for example,
     * an instruction PC might need to access as it increments
     */
    public void incrementBy(int n) {
        for (int i = 0; i < n; i++) {
            this.increment();
        }
    }

    public String toString_Binary() {
        return this.value.toString_Binary();
    }

}
