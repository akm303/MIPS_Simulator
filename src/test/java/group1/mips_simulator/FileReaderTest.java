package group1.mips_simulator;

import group1.mips_simulator.components.instruction.Instruction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void readBinaryFile() {
        FileReader underTest = new FileReader();
        Vector<Instruction> result = underTest.readBinaryFile("src/test/java/group1/mips_simulator/testFile.txt");
        assertEquals(result.size(), 41);
    }
}