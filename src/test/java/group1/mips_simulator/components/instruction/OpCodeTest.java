package group1.mips_simulator.components.instruction;

import group1.mips_simulator.components.dataParts.instructionParts.OpCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpCodeTest {

    @Test
    void fromNumber_Decimal() {
        for (int opCodeValue = 0; opCodeValue <= 36; opCodeValue++) {
            // Convert the value to an OpCode object, then back to a value should produce the same result
            assertEquals(OpCode.fromNumber_Decimal(opCodeValue).toShort_Decimal(), opCodeValue);
        }

    }

    @Test
    void toString_Binary() {
        assertEquals(OpCode.fromNumber_Decimal(0).toString_Binary(), "000000");
        assertEquals(OpCode.fromNumber_Decimal(1).toString_Binary(), "000001");
        assertEquals(OpCode.fromNumber_Decimal(2).toString_Binary(), "000010");
        assertEquals(OpCode.fromNumber_Decimal(3).toString_Binary(), "000011");
        assertEquals(OpCode.fromNumber_Decimal(4).toString_Binary(), "000100");

        assertEquals(OpCode.fromNumber_Decimal(5).toString_Binary(), "000101");
        assertEquals(OpCode.fromNumber_Decimal(6).toString_Binary(), "000110");
        assertEquals(OpCode.fromNumber_Decimal(7).toString_Binary(), "000111");
        assertEquals(OpCode.fromNumber_Decimal(8).toString_Binary(), "001000");

        assertEquals(OpCode.fromNumber_Decimal(9).toString_Binary(),  "001001");
        assertEquals(OpCode.fromNumber_Decimal(10).toString_Binary(), "001010");
        assertEquals(OpCode.fromNumber_Decimal(11).toString_Binary(), "001011");
        assertEquals(OpCode.fromNumber_Decimal(12).toString_Binary(), "001100");
        assertEquals(OpCode.fromNumber_Decimal(13).toString_Binary(), "001101");
        assertEquals(OpCode.fromNumber_Decimal(14).toString_Binary(), "001110");
        assertEquals(OpCode.fromNumber_Decimal(15).toString_Binary(), "001111");
        assertEquals(OpCode.fromNumber_Decimal(16).toString_Binary(), "010000");
    }
}