package group1.mips_simulator.components;

import group1.mips_simulator.components.instruction.Field;
import group1.mips_simulator.components.instruction.OpCode;
import group1.mips_simulator.components.instruction.RXIA_Instruction;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ExecutionInstructionsTest {

    MipsComputer buildMipsComputer() {
        MipsComputer result = new MipsComputer();
        result.memory = new Memory();
        result.registers = new Vector<>() {{
            add(new Register());
            add(new Register());
            add(new Register());
            add(new Register());
        }};
        result.indexRegisters = new Vector<>() {{
            add(new Register());
            add(new Register());
            add(new Register());
            add(new Register());
        }};
        result.programCounter = new Register();
        result.conditionCode = new ConditionCode();

        return result;
    }


    @Test
    void execute_setcce_test_FalseToTrue() {
        ExecutionInstructions underTest = new ExecutionInstructions();
        MipsComputer computer = buildMipsComputer();

        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("setcce"),
                new Vector<>() {{
                    add(new Field(1, 2));  // R
                    add(new Field(0, 2));  // X
                    add(new Field(0, 1));  // I
                    add(new Field(0, 5));  // A
                }}
        );

        computer.registers.get(1).set(0);// c(r) = 0

        computer.conditionCode.setBit(ConditionCode.EBIT_INDEX, false); // e bit = 0
        assertFalse(computer.conditionCode.getEBit());

        computer.executeInstruction(instruction);
        assertTrue(computer.conditionCode.getEBit());
    }

    @Test
    void execute_setcce_test_TrueToFalse() {
        ExecutionInstructions underTest = new ExecutionInstructions();
        MipsComputer computer = buildMipsComputer();

        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("setcce"),
                new Vector<>() {{
                    add(new Field(1, 2));  // R
                    add(new Field(0, 2));  // X
                    add(new Field(0, 1));  // I
                    add(new Field(0, 5));  // A
                }}
        );

        computer.registers.get(1).set(1);// c(r) != 0

        computer.conditionCode.setBit(ConditionCode.EBIT_INDEX, true); // e bit = 1
        assertTrue(computer.conditionCode.getEBit());

        computer.executeInstruction(instruction);
        assertFalse(computer.conditionCode.getEBit());
    }



    @Test
    void execute_jz() {
        ExecutionInstructions underTest = new ExecutionInstructions();
        MipsComputer computer = buildMipsComputer();

        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("jz"),
                new Vector<>() {{
                    add(new Field(0, 2));  // R
                    add(new Field(0, 2));  // X
                    add(new Field(0, 1));  // I
                    add(new Field(20, 5));  // A
                }}
        );

        computer.conditionCode.setBit(ConditionCode.EBIT_INDEX, true);
        assertEquals(computer.programCounter.read().get(), 0);
        computer.executeInstruction(instruction);
        assertEquals(20, computer.programCounter.read().get());
    }
}