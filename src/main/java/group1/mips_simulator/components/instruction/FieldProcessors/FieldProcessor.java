package group1.mips_simulator.components.instruction.FieldProcessors;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.instruction.Field;
import group1.mips_simulator.components.instruction.OpCode;

import java.util.Vector;

public class FieldProcessor {

    public Vector<Field> getFieldsForOpCode(OpCode code, String binaryFields) {
        return switch (code.name.toLowerCase()) {
            // Miscellaneous Instructions, pg 12
            case "hlt" -> binaryToFields_Halt(binaryFields);
            case "trap" -> binaryToFields_Trap(binaryFields);
            // Load/Store instructions, pg 12
            case "ldr", "str", "lda", "ldx", "stx",
                    // Transfer Instructions pg 15
                    "setcce", "jz", "jne", "jcc", "jma", "jsr", "rfs", "sob", "jge",
                    // Arithmetic and logical instructions, pg 16
                    "amr", "smr", "air", "sir",
                    // Floating Point Arithmetic, pg 21
                    "fadd", "fsub", "vadd", "vsub", "cnvrt", "ldfr", "stfr" -> binaryToFields_rxia(binaryFields);
            // Register to Register instructions, pg 17
            case "mlt", "dvd", "trr", "and", "orr", "not" -> binaryToFields_reg2reg(binaryFields);
            // Shift/ Rotate operations, pg 18
            case "src", "rrc" -> binaryToFields_shiftRotate(binaryFields);
            // I/O operations, pg 20
            case "in", "out", "chk" -> binaryToFields_io(binaryFields);
            default -> throw new IllegalArgumentException("Unknown OpCode: " + code.name);
        };
    }


    protected void assertBinLength(String binaryFields) {
        if (binaryFields.length() != 10) {
            throw new IllegalArgumentException("Invalid fields provided. Expected 10 bits, instead got: " + binaryFields);
        }
    }

    /**
     * [RR XX I AAAAA]
     *  01 23 4 5   9
     */
    Vector<Field> binaryToFields_rxia(String binaryFields) {
        assertBinLength(binaryFields);
        String rr = binaryFields.substring(0, 1 + 1);
        String xx = binaryFields.substring(2, 3 + 1);
        String i = binaryFields.substring(4, 4 + 1);
        String address = binaryFields.substring(5, 9 + 1);

        return new Vector<Field>(4) {{
            add(new Field(Utility.binaryToShort(rr), 2));
            add(new Field(Utility.binaryToShort(xx), 2));
            add(new Field(Utility.binaryToShort(i), 1));
            add(new Field(Utility.binaryToShort(address), 5));
        }};
    }

    /**
     * [#### 000000]
     * 0  3 4    9
     */
    Vector<Field> binaryToFields_Halt(String binaryFields) {
        assertBinLength(binaryFields);
        String blank = binaryFields.substring(0, 3 + 1);
        String zeros = binaryFields.substring(4, 9 + 1);

        return new Vector<Field>() {{
            add(new Field(Utility.binaryToShort(blank), 4));
            add(new Field(Utility.binaryToShort(zeros), 6));
        }};
    }

    /**
     * [###### 0000]
     * 0    5 6  9
     */
    Vector<Field> binaryToFields_Trap(String binaryFields) {
        assertBinLength(binaryFields);
        String blank = binaryFields.substring(0, 5 + 1);
        String trapCode = binaryFields.substring(6, 9 + 1);

        return new Vector<Field>() {{
            add(new Field(Utility.binaryToShort(blank), 4));
            add(new Field(Utility.binaryToShort(trapCode), 6));
        }};
    }

    /**
     * [RX RY ######]
     * 01 23 4    9
     */
    Vector<Field> binaryToFields_reg2reg(String binaryFields) {
        assertBinLength(binaryFields);
        String rx = binaryFields.substring(0, 1 + 1);
        String ry = binaryFields.substring(2, 3 + 1);
        String blank = binaryFields.substring(4, 9 + 1);

        return new Vector<Field>(3) {{
            add(new Field(Utility.binaryToShort(rx), 2));
            add(new Field(Utility.binaryToShort(ry), 2));
            add(new Field(Utility.binaryToShort(blank), 6));
        }};
    }

    /**
     * [RR A R ## CCCC]
     * 01 2 3 45 6  9
     */
    Vector<Field> binaryToFields_shiftRotate(String binaryFields) {
        assertBinLength(binaryFields);
        String rr = binaryFields.substring(0, 1 + 1);
        String a = binaryFields.substring(2, 2 + 1);
        String r = binaryFields.substring(3, 3 + 1);
        String blank = binaryFields.substring(4, 5 + 1);
        String count = binaryFields.substring(6, 9 + 1);

        return new Vector<Field>(3) {{
            add(new Field(Utility.binaryToShort(rr), 2));
            add(new Field(Utility.binaryToShort(a), 1));
            add(new Field(Utility.binaryToShort(r), 1));
            add(new Field(Utility.binaryToShort(blank), 2));
            add(new Field(Utility.binaryToShort(count), 4));
        }};
    }

    /**
     * [RR ### DDDDD]
     * 01 2 4 5   9
     */
    Vector<Field> binaryToFields_io(String binaryFields) {
        assertBinLength(binaryFields);
        String r = binaryFields.substring(0, 1 + 1);
        String blank = binaryFields.substring(2, 4 + 1);
        String deviceId = binaryFields.substring(5, 9 + 1);

        return new Vector<Field>(3) {{
            add(new Field(Utility.binaryToShort(r), 2));
            add(new Field(Utility.binaryToShort(blank), 3));
            add(new Field(Utility.binaryToShort(deviceId), 5));
        }};
    }

}
