package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Value;

/**
 * A Register is a space that holds a value, and makes it available for
 * the CPU to read/ write quickly.
 */
public class Register {
    protected Value value = new Value(0);
    public short bitWidth = Utility.WORD_SIZE;

    public Value read() {
        return this.value;
    }

    public void write(Value newValue) {
        this.value = newValue.clone();
        this.value.setSize(this.bitWidth);
    }

    public void write(short newValue) {
        this.write(new Value(newValue));
    }

    public void increment() {
        this.value.set(this.value.get() + 1);
    }

    public void setBitWidth(short width) {
        this.bitWidth = width;
        this.value.setSize(width);
    }

    public void incrementBy(int n) {
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
