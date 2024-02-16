package group1.mips_simulator.model;

import group1.mips_simulator.model.instruction.Instruction;

import java.util.Vector;

/**
 * A Mips Computer is a class to represent the classical computer architecture
 * being discussed in class.
 */
public class MipsComputer {

    public Memory memory;
    public Vector<Register> registers;
    public Register programCounter;

    public MipsComputer() {
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
