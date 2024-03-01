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

    public static final int OBIT_INDEX = 0; //Overflow bit
    public static final int UBIT_INDEX = 1; //Underflow bit
    public static final int DBIT_INDEX = 2; //Divide-by-0 bit
    public static final int EBIT_INDEX = 3; // Equal-or-Not bit

    protected Vector<Boolean> conditionCodes = new Vector<Boolean>(4) {{
        add(false); // cc(0)  Overflow bit
        add(false); // cc(1)  Underflow bit
        add(false); // cc(2)  Divide by 0 bit
        add(false); // cc(3)  Equal or Not bit
    }};

    //Overflow bit
    public boolean getOBit() {
        return getBit(OBIT_INDEX);
    }

    //Underflow bit
    public boolean getUit() {
        return getBit(UBIT_INDEX);
    }

    //Divide-by-0 bit
    public boolean getDBit() {
        return getBit(DBIT_INDEX);
    }

    // Equal-or-Not bit
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
