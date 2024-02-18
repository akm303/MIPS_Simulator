package group1.mips_simulator.components.instruction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {

    @Test
    void toString_Binary() {
        Instruction hlt = Instruction.buildInstruction_fromBinary("0000000000000000");
        assertEquals("0000000000000000", hlt.toString_Binary());

        Instruction ldr = Instruction.buildInstruction_fromBinary("0000010101101101");
        assertEquals("0000010101101101", ldr.toString_Binary());
    }
}