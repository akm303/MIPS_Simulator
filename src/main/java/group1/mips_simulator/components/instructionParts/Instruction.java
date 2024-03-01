package group1.mips_simulator.components.instructionParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.Value;
import group1.mips_simulator.components.instructionParts.FieldProcessors.FieldProcessor;

import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

public class Instruction {

    public static final int BIT_COUNT = 16;

    public static Instruction buildInstruction_fromOctal(String octal) {
        String binary = Utility.octalStringToBinaryString(octal, 16);
        return buildInstruction_fromBinary(binary);
    }

    public static Instruction buildInstruction_fromBinary(String binary) {
        // Op Code is the first 6 bits
        String opCodeBinary = binary.substring(0, 6); // [0, 6)
        String fieldsBinary = binary.substring(6); //[6, end]

        OpCode code = OpCode.fromNumber_Decimal(Utility.binaryToInt(opCodeBinary));
        // Each OpCode has its own schema for processing the remaining 10 bits
        FieldProcessor processor = new FieldProcessor();
        Vector<Field> fields = processor.getFieldsForOpCode(code, fieldsBinary);

        return new Instruction(code, fields);
    }

    public static Instruction buildInstruction_fromShort(short number) {
        return buildInstruction_fromBinary(Utility.shortToBinaryString(number, Config.WORD_SIZE));
    }

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


    public short toShort(){
        // generates an int from string of self,
        // returns a short casting of this value
        int rvalue = 0;
        char[] instructions = toString_Binary().toCharArray();
        for(int i = 0; i < instructions.length; i++){
            if(instructions[i] == '1')
                rvalue |= 1<<i;
        }
        return (short) rvalue;
    }

    public Value toValue(){
        //generate a Value of itself
        return new Value(this.toShort());
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
