package group1.mips_simulator.components;

import group1.mips_simulator.components.cpuParts.ConditionCode;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.RXIA_Instruction;

/**
 * A class that holds all the functions for actually executing an Instruction object
 */
public class InstructionExecutions {

    /**
     * L0DR r, x, address[,I]
     * opcode 01(octal)
     * Load Register From Memory, r = 0..3
     * r <− c(EA)
     * note that EA is computed as given above
     */
    public void execute_ldr(Computer computer, RXIA_Instruction i) {
        // r <- c(EA)
        short ea = computer.calculateEA(i);
        short contentsEA = computer.memory.read(ea);

        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        targetReg.write(contentsEA);
    }

    /**
     * STR r, x, address[,I]
     * opcode 02(octal)
     * Store Register To Memory, r = 0..3
     * Memory(EA) <− c(r)
     */
    public void execute_str(Computer computer, RXIA_Instruction i) {
        // Memory(EA) <− c(r)
        short ea = computer.calculateEA(i);

        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        short contentsReg = targetReg.read();

        computer.memory.write(ea, contentsReg);
    }

    /**
     * LDA r, x, address[,I]
     * opcode 03(octal)
     * Load Register with Address, r = 0..3
     * r <− EA
     */
    public void execute_lda(Computer computer, RXIA_Instruction i) {
        // r <− EA
        short ea = computer.calculateEA(i);
        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);

        targetReg.write(ea);
    }

    /**
     * LDX x, address[,I]
     * opcode 04(octal)
     * Load Index Register from Memory, x = 1..3
     * Xx <- c(EA)
     */
    public void execute_ldx(Computer computer, RXIA_Instruction i) {
        // Xx <- c(EA)
        short ea = computer.calculateEA(i);
        short contentsEA = computer.memory.read(ea);
        Register targetReg = computer.cpu.regfile.getIXR(i.getR().value);

        targetReg.write(contentsEA);
    }

    /**
     * STX x, address[,I]
     * opcode 05(octal)
     * Store Index Register to Memory. X = 1..3
     * Memory(EA) <- c(Xx)
     */
    public void execute_stx(Computer computer, RXIA_Instruction i) {
        // Memory(EA) <- c(Xx)
        short ea = computer.calculateEA(i);

        Register targetReg = computer.cpu.regfile.getIXR(i.getR().value);
        short contentsReg = targetReg.read();

        computer.memory.write(ea, contentsReg);
    }

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
            short ea = computer.calculateEA(i);
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
            short ea = computer.calculateEA(i);
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
            short ea = computer.calculateEA(i);
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
        short ea = computer.calculateEA(i);
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
        short ea = computer.calculateEA(i);
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
            short ea = computer.calculateEA(i);
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
            short ea = computer.calculateEA(i);
            computer.cpu.regfile.getPC().write(ea);
            return;
        }
        // Else PC <- PC + 1
        computer.incrementPC();
    }

}
