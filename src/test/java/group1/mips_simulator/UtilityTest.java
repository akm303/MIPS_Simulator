package group1.mips_simulator;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

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
    void instructionToShort() {
//        Field f1 = new Field(0);
//        Instruction i1 = new Instruction();
//        assertEquals(Utility.instructionToShort( ));
    }

    @Test
    void charToShort() {
        assertEquals(0b0000_0000_0110_0001, Utility.charToShort('a'));
        assertEquals(0b0000_0000_0110_0010, Utility.charToShort('b'));
        assertEquals(0b0000_0000_0110_0011, Utility.charToShort('c'));
        assertEquals(0b0000_0000_0110_0100, Utility.charToShort('d'));

        assertEquals(0b0000_0000_0011_0000, Utility.charToShort('0'));
        assertEquals(0b0000_0000_0011_0001, Utility.charToShort('1'));
        assertEquals(0b0000_0000_0011_0010, Utility.charToShort('2'));
        assertEquals(0b0000_0000_0011_0011, Utility.charToShort('3'));
    }

    @Test
    void shortToChar() {
        assertEquals(Utility.shortToChar((short) 0b0000_0000_0110_0001), 'a');
        assertEquals(Utility.shortToChar((short) 0b0000_0000_0110_0010), 'b');
        assertEquals(Utility.shortToChar((short) 0b0000_0000_0110_0011), 'c');
        assertEquals(Utility.shortToChar((short) 0b0000_0000_0110_0100), 'd');

        assertEquals(Utility.shortToChar((short) 0b0000_0000_0011_0000), '0');
        assertEquals(Utility.shortToChar((short) 0b0000_0000_0011_0001), '1');
        assertEquals(Utility.shortToChar((short) 0b0000_0000_0011_0010), '2');
        assertEquals(Utility.shortToChar((short) 0b0000_0000_0011_0011), '3');
    }

    @Test
    void shortToCharAndBack() {
        HashSet<Character> chars = new HashSet<Character>() {{
            add('a');
            add('b');
            add('c');
            add('d');
            add('e');
            add('f');
            add('g');
            add('h');
            add('i');
            add('j');
            add('k');
            add('l');
            add('m');
            add('n');
            add('o');
            add('p');
            add('q');
            add('r');
            add('s');
            add('t');
            add('u');
            add('v');
            add('w');
            add('x');
            add('y');
            add('z');
            add('0');
            add('1');
            add('2');
            add('3');
            add('4');
            add('5');
            add('6');
            add('7');
            add('8');
            add('9');
            add('-');
            add('.');
        }};
        for(Character c : chars) {
            assertEquals(Utility.shortToChar(Utility.charToShort(c)), c);
        }


    }
}