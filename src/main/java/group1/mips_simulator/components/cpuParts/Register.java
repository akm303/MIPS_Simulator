package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.components.Value;

/**
 * A Register is a space that holds a value, and makes it available for
 * the CPU to read/ write quickly.
 */
public class Register {
    public Value value = new Value(0);

    //getter methods
    public Value get() {
        // get the Value in the register
        return this.value;
    }
    public short read() {
        // read the number in the register
        return this.value.get();
    }


    // setter methods
    public void set(Value newValue) {
        // set the Value of the register
        this.value = newValue;
    }
    public void write(short value_) {
        // write a short to the register
        this.value = new Value(value_);
    }
    public void write(int value_) {
        // write an int to the register
        this.write((short) value_);
    }

    public void increment(){
        this.value.set(this.value.get() + 1);
    }

    public void incrementBy(int n){
        //increment by multiple
        // implemented using increment in case we want to add logic based on, for example,
        // an instruction PC might need to access as it increments
        for(int i = 0; i < n; i++){
            this.increment();
        }
    }

}
