package group1.mips_simulator.components;

/**
 * A Register is a space that holds a value, and makes it available for
 * the CPU to read/ write quickly.
 */
public class Register {
    regType type;
    public Value value;

    private enum regType{
        GP, //General Purpose Registers
        IX; //IndeX Registers
    }

    public Value read() {
        // TODO
        return new Value(0);
    }

    public void set(Value newValue) {
        // TODO:
    }

}
