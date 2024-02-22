package group1.mips_simulator.components;

import group1.mips_simulator.components.instruction.Field;
import group1.mips_simulator.components.instruction.RXIA_Instruction;

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
    public void execute_setcce(MipsComputer computer, RXIA_Instruction i) {
        Register targetR = computer.registers.get(i.getR().value);
        Value regContents = targetR.read();
        computer.conditionCode.setBit(ConditionCode.EBIT_INDEX, regContents.get() == 0);
    }

    /**
     * JZ x, address[,I]
     * 06(octal)
     * Jump If Zero:
     * If the E bit of  condition code is 1, then PC <− EA
     * Else PC <- PC+1
     */
    public void execute_jz(MipsComputer computer, RXIA_Instruction i) {
        if (computer.conditionCode.getEBit()) {
            // if eBit is 1
            // PC <-- EA
            Value ea = computer.calculateEA(i);
            computer.programCounter.set(ea);
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
    public void execute_jne(MipsComputer computer, RXIA_Instruction i) {
        if (!computer.conditionCode.getEBit()) {
            // if eBit is 0
            // PC <-- EA
            Value ea = computer.calculateEA(i);
            computer.programCounter.set(ea);
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
    public void execute_jcc(MipsComputer computer, RXIA_Instruction i) {
        boolean targetCcBit = computer.conditionCode.getBit(i.getR().value);
        if (targetCcBit) {
            // if cc bit = 1
            // PC <-- EA
            Value ea = computer.calculateEA(i);
            computer.programCounter.set(ea);
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
    public void execute_jma(MipsComputer computer, RXIA_Instruction i) {
        Value ea = computer.calculateEA(i);
        computer.programCounter.set(ea);
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
    public void execute_jsr(MipsComputer computer, RXIA_Instruction i) {
        // R3 <− PC+1;
        Register r = computer.registers.get(3);
        r.set(new Value(computer.programCounter.read().get() + 1));
        computer.registers.set(3, r);

        // PC <− EA
        Value ea = computer.calculateEA(i);
        computer.programCounter.set(ea);

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
    public void execute_rfs(MipsComputer computer, RXIA_Instruction i) {
        Field immed = i.getAddress();

        // R0 <− Immed
        Register r0 = computer.registers.get(0);
        r0.set(new Value(immed.value));
        computer.registers.set(0, r0);

        // PC <- c(R3)
        Register r3 = computer.registers.get(3);
        computer.programCounter.set(r3.read());
    }

    /**
     * SOB r, x, address[,I]
     * 14(octal)
     * Subtract One and Branch. R = 0..3
     * r <− c(r) – 1
     * If c(r) > 0,  PC <- EA;
     * Else PC <- PC + 1
     */
    public void execute_sob(MipsComputer computer, RXIA_Instruction i) {
        // r <− c(r) – 1
        int r = computer.registers.get(i.getR().value).read().get() - 1;

        // If c(r) > 0,  PC <- EA;
        if (computer.registers.get(r).read().get() > 0) {
            Value ea = computer.calculateEA(i);
            computer.programCounter.set(ea);
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
    public void execute_jge(MipsComputer computer, RXIA_Instruction i) {
        // If c(r) >= 0
        Register targetReg = computer.registers.get(i.getR().value);
        if (targetReg.read().get() >= 0) {
            // then PC <- EA
            Value ea = computer.calculateEA(i);
            computer.programCounter.set(ea);
            return;
        }
        // Else PC <- PC + 1
        computer.incrementPC();
    }

}
