package group1.mips_simulator.components.instruction;

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
    public static OpCode fromNumber(short opCodeValue) {
        return switch (opCodeValue) {
            /* Miscellaneous Instructions */
            case 0 -> new OpCode("hlt");
            case 37 -> new OpCode("trap");

            /* Load/Store Instructions */
            case 1 -> new OpCode("ldr");
            case 2 -> new OpCode("str");
            case 3 -> new OpCode("lda");
            case 4 -> new OpCode("ldx");
            case 5 -> new OpCode("stx");

            /* transfer instructions */
            case 36 -> new OpCode("setcce");
            case 6 -> new OpCode("jz");
            case 7 -> new OpCode("jne");
            case 8 -> new OpCode("jcc");
            case 9 -> new OpCode("jma");
            case 10 -> new OpCode("jsr");
            case 11 -> new OpCode("rfs");
            case 12 -> new OpCode("sob");
            case 13 -> new OpCode("jge");

            /* Arithmetic and Logical Instructions */
            case 14 -> new OpCode("amr");
            case 15 -> new OpCode("smr");
            case 16 -> new OpCode("air");
            case 17 -> new OpCode("sir");

            /* Register to Register */
            case 18 -> new OpCode("mlt");
            case 19 -> new OpCode("dvd");
            case 20 -> new OpCode("trr");
            case 21 -> new OpCode("and");
            case 22 -> new OpCode("orr");
            case 23 -> new OpCode("not");

            /* Shift/Rotate Operations */
            case 24 -> new OpCode("src");
            case 25 -> new OpCode("rrc");

            /* I/O Operations */
            case 26 -> new OpCode("in");
            case 27 -> new OpCode("out");
            case 28 -> new OpCode("chk");

            /* Floating Point Instructions/Vector Operations */
            case 29 -> new OpCode("fadd");
            case 30 -> new OpCode("fsub");
            case 31 -> new OpCode("vadd");
            case 32 -> new OpCode("vsub");
            case 33 -> new OpCode("cnvrt");
            case 34 -> new OpCode("ldfr");
            case 35 -> new OpCode("stfr");

            default -> throw new IllegalArgumentException("Unknown Operation Code: " + Integer.toString(opCodeValue));
        };
    }



}
