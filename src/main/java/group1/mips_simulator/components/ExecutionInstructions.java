package group1.mips_simulator.components;

import group1.mips_simulator.components.cpuParts.ConditionCode;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.RXIA_Instruction;

/**
 * A class that holds all the functions for actually executing an Instruction object
 */
public class ExecutionInstructions {

    /**
     * SETCCE r
     * opcode 44(octal)
     * If c(r) = 0, the E bit of the condition code is set to 1,
     * else the E bit of the condition code is set to 0
     */
    public void execute_setcce(Computer computer, RXIA_Instruction i) {
        Register targetR = computer.cpu.regfile.getGPR(i.getR().value);
        short regContents = targetR.read();
        boolean newEBit = (regContents == 0); // if c(r) == 0, then newEBit is true (1).
        computer.cpu.regfile.getCC().setBit(ConditionCode.EBIT_INDEX, newEBit);
    }

    /**
     * JZ x, address[,I]
     * 06(octal)
     * Jump If Zero:
     * If the E bit of  condition code is 1, then PC <− EA
     * Else PC <- PC+1
     */
    public void execute_jz(Computer computer, RXIA_Instruction i) {
        boolean eBit = computer.cpu.regfile.getCC().getEBit();
        if (eBit) {
            // if eBit is 1
            // PC <-- EA
            Value ea = computer.calculateEA(i);
            computer.cpu.regfile.getPC().write(ea);
            return;
        }
        // else PC <-- PC+1
        computer.incrementPC();
    }

    /**
     * JNE r, x, address[,I]
     * 07(octal)
     * Jump If Not Equal:
     * If E bit of condition code is 0, then PC <−- EA
     * Else PC <- PC + 1
     */
    public void execute_jne(Computer computer, RXIA_Instruction i) {
        boolean eBit = computer.cpu.regfile.getCC().getEBit();
        if (!eBit) {
            // if eBit is 0
            // PC <-- EA
            Value ea = computer.calculateEA(i);
            computer.cpu.regfile.getPC().write(ea);
            return;
        }
        // else PC <-- PC+1
        computer.incrementPC();
    }

    /**
     * JCC cc, x, address[,I]
     * 10(octal)
     * Jump If Condition Code
     * cc replaces r for this instruction
     * cc takes values 0, 1, 2, 3 as above and specifies the bit in the Condition Code Register to check;
     * If cc bit  = 1, PC <− EA
     * Else PC <- PC + 1
     */
    public void execute_jcc(Computer computer, RXIA_Instruction i) {
        boolean targetCcBit = computer.cpu.regfile.getCC().getBit(i.getR().value);
        if (targetCcBit) {
            // if cc bit = 1
            // PC <-- EA
            Value ea = computer.calculateEA(i);
            computer.cpu.regfile.getPC().write(ea);
            return;
        }
        // else PC <-- PC+1
        computer.incrementPC();
    }

    /**
     * JMA x, address[,I]
     * 11(octal)
     * Unconditional Jump To Address
     * PC <- EA,
     * Note: r is ignored in this instruction
     */
    public void execute_jma(Computer computer, RXIA_Instruction i) {
        Value ea = computer.calculateEA(i);
        computer.cpu.regfile.getPC().write(ea);
    }

    /**
     * JSR x, address[,I]
     * 12(octal)
     * Jump and Save Return Address:
     * R3 <− PC+1;
     * PC <− EA
     * R0 should contain pointer to arguments
     * Argument list should end with –1 (all 1s) value
     */
    public void execute_jsr(Computer computer, RXIA_Instruction i) {
        // R3 <− PC+1;
        Register r = computer.cpu.regfile.getGPR(3);
        short pcPlus1 = (short) (computer.cpu.regfile.getPC().read() + 1);
        r.write(new Value(pcPlus1));

        // PC <− EA
        Value ea = computer.calculateEA(i);
        computer.cpu.regfile.getPC().write(ea);

        // TODO:
        // R0 should contain pointer to arguments
        // Argument list should end with –1 (all 1s) value
    }

    /**
     * RFS Immed
     * Immed <=> address
     * <p>
     * 13(octal)
     * Return From Subroutine w/ return code as Immed portion (optional)
     * stored in the instruction’s address field.
     * R0 <− Immed; PC <− c(R3)
     * IX, I fields are ignored.
     */
    public void execute_rfs(Computer computer, RXIA_Instruction i) {
        Field immed = i.getAddress();

        // R0 <− Immed
        Register r0 = computer.cpu.regfile.getGPR(0);
        r0.write(new Value(immed.value));
        computer.cpu.regfile.getGPR(0).write(r0.read());

        // PC <- c(R3)
        Register r3 = computer.cpu.regfile.getGPR(3);
        computer.cpu.regfile.getPC().write(r3.read());
    }

    /**
     * SOB r, x, address[,I]
     * 14(octal)
     * Subtract One and Branch. R = 0..3
     * r <− c(r) – 1
     * If c(r) > 0,  PC <- EA;
     * Else PC <- PC + 1
     */
    public void execute_sob(Computer computer, RXIA_Instruction i) {
        // r <− c(r) – 1
        // Contents of register r, minus 1
        int r = computer.cpu.regfile.getGPR(i.getR().value).read() - 1;

        // If c(r) > 0,  PC <- EA;
        Register targetReg = computer.cpu.regfile.getGPR(r);
        if (targetReg.read() > 0) {
            Value ea = computer.calculateEA(i);
            computer.cpu.regfile.getPC().write(ea);
            return;
        }
        // Else PC <- PC + 1
        computer.incrementPC();
    }

    /**
     * JGE r,x, address[,I]
     * 15(octal)
     * Jump Greater Than or Equal To:
     * If c(r) >= 0, then PC <- EA
     * Else PC <- PC + 1
     */
    public void execute_jge(Computer computer, RXIA_Instruction i) {
        // If c(r) >= 0
        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        if (targetReg.read() >= 0) {
            // then PC <- EA
            Value ea = computer.calculateEA(i);
            computer.cpu.regfile.getPC().write(ea);
            return;
        }
        // Else PC <- PC + 1
        computer.incrementPC();
    }

}
