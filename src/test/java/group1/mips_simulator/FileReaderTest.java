package group1.mips_simulator;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {

    @Test
    void readBinaryFile() {
        FileReaderSwing underTest = new FileReaderSwing();
        Vector<String[]> result = null;
        try {
            result = underTest.readBinaryFile("src/test/java/group1/mips_simulator/testFile.txt");
        } catch (IOException e) {
            assert (false);
        }
        assertEquals(result.size(), 41);
    }

    @Test
    void processLineTest() {
        FileReaderSwing underTest = new FileReaderSwing();
        String[] expectedResult = new String[]{
                "000011",
                "003242"
        };
        assertEquals(expectedResult[0], underTest.processLine("000011\t003242\t\tldr 2,2,2,1")[0]);
        assertEquals(expectedResult[1], underTest.processLine("000011\t003242\t\tldr 2,2,2,1")[1]);
    }

    @Test
    void processLineTest_jz() {
        FileReaderSwing underTest = new FileReaderSwing();
        String[] expectedResult = new String[]{
                "000011",
                "014242"
        };
        assertEquals(expectedResult[0], underTest.processLine("000011\t014242\t\tjz 2,2,1")[0]);
        assertEquals(expectedResult[1], underTest.processLine("000011\t014242\t\tjz 2,2,1")[1]);
    }
}