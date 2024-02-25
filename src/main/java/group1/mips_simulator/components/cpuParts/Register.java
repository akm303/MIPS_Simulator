package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.components.Value;

/**
 * A Register is a space that holds a value, and makes it available for
 * the CPU to read/ write quickly.
 *
 * All Registers are initialized with a Value of 0, and can be modified from there
 */
public class Register {
    public Value value = new Value(0);
    //todo
    //i just realized i never wrote a constructor...but Register seems to work without one
    //since the only instance attribute gets initialized at declaration...so it doesnt need one(?)
    // should i write one, just to be explicit?

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
        this.set(new Value(value_));
    }
    public void write(int value_) {
        // write an int to the register
        this.write((short) value_);
    }

    public void increment(){
        // may outsource this functionality to an ALU class, which would essentially
        // do all arithmetic (EX of the pipeline diagram)
        // for now, for simplicity of program counter, leaving this built-in to register functionality
        this.value.set(this.value.get() + 1);
    }

    public void incrementBy(int n){     // may remove this at a future date if unused and/or we implement
        //increment by multiple
        // implemented using increment in case we want to add logic based on, for example,
        // an instruction PC might need to access as it increments
        for(int i = 0; i < n; i++){
            this.increment();
        }
    }

}
