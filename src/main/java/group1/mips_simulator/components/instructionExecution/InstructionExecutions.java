package group1.mips_simulator.components.instructionExecution;


import group1.mips_simulator.components.Computer;
import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.cpuParts.ConditionCode;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.instruction.Instruction;
import group1.mips_simulator.components.instructionParts.instruction.RXIA_Instruction;

/**
 * A class that holds all the functions for actually executing an Instruction object
 */
public class InstructionExecutions {

    /**
     * L0DR r, x, address[,I]
     * opcode 01(octal)˜
     * Load Register From Memory, r = 0..3
     * r <− c(EA)
     * note that EA is computed as given above
     */
    public ExecutionResult execute_ldr(Computer computer, RXIA_Instruction i) {
        // r <- c(EA)
        short ea = computer.calculateEA(i);
        short contentsEA = computer.cache.read(ea);

        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        targetReg.write(contentsEA);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * STR r, x, address[,I]
     * opcode 02(octal)
     * Store Register To Memory, r = 0..3
     * Memory(EA) <− c(r)
     */
    public ExecutionResult execute_str(Computer computer, RXIA_Instruction i) {
        // Memory(EA) <− c(r)
        short ea = computer.calculateEA(i);

        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        short contentsReg = targetReg.read();

        computer.cache.write(ea, contentsReg);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * LDA r, x, address[,I]
     * opcode 03(octal)
     * Load Register with Address, r = 0..3
     * r <− EA
     */
    public ExecutionResult execute_lda(Computer computer, RXIA_Instruction i) {
        // r <− EA
        short ea = computer.calculateEA(i);
        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);

        targetReg.write(ea);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * LDX x, address[,I]
     * opcode 04(octal)
     * Load Index Register from Memory, x = 1..3
     * Xx <- c(EA)
     */
    public ExecutionResult execute_ldx(Computer computer, RXIA_Instruction i) {
        // Xx <- c(EA)
        short ea = computer.calculateEA(i);
        short contentsEA = computer.cache.read(ea);
        Register targetReg = computer.cpu.regfile.getIXR(i.getIX().value);

        targetReg.write(contentsEA);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * STX x, address[,I]
     * opcode 05(octal)
     * Store Index Register to Memory. X = 1..3
     * Memory(EA) <- c(Xx)
     */
    public ExecutionResult execute_stx(Computer computer, RXIA_Instruction i) {
        // Memory(EA) <- c(Xx)
        short ea = computer.calculateEA(i);

        Register targetReg = computer.cpu.regfile.getIXR(i.getIX().value);
        short contentsReg = targetReg.read();

        computer.cache.write(ea, contentsReg);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * SETCCE r
     * opcode 44(octal)
     * If c(r) = 0, the E bit of the condition code is set to 1,
     * else the E bit of the condition code is set to 0
     */
    public ExecutionResult execute_setcce(Computer computer, RXIA_Instruction i) {
        Register targetR = computer.cpu.regfile.getGPR(i.getR().value);
        short regContents = targetR.read();
        boolean newEBit = (regContents == 0); // if c(r) == 0, then newEBit is true (1).
        computer.cpu.regfile.getCC().setBit(ConditionCode.EBIT_INDEX, newEBit);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * JZ x, address[,I]
     * 06(octal)
     * Jump If Zero:
     * If the E bit of  condition code is 1, then PC <− EA
     * Else PC <- PC+1
     */
    public ExecutionResult execute_jz(Computer computer, RXIA_Instruction i) {
        boolean eBit = computer.cpu.regfile.getCC().getEBit();
        if (eBit) {
            // if eBit is 1
            // PC <-- EA
            short ea = computer.calculateEA(i);
            return new ExecutionResult(ea);
        }
        // else PC <-- PC+1
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * JNE r, x, address[,I]
     * 07(octal)
     * Jump If Not Equal:
     * If E bit of condition code is 0, then PC <−- EA
     * Else PC <- PC + 1
     */
    public ExecutionResult execute_jne(Computer computer, RXIA_Instruction i) {
        boolean eBit = computer.cpu.regfile.getCC().getEBit();
        if (!eBit) {
            // if eBit is 0
            // PC <-- EA
            short ea = computer.calculateEA(i);
            return new ExecutionResult(ea);
        }
        // else PC <-- PC+1
        return new ExecutionResult(computer.currentPcPlus1());
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
    public ExecutionResult execute_jcc(Computer computer, RXIA_Instruction i) {
        boolean targetCcBit = computer.cpu.regfile.getCC().getBit(i.getR().value);
        if (targetCcBit) {
            // if cc bit = 1
            // PC <-- EA
            short ea = computer.calculateEA(i);
            return new ExecutionResult(ea);
        }
        // else PC <-- PC+1
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * JMA x, address[,I]
     * 11(octal)
     * Unconditional Jump To Address
     * PC <- EA,
     * Note: r is ignored in this instruction
     */
    public ExecutionResult execute_jma(Computer computer, RXIA_Instruction i) {
        short ea = computer.calculateEA(i);
        return new ExecutionResult(ea);
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
    public ExecutionResult execute_jsr(Computer computer, RXIA_Instruction i) {
        // R3 <− PC+1;
        Register r = computer.cpu.regfile.getGPR(3);
        short pcPlus1 = (short) (computer.cpu.regfile.getPC().read() + 1);
        r.write(new Word(pcPlus1));

        // PC <− EA
        short ea = computer.calculateEA(i);
        return new ExecutionResult(ea);

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
    public ExecutionResult execute_rfs(Computer computer, RXIA_Instruction i) {
        Field immed = i.getAddress();

        // R0 <− Immed
        Register r0 = computer.cpu.regfile.getGPR(0);
        r0.write(new Word(immed.value));
        computer.cpu.regfile.getGPR(0).write(r0.read());

        // PC <- c(R3)
        Register r3 = computer.cpu.regfile.getGPR(3);
        computer.cpu.regfile.getPC().write(r3.read());

        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * SOB r, x, address[,I]
     * 14(octal)
     * Subtract One and Branch. R = 0..3
     * r <− c(r) – 1
     * If c(r) > 0,  PC <- EA;
     * Else PC <- PC + 1
     */
    public ExecutionResult execute_sob(Computer computer, RXIA_Instruction i) {
        // r <− c(r) – 1
        // Contents of register r, minus 1
        int r = computer.cpu.regfile.getGPR(i.getR().value).read() - 1;

        // If c(r) > 0,  PC <- EA;
        Register targetReg = computer.cpu.regfile.getGPR(r);
        if (targetReg.read() > 0) {
            short ea = computer.calculateEA(i);
            return new ExecutionResult(ea);
        }
        // Else PC <- PC + 1
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * JGE r,x, address[,I]
     * 15(octal)
     * Jump Greater Than or Equal To:
     * If c(r) >= 0, then PC <- EA
     * Else PC <- PC + 1
     */
    public ExecutionResult execute_jge(Computer computer, RXIA_Instruction i) {
        // If c(r) >= 0
        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        if (targetReg.read() >= 0) {
            // then PC <- EA
            short ea = computer.calculateEA(i);
            return new ExecutionResult(ea);
        }
        // Else PC <- PC + 1
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * HLT
     * 0(octal)
     * Stops the machine.
     */
    public ExecutionResult execute_hlt(Computer computer, Instruction i) {
        // No action taken
        return new ExecutionResult(computer.currentPcPlus1(), false);
    }

    /**
     * TRAP code
     * 45(cotal)
     * Traps to memory address 0, which contains the address of a table in memory.
     * Stores the PC+1 in memory location 2. The table can have a maximum of 16
     * entries representing 16 routines for user-specified instructions stored
     * elsewhere in memory. Trap code contains an index into the table, e.g. it
     * takes values 0 – 15. When a TRAP instruction is executed, it goes to the
     * routine whose address is in memory location 0, executes those instructions,
     * and returns to the instruction stored in memory location 2. The PC+1 of the
     * TRAP instruction is stored in memory location 2.
     */
    public ExecutionResult execute_trap(Computer computer, Instruction i) {
        // Stores the PC+1 in mem location 2
        short pcPlus1 = computer.currentPcPlus1();
        computer.cache.write((short) 2, pcPlus1);

        // Traps to mem address 0
        short tableAddress = computer.memory.read((short) 0); //did not replace with cache

        // Trap code = index into table
        Field blank = i.fields.get(0);
        Field code = i.fields.get(1);

        // The target address in memory becomes the new Program Counter.
        // It's expected the developer has loaded in a program that starts at the target address
        // that's for this particular trap code.
        short targetAddress = (short) (tableAddress + code.value);
        return new ExecutionResult(targetAddress);
    }
}
