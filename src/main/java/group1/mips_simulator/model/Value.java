package group1.mips_simulator.model;

public class Value {

    private short value_;

    public Value(short value) {
        value_ = value;
    }

    public Value(int value) {
        this((short) value);
    }


    public short get() {
        return value_;
    }

    public void set(short value_) {
        this.value_ = value_;
    }

    public void set(int value) {
        this.set((short) value);
    }

    public void set_fromBinaryString(String binaryString) {
        // TODO:
    }

    public void set_fromOctString(String octString) {
        // TODO:
    }

    public String toString_Binary() {
        return Integer.toBinaryString(this.value_);
    }

    public String toString_Oct() {
        // TODO:
        return "TODO:ToString_Oct";
    }

    public String toString_Decimal() {
        return Integer.toString(this.value_);
    }
}
