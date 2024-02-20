package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.components.Value;

/**
 * A Register is a space that holds a value, and makes it available for
 * the CPU to read/ write quickly.
 */
public class Register {
    public Value value;

    public Value read() {
        return new Value(0);
    }

    public void write(Value newValue) {
        value = newValue;
    }

}
