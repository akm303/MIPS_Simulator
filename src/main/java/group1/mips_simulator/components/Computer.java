package group1.mips_simulator.components;

import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Instruction;
import group1.mips_simulator.components.memParts.Memory;

import java.util.Vector;

/**
 * A Mips Computer is a class to represent the classical computer architecture
 * being discussed in class.
 */
public class Computer {

    public Memory memory;
    public Vector<Register> registers;
    public Register programCounter;

    public Computer() {
    }

    public void executeProgram(Vector<Instruction> program) {
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
        Value pc = this.programCounter.read();
        pc.set(pc.get() + 1);
        this.programCounter.set(pc);
    }
}
