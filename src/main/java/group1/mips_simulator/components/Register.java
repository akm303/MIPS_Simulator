package group1.mips_simulator.components;

/**
 * A Register is a space that holds a value, and makes it available for
 * the CPU to read/ write quickly.
 */
public class Register {

    public Register() {

    }

    public Register(short initialValue) {

    }

    public Register(Value initialValue) {

    }

    public Value read() {
        // TODO
        return new Value(0);
    }

    public void set(Value newValue) {
        // TODO:
    }

    public void set(int newValue) {
        this.set(new Value(newValue));
    }

}
