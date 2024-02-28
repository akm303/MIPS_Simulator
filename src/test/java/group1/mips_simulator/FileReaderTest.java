package group1.mips_simulator;

import group1.mips_simulator.components.instructionParts.Instruction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void readBinaryFile() {
        FileReader underTest = new FileReader();
        Vector<Instruction> result = null;
        try {
            result = underTest.readBinaryFile("src/test/java/group1/mips_simulator/testFile.txt");
        } catch (IOException e) {
<<<<<<< HEAD
            throw new RuntimeException("Unexpected runtime error in FileReaderTest.readBinaryFile()");
=======
            assert(false);
>>>>>>> ac704b8746ea00a66f3462698d5c6c8a0a43ae22
        }
        assertEquals(result.size(), 41);
    }
}