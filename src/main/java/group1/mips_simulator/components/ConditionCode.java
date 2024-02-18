package group1.mips_simulator.components;

import java.util.Vector;

public class ConditionCode {

    public static final int OBIT_INDEX = 0;
    public static final int UBIT_INDEX = 1;
    public static final int DBIT_INDEX = 2;
    public static final int EBIT_INDEX = 3;

    protected Vector<Boolean> conditionCodes = new Vector<Boolean>(4) {{
        add(false); // cc(0)  Overflow bit
        add(false); // cc(1)  Underflow bit
        add(false); // cc(2)  Divide by 0 bit
        add(false); // cc(3)  Equal or Not bit
    }};

    public boolean getOBit() {
        return getBit(OBIT_INDEX);
    }

    public boolean getUit() {
        return getBit(UBIT_INDEX);
    }

    public boolean getDBit() {
        return getBit(DBIT_INDEX);
    }

    public boolean getEBit() {
        return getBit(EBIT_INDEX);
    }

    public boolean getBit(int targetBitIndex) {
        try {
            return conditionCodes.get(targetBitIndex);
        } catch (Exception e) {
            throw new IllegalArgumentException("Attempted to read an invalid Condition Code bit: " + targetBitIndex);
        }
    }

    public void setBit(int targetBitIndex, boolean bitValue) {
        try {
            conditionCodes.set(targetBitIndex, bitValue);
        } catch (Exception e) {
            throw new IllegalArgumentException("attempted to set an invalid condition code bit: " + targetBitIndex);
        }
    }
}
