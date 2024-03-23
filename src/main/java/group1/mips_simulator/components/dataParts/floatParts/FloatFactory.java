package group1.mips_simulator.components.dataParts.floatParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.dataParts.FieldProcessors.Field;
import group1.mips_simulator.components.dataParts.FieldProcessors.FieldProcessor;

import java.util.Vector;

public class FloatFactory {

    public Vector<Field> fields;


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
        //todo commented out because it depends on which you prefer implementation wise.
        // floats are consistent, so doesnt need a seperate handler, but we have a class
        // already dedicated to handling fields, we might as well use it
//        // sign bit is first bit
//        String signBit = binary.substring(0, 1); // [0, 1)
//        // exponent bits are next 7 bits
//        String exponentBinary = binary.substring(1,8); //[6, 8)
//        // mantissa bits are remaining bits
//        String mantissaBinary = binary.substring(8); //[8,end]

        // Every float shares schema for processing all bits.
        // Will pass whole binary to the FieldProcessor to convert the fields into floats.
        FieldProcessor processor = new FieldProcessor();
        Vector<Field> fields = processor.getFieldsForFloat(binary);

        return packageFloat(fields);
    }

    public Float buildInstruction_fromShort(short number) {
        String binary = Utility.shortToBinaryString(number, Config.WORD_SIZE);
        return buildFloat_fromBinary(binary);
    }

    protected Float packageFloat(Vector<Field> fields){
        return new Float(fields);
    }
}
