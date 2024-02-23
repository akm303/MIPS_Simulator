package group1.mips_simulator.components.instructionParts;

public class OpCode {

    public final String name;

    public OpCode(String name) {
        this.name = name;
    }

    /**
     * Convert a number to the OpCode object.
     * This is a factory function for convince allowing one to create OpCode
     * objects with just the number.
     *
     * @param opCodeValue The value to convert into an OpCode
     * @return The resultant OpCode object
     */
    public static group1.mips_simulator.components.instructionParts.OpCode fromNumber(short opCodeValue) {
        return switch (opCodeValue) {
            /* Miscellaneous Instructions */
            case 0 -> new group1.mips_simulator.components.instructionParts.OpCode("hlt");
            case 37 -> new group1.mips_simulator.components.instructionParts.OpCode("trap");

            /* Load/Store Instructions */
            case 1 -> new group1.mips_simulator.components.instructionParts.OpCode("ldr");
            case 2 -> new group1.mips_simulator.components.instructionParts.OpCode("str");
            case 3 -> new group1.mips_simulator.components.instructionParts.OpCode("lda");
            case 4 -> new group1.mips_simulator.components.instructionParts.OpCode("ldx");
            case 5 -> new group1.mips_simulator.components.instructionParts.OpCode("stx");

            /* transfer instructions */
            case 36 -> new group1.mips_simulator.components.instructionParts.OpCode("setcce");
            case 6 -> new group1.mips_simulator.components.instructionParts.OpCode("jz");
            case 7 -> new group1.mips_simulator.components.instructionParts.OpCode("jne");
            case 8 -> new group1.mips_simulator.components.instructionParts.OpCode("jcc");
            case 9 -> new group1.mips_simulator.components.instructionParts.OpCode("jma");
            case 10 -> new group1.mips_simulator.components.instructionParts.OpCode("jsr");
            case 11 -> new group1.mips_simulator.components.instructionParts.OpCode("rfs");
            case 12 -> new group1.mips_simulator.components.instructionParts.OpCode("sob");
            case 13 -> new group1.mips_simulator.components.instructionParts.OpCode("jge");

            /* Arithmetic and Logical Instructions */
            case 14 -> new group1.mips_simulator.components.instructionParts.OpCode("amr");
            case 15 -> new group1.mips_simulator.components.instructionParts.OpCode("smr");
            case 16 -> new group1.mips_simulator.components.instructionParts.OpCode("air");
            case 17 -> new group1.mips_simulator.components.instructionParts.OpCode("sir");

            /* Register to Register */
            case 18 -> new group1.mips_simulator.components.instructionParts.OpCode("mlt");
            case 19 -> new group1.mips_simulator.components.instructionParts.OpCode("dvd");
            case 20 -> new group1.mips_simulator.components.instructionParts.OpCode("trr");
            case 21 -> new group1.mips_simulator.components.instructionParts.OpCode("and");
            case 22 -> new group1.mips_simulator.components.instructionParts.OpCode("orr");
            case 23 -> new group1.mips_simulator.components.instructionParts.OpCode("not");

            /* Shift/Rotate Operations */
            case 24 -> new group1.mips_simulator.components.instructionParts.OpCode("src");
            case 25 -> new group1.mips_simulator.components.instructionParts.OpCode("rrc");

            /* I/O Operations */
            case 26 -> new group1.mips_simulator.components.instructionParts.OpCode("in");
            case 27 -> new group1.mips_simulator.components.instructionParts.OpCode("out");
            case 28 -> new group1.mips_simulator.components.instructionParts.OpCode("chk");

            /* Floating Point Instructions/Vector Operations */
            case 29 -> new group1.mips_simulator.components.instructionParts.OpCode("fadd");
            case 30 -> new group1.mips_simulator.components.instructionParts.OpCode("fsub");
            case 31 -> new group1.mips_simulator.components.instructionParts.OpCode("vadd");
            case 32 -> new group1.mips_simulator.components.instructionParts.OpCode("vsub");
            case 33 -> new group1.mips_simulator.components.instructionParts.OpCode("cnvrt");
            case 34 -> new group1.mips_simulator.components.instructionParts.OpCode("ldfr");
            case 35 -> new group1.mips_simulator.components.instructionParts.OpCode("stfr");

            default -> throw new IllegalArgumentException("Unknown Operation Code: " + Integer.toString(opCodeValue));
        };
    }



}