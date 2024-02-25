package group1.mips_simulator.components;

import group1.mips_simulator.components.cpuParts.ConditionCode;
import group1.mips_simulator.components.cpuParts.RegisterFile;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.OpCode;
import group1.mips_simulator.components.instructionParts.RXIA_Instruction;
import group1.mips_simulator.components.memParts.Memory;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ExecutionInstructionsTest {

    Computer buildComputer() {
        Computer result = new Computer();
        result.memory = new Memory();
        result.cpu.regfile = new RegisterFile();
        return result;
    }


    @Test
    void execute_setcce_test_FalseToTrue() {
        ExecutionInstructions underTest = new ExecutionInstructions();
        Computer computer = buildComputer();

        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("setcce"),
                new Vector<>() {{
                    add(new Field(1, 2));  // R
                    add(new Field(0, 2));  // X
                    add(new Field(0, 1));  // I
                    add(new Field(0, 5));  // A
                }}
        );

        computer.cpu.regfile.getGPR(1).write((short) 0); // c(r) = 0

        // e bit = 0
        computer.cpu.regfile.getCC().setBit(ConditionCode.EBIT_INDEX, false);
        assertFalse(computer.cpu.regfile.getCC().getEBit());

        computer.executeInstruction(instruction);
        assertTrue(computer.cpu.regfile.getCC().getEBit());
    }

    @Test
    void execute_setcce_test_TrueToFalse() {
        ExecutionInstructions underTest = new ExecutionInstructions();
        Computer computer = buildComputer();

        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("setcce"),
                new Vector<>() {{
                    add(new Field(1, 2));  // R
                    add(new Field(0, 2));  // X
                    add(new Field(0, 1));  // I
                    add(new Field(0, 5));  // A
                }}
        );

        computer.cpu.regfile.getGPR(1).write((short) 1); // c(r) = 1

        // e bit = 0
        computer.cpu.regfile.getCC().setBit(ConditionCode.EBIT_INDEX, true);
        assertTrue(computer.cpu.regfile.getCC().getEBit());

        computer.executeInstruction(instruction);
        assertFalse(computer.cpu.regfile.getCC().getEBit());
    }


    @Test
    void execute_jz() {
        ExecutionInstructions underTest = new ExecutionInstructions();
        Computer computer = buildComputer();

        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("jz"),
                new Vector<>() {{
                    add(new Field(0, 2));   // R
                    add(new Field(0, 2));   // X
                    add(new Field(0, 1));   // I
                    add(new Field(20, 5));  // A
                }}
        );

        computer.cpu.regfile.getCC().setBit(ConditionCode.EBIT_INDEX, true);
        assertEquals(computer.cpu.regfile.getPC().read().get(), 0);
        computer.executeInstruction(instruction);
        assertEquals(20, computer.cpu.regfile.getPC().read().get());
    }
}