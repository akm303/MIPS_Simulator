package group1.mips_simulator.components.instruction.FieldProcessors;

import group1.mips_simulator.components.dataParts.Field;
import group1.mips_simulator.components.dataParts.instructionParts.FieldProcessors.FieldProcessor;
import group1.mips_simulator.components.dataParts.instructionParts.OpCode;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldProcessorTest {

    @Test
    void getFieldsForOpCode() {
        FieldProcessor underTest = new FieldProcessor();

        OpCode inputOpCode = new OpCode("ldr");
        Vector<Field> expectedOutput = new Vector<>() {{
            add(new Field(1, 2));
            add(new Field(1, 2));
            add(new Field(1, 1));
            add(new Field(1, 5));
        }};

        Vector<Field> actualOutput = underTest.getFieldsForOpCode(inputOpCode, "01 01 1 00001");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getFieldsForOpCode_rxia() {
        Vector<String> opCodes = new Vector<>() {{
            add("ldr");
            add("str");
            add("lda");
            add("ldx");
            add("stx");

            add("setcce");
            add("jz");
            add("jne");
            add("jcc");
            add("jma");
            add("jsr");
            add("rfs");
            add("sob");
            add("jge");

            add("amr");
            add("smr");
            add("air");
            add("sir");

            add("fadd");
            add("fsub");
            add("vadd");
            add("vsub");
            add("cnvrt");
            add("ldfr");
            add("stfr");
        }};

        FieldProcessor underTest = new FieldProcessor();
        Vector<Field> expectedOutput = new Vector<>() {{
            add(new Field(3, 2));
            add(new Field(2, 2));
            add(new Field(0, 1));
            add(new Field(17, 5));
        }};

        for (String opCode : opCodes) {
            OpCode inputOpCode = new OpCode(opCode);

            Vector<Field> actualOutput = underTest.getFieldsForOpCode(inputOpCode, "11 10 0 10001");

            assertEquals(expectedOutput, actualOutput);

        }
    }

    @Test
    void getFieldsForOpCode_hlt() {
        FieldProcessor underTest = new FieldProcessor();

        OpCode inputOpCode = new OpCode("hlt");
        Vector<Field> expectedOutput = new Vector<>() {{
            add(new Field(0, 4));
            add(new Field(63, 6));
        }};

        Vector<Field> actualOutput = underTest.getFieldsForOpCode(inputOpCode, "0000 111111");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getFieldsForOpCode_trap() {
        FieldProcessor underTest = new FieldProcessor();

        OpCode inputOpCode = new OpCode("trap");
        Vector<Field> expectedOutput = new Vector<>() {{
            add(new Field(0, 6));
            add(new Field(15, 4));
        }};

        Vector<Field> actualOutput = underTest.getFieldsForOpCode(inputOpCode, "000000 1111");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getFieldsForOpCode_reg2reg() {
        FieldProcessor underTest = new FieldProcessor();

        Vector<String> opCodes = new Vector<>() {{
            add("mlt");
            add("dvd");
            add("trr");
            add("and");
            add("orr");
            add("not");
        }};

        Vector<Field> expectedOutput = new Vector<>() {{
            add(new Field(2, 2));
            add(new Field(1, 2));
            add(new Field(0, 6));
        }};

        for (String opCode : opCodes) {
            OpCode inputOpCode = new OpCode(opCode);
            Vector<Field> actualOutput = underTest.getFieldsForOpCode(inputOpCode, "10 01 000000");

            assertEquals(expectedOutput, actualOutput);
        }
    }

    @Test
    void getFieldsForOpCode_shiftRotate() {
        FieldProcessor underTest = new FieldProcessor();

        Vector<String> opCodes = new Vector<>() {{
            add("src");
            add("rrc");
        }};

        Vector<Field> expectedOutput = new Vector<>() {{
            add(new Field(3, 2));
            add(new Field(1, 1));
            add(new Field(0, 1));
            add(new Field(0, 2));
            add(new Field(9, 4));
        }};

        for (String opCode : opCodes) {
            OpCode inputOpCode = new OpCode(opCode);
            Vector<Field> actualOutput = underTest.getFieldsForOpCode(inputOpCode, "11 1 0 00 1001");

            assertEquals(expectedOutput, actualOutput);
        }
    }

    @Test
    void getFieldsForOpCode_io() {
        FieldProcessor underTest = new FieldProcessor();

        Vector<String> opCodes = new Vector<>() {{
            add("in");
            add("out");
            add("chk");
        }};

        Vector<Field> expectedOutput = new Vector<>() {{
            add(new Field(2, 2));
            add(new Field(0, 3));
            add(new Field(9, 5));
        }};

        for (String opCode : opCodes) {
            OpCode inputOpCode = new OpCode(opCode);
            Vector<Field> actualOutput = underTest.getFieldsForOpCode(inputOpCode, "10 000 01001");

            assertEquals(expectedOutput, actualOutput);
        }
    }
}