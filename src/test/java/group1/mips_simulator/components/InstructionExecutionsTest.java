package group1.mips_simulator.components;

import group1.mips_simulator.components.cpuParts.ConditionCode;
import group1.mips_simulator.components.cpuParts.RegisterFile;
import group1.mips_simulator.components.instructionExecution.InstructionExecutions;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.OpCode;
import group1.mips_simulator.components.instructionParts.instruction.RXIA_Instruction;
import group1.mips_simulator.components.memParts.Memory;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class InstructionExecutionsTest {

    Computer buildComputer() {
        Computer result = new Computer();
        result.memory = new Memory();
        result.cpu.regfile = new RegisterFile();
        return result;
    }


    @Test
    void execute_setcce_test_FalseToTrue() {
        InstructionExecutions underTest = new InstructionExecutions();
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
        InstructionExecutions underTest = new InstructionExecutions();
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
    void execute_ldr() {
        // Set up inputs
        Computer computer = buildComputer();
        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("ldr"),
                new Vector<>() {{
                    add(new Field(0, 2));   // R
                    add(new Field(0, 2));   // X
                    add(new Field(0, 1));   // I
                    add(new Field(20, 5));  // A
                }}
        );

        // This value will be put into register
        computer.memory.write((short) 20, (short) 100);

        // Run code under test to demonstrate change
        assertEquals(0, computer.cpu.regfile.getGPR(0).read());
        computer.executeInstruction(instruction);
        assertEquals(100, computer.cpu.regfile.getGPR(0).read());
    }

    @Test
    void execute_str() {
        // Set up inputs
        Computer computer = buildComputer();
        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("str"),
                new Vector<>() {{
                    add(new Field(0, 2));   // R
                    add(new Field(0, 2));   // X
                    add(new Field(0, 1));   // I
                    add(new Field(20, 5));  // A
                }}
        );

        // This value will be written into memory
        computer.cpu.regfile.getGPR(0).write(new Value(100));

        // Run code under test to demonstrate change
        assertEquals(0, computer.memory.read((short) 20));
        computer.executeInstruction(instruction);
        assertEquals(100, computer.memory.read((short) 20));
    }

    @Test
    void execute_lda() {
        // Set up inputs
        Computer computer = buildComputer();
        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("lda"),
                new Vector<>() {{
                    add(new Field(1, 2));   // R
                    add(new Field(0, 2));   // X
                    add(new Field(0, 1));   // I
                    add(new Field(20, 5));  // A
                }}
        );

        // Run code under test to demonstrate change
        // EA will be written into register
        assertEquals(0, computer.cpu.regfile.getGPR(1).read());
        computer.executeInstruction(instruction);
        assertEquals(20, computer.cpu.regfile.getGPR(1).read());
    }

    @Test
    void execute_ldx() {
        // Set up inputs
        Computer computer = buildComputer();
        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("ldx"),
                new Vector<>() {{
                    add(new Field(1, 2));   // R
                    add(new Field(1, 2));   // X
                    add(new Field(0, 1));   // I
                    add(new Field(20, 5));  // A
                }}
        );

        // This will be put into index reg
        computer.memory.write((short) 20, (short) 99);

        // Run code under test to demonstrate change
        assertEquals(0, computer.cpu.regfile.getIXR(1).read());
        computer.executeInstruction(instruction);
        assertEquals(99, computer.cpu.regfile.getIXR(1).read());
    }

    @Test
    void execute_stx() {
        // Set up inputs
        Computer computer = buildComputer();
        RXIA_Instruction instruction = new RXIA_Instruction(
                new OpCode("stx"),
                new Vector<>() {{
                    add(new Field(1, 2));   // R
                    add(new Field(1, 2));   // X
                    add(new Field(0, 1));   // I
                    add(new Field(20, 5));  // A
                }}
        );

        // This will be put into index reg
        computer.cpu.regfile.getIXR(1).write(55);

        // Run code under test to demonstrate change
        assertEquals(0, computer.memory.read(55 + 20));
        computer.executeInstruction(instruction);
        assertEquals(55, computer.memory.read(55 + 20));
    }

    @Test
    void execute_jz() {
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
        assertEquals(computer.cpu.regfile.getPC().read(), 0);
        computer.executeInstruction(instruction);
        assertEquals(20, computer.cpu.regfile.getPC().read());
    }
}