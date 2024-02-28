package group1.mips_simulator.components.instructionParts;

import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class Instruction {

    public static final int BIT_COUNT = 16;

    public Instruction(OpCode opCode, Vector<Field> fields) {
        this.opCode = opCode;
        this.fields = fields;
    }

    public OpCode opCode;
    public Vector<Field> fields;

    public String toString_Binary() {
        StringBuilder result = new StringBuilder();
        result.append(this.opCode.toString_Binary());
        for (Field f : fields) {
            result.append(f.toBinString());
        }
        return result.toString();
    }

    /**
     * Is this instruction a "Transfer Instruction"? True if so, false otherwise.
     * This is useful to quickly check because transfer instructions change the Program Counter
     * so during execution we don't want to double increment the PC accidentally.
     */
    public boolean isTransferInstruction() {
        return switch (this.opCode.name.toLowerCase()) {
            case "setcce", "jz", "jne", "jcc", "jma", "jsr", "rfs", "sob", "jge" -> true;
            default -> false;
        };
    }

    public boolean isHaltInstruction() {
        return this.opCode.name.equalsIgnoreCase("hlt");
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "opCode=" + opCode +
                ", fields=" + fields +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return Objects.equals(opCode, that.opCode) && Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        int fieldsHash = Arrays.deepHashCode(fields.toArray());
        return Objects.hash(opCode.hashCode(), fieldsHash);
    }
}
