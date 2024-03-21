package group1.mips_simulator.components.dataParts.instructionParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.dataParts.instructionParts.FieldProcessors.FieldProcessor;

import java.util.Vector;

public class InstructionFactory {

    public Instruction buildInstruction_fromOctal(String octal) {
        String binary = Utility.octalStringToBinaryString(octal, Config.WORD_SIZE);
        return buildInstruction_fromBinary(binary);
    }

    public Instruction buildInstruction_fromBinary(String binary) {
        binary = binary.replace(" ", "");
        if (binary.length() != Config.WORD_SIZE) {
            throw new IllegalArgumentException("Can not build instruction from binary:" +
                    "Not expected size. Given binary: " + binary);
        }
        // Op Code is the first 6 bits
        String opCodeBinary = binary.substring(0, 6); // [0, 6)
        String fieldsBinary = binary.substring(6); //[6, end]

        OpCode code = OpCode.fromNumber_Decimal((short) Utility.binaryToInt(opCodeBinary));
        // Each OpCode has its own schema for processing the remaining 10 bits
        FieldProcessor processor = new FieldProcessor();
        Vector<Field> fields = processor.getFieldsForOpCode(code, fieldsBinary);

        return packageInstruction(code, fields);
    }

    public Instruction buildInstruction_fromShort(short number) {
        String binary = Utility.shortToBinaryString(number, Config.WORD_SIZE);
        return buildInstruction_fromBinary(binary);
    }

    protected Instruction packageInstruction(OpCode code, Vector<Field> fields) {
        return switch (code.name.toLowerCase()) {
            // Miscellaneous Instructions, pg 12
            case "hlt" -> new Instruction(code, fields);
            case "trap" -> new Instruction(code, fields);
            // Load/Store instructions, pg 12
            case "ldr", "str", "lda", "ldx", "stx",
                    // Transfer Instructions pg 15
                    "setcce", "jz", "jne", "jcc", "jma", "jsr", "rfs", "sob", "jge",
                    // Arithmetic and logical instructions, pg 16
                    "amr", "smr", "air", "sir",
                    // Floating Point Arithmetic, pg 21
                    "fadd", "fsub", "vadd", "vsub", "cnvrt", "ldfr", "stfr" -> new RXIA_Instruction(code, fields);
            // Register to Register instructions, pg 17
            case "mlt", "dvd", "trr", "and", "orr", "not" -> new Instruction(code, fields);
            // Shift/ Rotate operations, pg 18
            case "src", "rrc" -> new Instruction(code, fields);
            // I/O operations, pg 20
            case "in", "out", "chk" -> new Instruction(code, fields);
            default -> throw new IllegalArgumentException("Unknown OpCode: " + code.name);
        };
    }

}
