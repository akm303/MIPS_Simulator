package group1.mips_simulator.components.instruction;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.instruction.FieldProcessors.FieldProcessor;

import java.util.Vector;

public class Instruction {

    public static Instruction buildInstruction_fromOctal(String octal) {
        String binary = Utility.octalToBinary(octal, 16);
        return buildInstruction_fromBinary(binary);
    }

    public static Instruction buildInstruction_fromBinary(String binary) {
        // Op Code is the first 6 bits
        String opCodeBinary = binary.substring(0, 6); // [0, 6)
        String fieldsBinary = binary.substring(6); //[6, end]

        OpCode code = OpCode.fromNumber((short) Utility.binaryToInt(opCodeBinary));
        // Each OpCode has its own schema for processing the remaining 10 bits
        FieldProcessor processor = new FieldProcessor();
        Vector<Field> fields = processor.getFieldsForOpCode(code, fieldsBinary);

        return new Instruction(code, fields);
    }

    public Instruction(OpCode opCode, Vector<Field> fields) {
        this.opCode = opCode;
        this.fields = fields;
    }

    public OpCode opCode;
    public Vector<Field> fields;

    @Override
    public String toString() {
        return "Instruction{" +
                "opCode=" + opCode +
                ", fields=" + fields +
                '}';
    }
}
