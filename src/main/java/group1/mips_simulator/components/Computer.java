package group1.mips_simulator.components;


import group1.mips_simulator.components.cpuParts.CPU;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.Instruction;
import group1.mips_simulator.components.instructionParts.RXIA_Instruction;
import group1.mips_simulator.components.memParts.Memory;

import java.util.Vector;

/**
 * A Mips Computer is a class to represent the classical computer architecture
 * being discussed in class.
 */
public class Computer {
/*
    public Memory memory;
    public Vector<Register> registers;
    public Vector<Register> indexRegisters;
    public Register programCounter;

    public ConditionCode conditionCode = new ConditionCode();
//    public Register programCounter;
*/

    public Memory memory;
    public CPU cpu;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
    }

    /**
     * Take a collection of Instructions and execute them all in order.
     *
     * @param program The instruction set to run
     */
    public void executeProgram(Vector<Instruction> program) {
        // IF -> ID -> Exe ->
        //
        for (Instruction i : program) {
            try {
                this.executeInstruction(i);
            } catch (Exception e) {
                System.out.println("Encountered an error when running the instruction " + i.toString() +
                        "\nError:\n" + e);
            }
        }
    }

    /**
     * Take a single instruction, and execute it on this machine.
     * Effectively, this will dispatch the instruction to a custom "execute"
     * function based on the op code. The instruction's fields will also be
     * passed, in addition to `this` MipsComputer object.
     * <p>
     * The execute function will read/ write/ update the various registers,
     * memory, pointers etc. of `this` MipsComputer instance.
     *
     * @param instruction The instructino to run.
     */
    public void executeInstruction(Instruction instruction) {
        ExecutionInstructions exe = new ExecutionInstructions();
        switch (instruction.opCode.name.toLowerCase()) {
            // Miscellaneous Instructions
            // TODO
            // Load/Store Instructions
            // TODO
            // Transfer Instructions
            case "setcce" -> exe.execute_setcce(this, (RXIA_Instruction) instruction);
            case "jz" -> exe.execute_jz(this, (RXIA_Instruction) instruction);
            case "jne" -> exe.execute_jne(this, (RXIA_Instruction) instruction);
            case "jcc" -> exe.execute_jcc(this, (RXIA_Instruction) instruction);
            case "jma" -> exe.execute_jma(this, (RXIA_Instruction) instruction);
            case "jsr" -> exe.execute_jsr(this, (RXIA_Instruction) instruction);
            case "rfs" -> exe.execute_rfs(this, (RXIA_Instruction) instruction);
            case "sob" -> exe.execute_sob(this, (RXIA_Instruction) instruction);
            case "jge" -> exe.execute_jge(this, (RXIA_Instruction) instruction);
            // Arithmetic and Logical Instructions
            // TODO
            // Register to Register Instructions
            // TODO
            // Shift/Rotate Operations
            // TODO
            // I/O Operations
            // TODO
            // Floating Point Instructions/ Vector Operations
            // TODO
            default ->
                    throw new IllegalArgumentException("Unknown instruction op code name: " + instruction.opCode.name +
                            "\nInstruction: " + instruction);
        }
        // After every instruction then we increment the Program Counter
        // EXCEPT if the instruction is a transfer instruction. Transfer instructions
        // are responsible for setting the PC themselves.
        if (!instruction.isTransferInstruction()) {
            this.incrementPC();
        }
    }

    /**
     * Update the program counter register by incrementing it to the
     * next position (+1)
     */
    public void incrementPC() {
        Register pc = this.cpu.regfile.getPC();
        pc.increment();
    }

    /**
     * The Effective Address (EA) is a memory location that considers
     * a bunch of various modifications including:
     * - Base Address
     * - Indirection Bit
     * - Index Registers
     * <p>
     * The specific instructions for calculating the EA are from the project documentation pg13
     *
     * @return The memory location that is the Effective Address for the provided
     * instruction.
     */
    public Value calculateEA(RXIA_Instruction instruction) {
        return this.calculateEA(instruction.getIX(), instruction.getAddress(), instruction.getI());
    }


    /**
     * The Effective Address (EA) is a memory location that considers
     * a bunch of various modifications including:
     * - Base Address
     * - Indirection Bit
     * - Index Registers
     * <p>
     * The specific instructions for calculating the EA are from the project documentation pg13
     *
     * @return The memory location that is the Effective Address for the provided
     * instruction.
     */
    public Value calculateEA(Field ix, Field address) {
        return this.calculateEA(ix, address, new Field(0, 1));
    }

    /**
     * The Effective Address (EA) is a memory location that considers
     * a bunch of various modifications including:
     * - Base Address
     * - Indirection Bit
     * - Index Registers
     * <p>
     * The specific instructions for calculating the EA are from the project documentation pg13
     *
     * @return The memory location that is the Effective Address for the provided
     * instruction.
     */
    public Value calculateEA(Field ix, Field address, Field i) {
        /*
        Effective Address (EA) =
        if I field = 0:
            // NO indirect addressing
            If  IX = 00
                EA = contents of the Address field       c(Address Field)
            else if IX = 1..3,
                EA = c(IX) + c(Address Field)
                // that is, the IX field has an
                //index register number, the contents of that register are
                //added to the contents of the address field
        if I field  = 1:
            // indirect addressing, but NO indexing
            if IX = 00,
                EA = c(c(Address Field))
                // It helps to think in terms of a
                //pointer where the address field has the location of the EA
                //in memory
                // both indirect addressing and indexing
            else if IX = 1..3
                c(c(IX) + c(Address Field))
        // another way to think of this is take the EA computed without indirections and use that
        // as the location of where the EA is stored.
        */

        if (i.value == 0) {
            // NO indirect addressing
            if (ix.value == 0) {
                // EA = contents of the Address field       c(Address Field)
                return new Value(address.value);
            } else {
                // EA = c(IX) + c(Address Field)
                // that is, the IX field has an
                // index register number, the contents of that register are
                // added to the contents of the address field
                short ixContents = this.cpu.regfile.getIXR(ix.value).read().get();
                short addressField = address.value;
                return new Value(ixContents + addressField);
            }
        } else {
            // Indirection bit is set
            if (ix.value == 0) {
                // EA = c(c(Address Field))
                // It helps to think in terms of a
                //pointer where the address field has the location of the EA
                //in memory
                // both indirect addressing and indexing
                short addressContents = memory.read(address.value);
                return new Value(addressContents);
            } else {
                // c(c(IX) + c(Address Field))
                short ixContents = this.cpu.regfile.getIXR(ix.value).read().get();
                short addressField = address.value;
                return new Value(memory.read((short) (ixContents + addressField)));
            }
        }
    }

}
