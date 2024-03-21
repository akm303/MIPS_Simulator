package group1.mips_simulator.components.dataParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;

public class Value {

    protected short value_;
    protected short size = Config.WORD_SIZE; // 16 by default


    //region Constructors
    public Value(short value) {
        value_ = value;
    }

    public Value(int value) {
        this((short) value);
    }

    public Value clone() {
        return new Value(this.value_);
    }

    //endregion

    //region getters
    public short get() { return this.getShort(); }

    public short getShort() {return value_;}

    public int getInt() {return (int) value_;}

    //region setters
    public void set(short value_) {
        this.value_ = value_;
    }

    public void set(Value value_) { this.set(value_.get()); }


    public void set(int value) {
        this.set((short) value);
    }

    public void setSize(short newSize) {
        this.size = newSize;
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
        return Utility.shortToBinaryString_Pretty(this.value_, this.size);
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
