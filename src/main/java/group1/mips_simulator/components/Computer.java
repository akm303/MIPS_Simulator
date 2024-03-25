package group1.mips_simulator.components;


import group1.mips_simulator.components.cpuParts.CPU;
import group1.mips_simulator.components.cpuParts.RomLoader;
import group1.mips_simulator.components.instructionExecution.ExecutionResult;
import group1.mips_simulator.components.instructionExecution.InstructionExecutions;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.instruction.Instruction;
import group1.mips_simulator.components.instructionParts.instruction.InstructionFactory;
import group1.mips_simulator.components.instructionParts.instruction.RXIA_Instruction;
import group1.mips_simulator.components.instructionParts.instruction.Reg2RegInstruction;
import group1.mips_simulator.components.memParts.Memory;


/**
 * A Mips Computer is a class to represent the classical computer architecture
 * being discussed in class.
 */
public class Computer {

    public Memory memory;
    public CPU cpu;
    public ROM rom = new ROM();

    public Computer() {
        cpu = new CPU();
        memory = new Memory(Config.MEM_SIZE);
    }

    public void loadROM(ROM rom_) {
        rom = rom_;
        cpu.romLoader = new RomLoader();
        cpu.romLoader.load(rom, memory);
    }

    /**
     * Uses the current Program Counter to read an instruction from Memory
     * Executes that Instruction (which will probably change the PC and other
     * registers/ memory)
     * Returns TRUE meaning that the program may safely continue.
     * Returning FALSE means a HALT or some other error has occurred and the
     * program must stop.
     *
     * @return True if the instruction executed successfully and is not a HALT.
     * False  =>  program should stop.
     */
    public boolean runCurrentPC() {
        // Get instruction from memory (specified by the Program Counter)
        short pcAddress = this.cpu.regfile.getPC().read();

        // Load mem address into IR
        short memoryContents = this.memory.read(pcAddress);
        this.cpu.regfile.getIR().write(memoryContents);

        // Convert IR into Instruction object
        InstructionFactory factory = new InstructionFactory();
        Instruction nextInstruction = factory.buildInstruction_fromShort(this.cpu.regfile.getIR().read());

        try {
            System.out.println("Executing instruction: " + nextInstruction.toString_Binary());
            return this.executeInstruction(nextInstruction);
        } catch (IllegalArgumentException e) {
            // If we run into some issue while running the program
            // For example if we see an unknown instruction
            // TODO: Consider printing the error to the front panel?
            System.out.println("Encountered an error when running instruction: " + e);
            return false; // Signal the computer can NOT continue running instructions
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
     * @param instruction The instruction to run.
     */
    public boolean executeInstruction(Instruction instruction) {
        InstructionExecutions exe = new InstructionExecutions();
        System.out.println("Running instruction op code: " + instruction.opCode.name);
        ExecutionResult executionResult = switch (instruction.opCode.name.toLowerCase()) {
            // Miscellaneous Instructions
            case "hlt" -> exe.execute_hlt(this, instruction); // Halt instructions stop machine
            case "trap" -> exe.execute_trap(this, instruction);
            // Load/Store Instructions
            case "ldr" -> exe.execute_ldr(this, (RXIA_Instruction) instruction);
            case "str" -> exe.execute_str(this, (RXIA_Instruction) instruction);
            case "lda" -> exe.execute_lda(this, (RXIA_Instruction) instruction);
            case "ldx" -> exe.execute_ldx(this, (RXIA_Instruction) instruction);
            case "stx" -> exe.execute_stx(this, (RXIA_Instruction) instruction);
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
            case "mlt" -> exe.execute_mlt(this, (Reg2RegInstruction) instruction);
            case "dvd" -> exe.execute_DVD(this, (Reg2RegInstruction) instruction);
            case "trr" -> exe.execute_trr(this, (Reg2RegInstruction) instruction);
            case "and" -> exe.execute_and(this, (Reg2RegInstruction) instruction);
            case "orr" -> exe.execute_orr(this, (Reg2RegInstruction) instruction);
            case "not" -> exe.execute_not(this, (Reg2RegInstruction) instruction);
            // Shift/Rotate Operations
            // TODO
            // I/O Operations
            // TODO
            // Floating Point Instructions/ Vector Operations
            // TODO
            default ->
                    throw new IllegalArgumentException("Unknown instruction op code name: " + instruction.opCode.name +
                            "\nInstruction: " + instruction);
        };

        // Set the PC to whatever the instruction results dictates
        this.cpu.regfile.getPC().write(executionResult.newPC);

        // Return if the instruction is HLT, or an error has occurred, or whatever other reason
        // to signal the stop of the computer
        return executionResult.shouldContinue;
    }

    public short currentPC() {
        return this.cpu.regfile.getPC().read();
    }

    public short currentPcPlus1() {
        return (short) (this.currentPC() + 1);
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
    public short calculateEA(RXIA_Instruction instruction) {
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
    public short calculateEA(Field ix, Field address) {
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
    public short calculateEA(Field ix, Field address, Field i) {
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
                return address.value;
            } else {
                // EA = c(IX) + c(Address Field)
                // that is, the IX field has an
                // index register number, the contents of that register are
                // added to the contents of the address field
                short ixContents = this.cpu.regfile.getIXR(ix.value).read();
                short addressField = address.value;
                return (short) (ixContents + addressField);
            }
        } else {
            // Indirection bit is set
            if (ix.value == 0) {
                // EA = c(c(Address Field))
                // It helps to think in terms of a
                //pointer where the address field has the location of the EA
                //in memory
                // both indirect addressing and indexing
                return memory.read(address.value);
            } else {
                // c(c(IX) + c(Address Field))
                short ixContents = this.cpu.regfile.getIXR(ix.value).read();
                short addressField = address.value;
                return memory.read((short) (ixContents + addressField));
            }
        }
    }
}
