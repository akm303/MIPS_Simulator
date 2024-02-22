package group1.mips_simulator.components.instruction;

import java.util.Objects;

public class OpCode {

    public static final int BIT_LENGTH = 6;

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

    public int toInt() {
        switch (this.name.toLowerCase()) {
            /* Undocumented Instructions */
            case "data" -> {
                return 0;
            }
            case "loc", "end" -> {
                return 0;
            }

            /* Miscellaneous Instructions */
            case "hlt" -> {
                return 0;
            }
            case "trap" -> {
                return 37; // 45 octal
            }
            /* Load/Store Instructions */
            case "ldr" -> {
                return 1; // 1 octal
            }
            case "str" -> {
                return 2; // 2 octal
            }
            case "lda" -> {
                return 3; // 3 octal
            }
            case "ldx" -> {
                return 4; // 4 octal
            }
            case "stx" -> {
                return 5; // 5 octal
            }
            /* Transfer Instructions */
            case "setcce" -> {
                return 36; // 44 octal
            }
            case "jz" -> {
                return 6; // 6 octal
            }
            case "jne" -> {
                return 7; // 7 octal
            }
            case "jcc" -> {
                return 8; // 10 octal
            }
            case "jma" -> {
                return 9; // 11 octal
            }
            case "jsr" -> {
                return 10; // 12 octal
            }
            case "rfs" -> {
                return 11; // 13 octal
            }
            case "sob" -> {
                return 12; // 14 octal
            }
            case "jge" -> {
                return 13; // 15 octal
            }
            /* Arithmetic and Logical Instructions */
            case "amr" -> {
                return 14; // 16 octal
            }
            case "smr" -> {
                return 15; // 17 octal
            }
            case "air" -> {
                return 16; // 20 octal
            }
            case "sir" -> {
                return 17; // 21 octal
            }
            /* Register to Register */
            case "mlt" -> {
                return 18; // 22 octal
            }
            case "dvd" -> {
                return 19; // 23 octal
            }
            case "trr" -> {
                return 20; // 24 octal
            }
            case "and" -> {
                return 21; // 25 octal
            }
            case "orr" -> {
                return 22; // 26 octal
            }
            case "not" -> {
                return 23; // 27 octal
            }

            /* Shift/Rotate Operations */
            case "src" -> {
                return 24; // 30 octal
            }
            case "rrc" -> {
                return 25; // 32 octal
            }

            /* I/O Operations */
            case "in" -> {
                return 26; // 32 octal
            }
            case "out" -> {
                return 27; // 33 octal
            }
            case "chk" -> {
                return 28; // 34 octal
            }

            /* Floating Point Instructions/Vector Operations */
            case "fadd" -> {
                return 29; // 35 octal
            }
            case "fsub" -> {
                return 30; // 36 octal
            }
            case "vadd" -> {
                return 31; // 37 octal
            }
            case "vsub" -> {
                return 32; // 40 octal
            }
            case "cnvrt" -> {
                return 33; // 42 octal
            }
            case "ldfr" -> {
                return 34; // 42 octal
            }
            case "stfr" -> {
                return 35; // 43 octal
            }
            default -> throw new IllegalArgumentException("Unknown Operation Code: " + this.name);
        }
    }

    public String toString_Binary() {
        StringBuilder result = new StringBuilder(BIT_LENGTH);
        result.append(Integer.toString(this.toInt()));
        while(result.length() < BIT_LENGTH) {
            result.insert(0, '0');
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpCode opCode = (OpCode) o;
        return Objects.equals(name, opCode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
