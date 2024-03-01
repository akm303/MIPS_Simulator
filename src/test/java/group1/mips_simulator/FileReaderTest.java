package group1.mips_simulator;

import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.Instruction;
import group1.mips_simulator.components.instructionParts.OpCode;
import group1.mips_simulator.components.instructionParts.RXIA_Instruction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {

    @Test
    void readBinaryFile() {
        FileReader underTest = new FileReader();
        Vector<String[]> result = null;
        try {
            result = underTest.readBinaryFile("src/test/java/group1/mips_simulator/testFile.txt");
        } catch (IOException e) {
            assert(false);
        }
        assertEquals(result.size(), 41);
    }

    @Test
    void processLineTest() {
        FileReader underTest = new FileReader();
        Instruction expectedResult = new RXIA_Instruction(new OpCode("ldr"), new Vector<>() {{
            add(new Field(2, 2));
            add(new Field(2, 2));
            add(new Field(1, 1));
            add(new Field(2, 5));
        }});
        assertEquals(expectedResult, underTest.processLine("000011\t003242\t\tldr 2,2,2,1"));
    }

    @Test
    void processLineTest_jz() {
        FileReader underTest = new FileReader();
        Instruction expectedResult = new RXIA_Instruction(new OpCode("jz"), new Vector<>() {{
            add(new Field(0, 2));
            add(new Field(2, 2));
            add(new Field(1, 1));
            add(new Field(2, 5));
        }});
        assertEquals(expectedResult, underTest.processLine("000011\t014242\t\tjz 2,2,1"));
    }
}