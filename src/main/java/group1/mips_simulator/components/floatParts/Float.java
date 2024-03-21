package group1.mips_simulator.components.floatParts;
import group1.mips_simulator.components.Value;


public class Float {
    protected Value value = new Value(0);

    public Value get() {
        return this.value;
    }

    public void set(Value newValue) {
        value = newValue;
    }
}
