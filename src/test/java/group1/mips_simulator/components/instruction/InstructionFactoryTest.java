package group1.mips_simulator.components.instruction;

import group1.mips_simulator.components.instructionParts.instruction.Instruction;
import group1.mips_simulator.components.instructionParts.instruction.InstructionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstructionFactoryTest {

    @Test
    void toString_Binary() {
        InstructionFactory factory = new InstructionFactory();
        Instruction hlt = factory.buildInstruction_fromBinary("0000000000000000");
        assertEquals("000000 0000 000000", hlt.toString_Binary());

        Instruction ldr = factory.buildInstruction_fromBinary("0000010101101101");
        assertEquals("000001 01 01 1 01101", ldr.toString_Binary());
    }
}