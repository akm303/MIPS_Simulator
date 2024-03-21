package group1.mips_simulator.components.dataParts.floatParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.dataParts.FieldProcessors.Field;
import group1.mips_simulator.components.dataParts.FieldProcessors.FieldProcessor;

import java.util.Vector;

public class FloatFactory {

    public Float buildFloat_fromOctal(String octal) {
        String binary = Utility.octalStringToBinaryString(octal, Config.WORD_SIZE);
        return buildFloat_fromBinary(binary);
    }

    public Float buildFloat_fromBinary(String binary) {
        binary = binary.replace(" ", "");
        if (binary.length() != Config.WORD_SIZE) {
            throw new IllegalArgumentException("Can not build Float from binary:" +
                    "Not expected size. Given binary: " + binary);
        }
        // sign bit is first bit
        String signBit = binary.substring(0, 1); // [0, 1)
        // exponent bits are next 7 bits
        String exponentBinary = binary.substring(1,8); //[6, 8)
        // mantissa bits are remaining bits
        String mantissaBinary = binary.substring(8); //[8,end]

        // Each OpCode has its own schema for processing the remaining 10 bits
        FieldProcessor processor = new FieldProcessor();
        Vector<Field> fields = processor.getFieldsForOpCode(fieldsBinary);

        return packageFloat(fields);
    }

    public Float buildInstruction_fromShort(short number) {
        String binary = Utility.shortToBinaryString(number, Config.WORD_SIZE);
        return buildFloat_fromBinary(binary);
    }

    protected Float packageFloat(fields){

    }
}
