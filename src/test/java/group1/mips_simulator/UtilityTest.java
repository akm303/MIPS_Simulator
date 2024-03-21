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
    void instructionToShort() {
//        Field f1 = new Field(0);
//        Instruction i1 = new Instruction();
//        assertEquals(Utility.instructionToShort( ));
    }

}