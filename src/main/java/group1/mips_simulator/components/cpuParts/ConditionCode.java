package group1.mips_simulator.components.cpuParts;

import group1.mips_simulator.components.cpuParts.Register;

import java.util.Vector;

/**
 * A ConditionCode is a register/ clas that holds various bits.
 * This specific condition code holds (at the time of writing):
 * - Overflow
 * - Underflow
 * - Divide by 0
 * - Equal or Not
 *
 */
public class ConditionCode extends Register {

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

    public void setOverflowBit(boolean value) {
        this.setBit(OBIT_INDEX, value);
    }

    public void setUnderflowBit(boolean value) {
        this.setBit(UBIT_INDEX, value);
    }

    public void setDivideByZeroBit(boolean value) {
        this.setBit(DBIT_INDEX, value);
    }

    public void setEqualityBit(boolean value) {
        this.setBit(EBIT_INDEX, value);
    }

    @Override
    public String toString_Binary() {
        StringBuilder result = new StringBuilder();
        result.append((this.getOBit()) ? ("1") : ("0"));
        result.append((this.getUit()) ? ("1") : ("0"));
        result.append((this.getEBit()) ? ("1") : ("0"));
        result.append((this.getDBit()) ? ("1") : ("0"));
        return result.toString();
    }
}
