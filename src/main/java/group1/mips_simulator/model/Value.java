package group1.mips_simulator.model;

public class Value {

    private short value_;

    //region Constructors
    public Value(short value) {
        value_ = value;
    }

    public Value(int value) {
        this((short) value);
    }

    //endregion

    //region getters and setters
    public short get() {
        return value_;
    }

    public void set(short value_) {
        this.value_ = value_;
    }

    public void set(int value) {
        this.set((short) value);
    }
    //endregion

    //region Stringify
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
    //endregion

    //region Overrides
    @Override
    public String toString() {
        return this.toString_Decimal();
    }

    @Override
    public int hashCode() {
        return this.value_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Value value = (Value) o;
        return value_ == value.value_;
    }
    //endregion
}
