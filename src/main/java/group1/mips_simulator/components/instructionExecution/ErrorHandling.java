package group1.mips_simulator.components.instructionExecution;

import group1.mips_simulator.components.Computer;

public class ErrorHandling {

    public void resetCC(Computer computer) {
        computer.cpu.regfile.getCC().setBit(0, false);
        computer.cpu.regfile.getCC().setBit(1, false);
        computer.cpu.regfile.getCC().setBit(2, false);
        computer.cpu.regfile.getCC().setBit(3, false);
    }

    public short detectOverUnderflow(Computer computer, int value) {
        if (value > Short.MAX_VALUE) {
            // Overflow detected
            computer.cpu.regfile.getCC().setBit(0, true);
            value = (short)value; // Truncate
        } else if (value < Short.MIN_VALUE) {
            // Underflow detected
            computer.cpu.regfile.getCC().setBit(1, true);
            value = (short)value; // Truncate
        }
        return (short)value;
    }

}
