package group1.mips_simulator.components;

import group1.mips_simulator.components.cpuParts.CPU;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Instruction;
import group1.mips_simulator.components.memParts.Memory;


import java.util.Vector;

/**
 * A Mips Computer is a class to represent the classical computer architecture
 * being discussed in class.
 */
public class Computer {

    public Memory mem;
    public CPU cpu;
    public ROM rom = new ROM();

    public Computer() {
        cpu = new CPU();
        mem = new Memory(Config.memorySize);
    }

    /**
     * Uses the current Program Counter to read an instruction from Memory
     * Executes that Instruction (which will probably change the PC and other
     * registers/ memory)
     * Returns TRUE meaning that the program may safely continue.
     * Returning FALSE means a HALT or some other error has occurred and the
     * program must stop.
     * @return True if the instruction executed successfully and is not a HALT.
     * False  =>  program should stop.
     */
    public boolean runCurrentPC() {
        // Get instruction from memory (specified by the Program Counter)
        Value pcAddress = this.cpu.regfile.getPC().get();
        Instruction nextInstruction = Instruction.buildInstruction_fromShort(this.mem.read(pcAddress));
        return this.executeInstruction(nextInstruction);
    }

    public void executeProgram(Vector<Instruction> program) {
        // IF -> ID -> EX -> ME -> WB
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
     * Runs the provided instruction against this Computer.
     * If the instruction results in a HALT then a false will be returned.
     * @param instruction
     * @return
     */
    public boolean executeInstruction(Instruction instruction) {
        // TODO:
        return true;
    }

    public void incrementPC() {
        Register pc = this.cpu.regfile.getPC();
        pc.increment();
    }
//    public void incrementPC() {
//        Value pc = this.programCounter.read();
//        pc.set(pc.get() + 1);
//        this.programCounter.write(pc);
//    }
}
