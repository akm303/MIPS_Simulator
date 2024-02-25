package group1.mips_simulator.components;

import group1.mips_simulator.components.cpuParts.CPU;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Instruction;
import group1.mips_simulator.components.memParts.Memory;
import group1.mips_simulator.components.ComputerConfig;


import java.util.Vector;

/**
 * A Mips Computer is a class to represent the classical computer architecture
 * being discussed in class.
 */
public class Computer {

    public Memory mem;
    public CPU cpu;

    public ROM readOnlyMemory = new ROM();

    public Computer() {
        cpu = new CPU();
        mem = new Memory(ComputerConfig.memorySize);
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

    public void executeInstruction(Instruction instruction) {
        // TODO:
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
