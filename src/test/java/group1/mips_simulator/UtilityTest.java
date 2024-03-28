package group1.mips_simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void isValidBinary() {
        assertTrue(Utility.isValidBinary("11010100101"));
        assertTrue(Utility.isValidBinary("00000"));
        assertTrue(Utility.isValidBinary("0000 0000 0000 0000"));
        assertTrue(Utility.isValidBinary("1111 1111 1111 1111"));
        assertTrue(Utility.isValidBinary("    "));


        assertFalse(Utility.isValidBinary("abcd"));
        assertFalse(Utility.isValidBinary("0001234"));
    }

    @Test
    void isValidOctal() {
        assertTrue(Utility.isValidOctal("11010100101"));
        assertTrue(Utility.isValidOctal("00000"));
        assertTrue(Utility.isValidOctal("0000 0000 0000 0000"));
        assertTrue(Utility.isValidOctal("1111 1111 1111 1111"));
        assertTrue(Utility.isValidOctal("    "));
        assertTrue(Utility.isValidOctal("0001234"));

        assertTrue(Utility.isValidOctal("777777"));


        assertFalse(Utility.isValidOctal("abcd"));
        assertFalse(Utility.isValidOctal("12345678"));
    }

    @Test
    void shortToBinaryString_Pretty() {
        assertEquals(Utility.shortToBinaryString_Pretty((short) 0, (short) 4), "0000");
        assertEquals(Utility.shortToBinaryString_Pretty((short) 15, (short) 4), "1111");
        assertEquals(Utility.shortToBinaryString_Pretty((short) -1, (short) 4), "1111");
        assertEquals(Utility.shortToBinaryString_Pretty((short) 15, (short) 12), "0000_0000_1111");
        assertEquals(Utility.shortToBinaryString_Pretty((short) 15, (short) 16), "0000_0000_0000_1111");
    }

    @Test
    void intTo32BitString() {
        assertEquals("00000000000000000000000000000000", Utility.intTo32BitString(0));
        assertEquals("00000000000000000000000000000001", Utility.intTo32BitString(1));
        assertEquals("11111111111111111111111111111111", Utility.intTo32BitString(-1));
        assertEquals("00000000000000000000000000001000", Utility.intTo32BitString(8));
        assertEquals("11111111111111111111111111111000", Utility.intTo32BitString(-8));
    }

    @Test
    void binaryNot() {
        assertEquals("11111111", Utility.binaryNot("00000000"));
        assertEquals("00000000", Utility.binaryNot("11111111"));
        assertEquals("01010101", Utility.binaryNot("10101010"));
    }

    @Test
    void rotateTests() {
        assertEquals("0010", Utility.rotateLeftOne("0001"));
        assertEquals("0010", Utility.rotateRightOne("0100"));


        assertEquals("0001", Utility.rotateLeftOne("1000"));
        assertEquals("1000", Utility.rotateRightOne("0001"));
    }

    @Test
    void instructionToShort() {
//        Field f1 = new Field(0);
//        Instruction i1 = new Instruction();
//        assertEquals(Utility.instructionToShort( ));
    }

}