package group1.mips_simulator.components.instructionExecution;


import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Computer;
import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.cpuParts.ConditionCode;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.instruction.Bitwise_Instruction;
import group1.mips_simulator.components.instructionParts.instruction.Instruction;
import group1.mips_simulator.components.instructionParts.instruction.RXIA_Instruction;
import group1.mips_simulator.components.instructionParts.instruction.Reg2RegInstruction;

/**
 * A class that holds all the functions for actually executing an Instruction object
 */
public class InstructionExecutions {

    ErrorHandling errorHandling = new ErrorHandling();

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
        short contentsEA = computer.memory.read(ea);

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

        computer.memory.write(ea, contentsReg);
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
        short contentsEA = computer.memory.read(ea);
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

        computer.memory.write(ea, contentsReg);
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
     * MLT rx,ry
     * 22(oct)
     * Multiply Register by Register
     * rx, rx+1 <- c(rx) * c(ry)
     * rx must be 0 or 2
     * ry must be 0 or 2
     * rx contains the high order bits, rx+1 contains the low order bits of the result
     * Set OVERFLOW flag, if overflow
     */
    public ExecutionResult execute_mlt(Computer computer, Reg2RegInstruction i) {
        // Reset the condition code overflow bit
        computer.cpu.regfile.getCC().setOverflowBit(false);

        // rx must be 0 or 2
        // ry must be 0 or 2
        short rxIndex = i.getRX().value;
        short ryIndex = i.getRY().value;
        if ((rxIndex != 0) && (rxIndex != 2)) {
            System.out.println("MLT instruction expected the RX to be 0 or 2, instead got: " + rxIndex);
            return new ExecutionResult(computer.currentPC(), false);
        }
        if ((ryIndex != 0) && (ryIndex != 2)) {
            System.out.println("MLT instruction expected the RY to be 0 or 2, instead got: " + ryIndex);
            return new ExecutionResult(computer.currentPC(), false);
        }

        Register regX = computer.cpu.regfile.getGPR(rxIndex);
        Register regY = computer.cpu.regfile.getGPR(ryIndex);

        // c(rx) * c(ry)
        long newValue = (long) regX.read() * (long) regY.read();

        String newValueBinaryStr = Utility.intTo32BitString((int) newValue);

        // Top 16 bits go into RegX
        // Bottom 16 bits go into RegY
        String top16Bits = newValueBinaryStr.substring(0, 16);
        String bot16Bits = newValueBinaryStr.substring(16, 32);

        Register regXPlus1 = computer.cpu.regfile.getGPR(rxIndex + 1);
        regX.write(Utility.binaryToShort(top16Bits));
        regXPlus1.write(Utility.binaryToShort(bot16Bits));

        // Set OVERFLOW flag, if overflow
        if (!top16Bits.equals("0000000000000000")) {
            // If the largest 16 bits are NOT all 0 then overflow
            computer.cpu.regfile.getCC().setOverflowBit(true);
        }

        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * DVD rx,ry
     * 23(oct)
     * Multiply Register by Register
     * rx, rx+1 <- c(rx) / c(ry)
     * rx must be 0 or 2
     * ry must be 0 or 2
     * rx contains the high order bits, rx+1 contains the low order bits of the result
     * If c(ry) = 0, set cc(3) to 1 (set DIVZERO flag)
     */
    public ExecutionResult execute_DVD(Computer computer, Reg2RegInstruction i) {
        // Reset the div by 0 bit
        computer.cpu.regfile.getCC().setDivideByZeroBit(false);

        // rx must be 0 or 2
        // ry must be 0 or 2
        short rxIndex = i.getRX().value;
        short ryIndex = i.getRY().value;
        if ((rxIndex != 0) && (rxIndex != 2)) {
            System.out.println("DVD instruction expected the RX to be 0 or 2, instead got: " + rxIndex);
            return new ExecutionResult(computer.currentPC(), false);
        }
        if ((ryIndex != 0) && (ryIndex != 2)) {
            System.out.println("DVD instruction expected the RY to be 0 or 2, instead got: " + ryIndex);
            return new ExecutionResult(computer.currentPC(), false);
        }

        Register regX = computer.cpu.regfile.getGPR(rxIndex);
        Register regY = computer.cpu.regfile.getGPR(ryIndex);

        if (regY.read() == 0) {
            System.out.println("DVD instruction Divide by zero detected. Halting");
            computer.cpu.regfile.getCC().setDivideByZeroBit(true);
            return new ExecutionResult(computer.currentPC(), false);
        }

        // c(rx) * c(ry)
        int newValue = regX.read() * regY.read();
        String newValueBinaryStr = Utility.intTo32BitString(newValue);

        // Top 16 bits go into RegX
        // Bottom 16 bits go into RegY
        String top16Bits = newValueBinaryStr.substring(0, 16);
        String bot16Bits = newValueBinaryStr.substring(16, 32);

        Register regXPlus1 = computer.cpu.regfile.getGPR(rxIndex + 1);
        regX.write(Utility.binaryToShort(top16Bits));
        regXPlus1.write(Utility.binaryToShort(bot16Bits));

        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * TRR rx, ry
     * 24(oct)
     * Test the Equality of Register and Register
     * If c(rx) = c(ry), set cc(4) <− 1; else, cc(4) <− 0
     */
    public ExecutionResult execute_trr(Computer computer, Reg2RegInstruction i) {
        Register regX = computer.cpu.regfile.getGPR(i.getRX().value);
        Register regY = computer.cpu.regfile.getGPR(i.getRY().value);

        boolean isEqual = (regX.read() == regY.read());
        computer.cpu.regfile.getCC().setEqualityBit(isEqual);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * AND rx, ry
     * 25(oct)
     * Logical And of Register and Register
     * c(rx) <− c(rx) AND c(ry)
     */
    public ExecutionResult execute_and(Computer computer, Reg2RegInstruction i) {
        Register regX = computer.cpu.regfile.getGPR(i.getRX().value);
        Register regY = computer.cpu.regfile.getGPR(i.getRY().value);

        short newValue = (short) (regX.read() & regY.read());
        regX.write(newValue);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * OR rx, ry
     * 26(oct)
     * Logical Or of Register and Register
     * c(rx) <− c(rx) OR c(ry)
     */
    public ExecutionResult execute_orr(Computer computer, Reg2RegInstruction i) {
        Register regX = computer.cpu.regfile.getGPR(i.getRX().value);
        Register regY = computer.cpu.regfile.getGPR(i.getRY().value);

        short newValue = (short) (regX.read() | regY.read());
        regX.write(newValue);
        return new ExecutionResult(computer.currentPcPlus1());
    }


    /**
     * NOT rx
     * 27(oct)
     * Logical Not of Register and Register
     * c(rx) <− NOT c(rx)
     */
    public ExecutionResult execute_not(Computer computer, Reg2RegInstruction i) {
        Register regX = computer.cpu.regfile.getGPR(i.getRX().value);

        // Convert short to string
        // Then apply bitwise NOT on that string
        // Then convert that back into short
        String newValueString = Utility.binaryNot(Utility.shortToBinaryString(regX.read(), 16));
        regX.write(Utility.binaryToShort(newValueString));
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
        computer.memory.write((short) 2, pcPlus1);

        // Traps to mem address 0
        short tableAddress = computer.memory.read((short) 0);

        // Trap code = index into table
        Field blank = i.fields.get(0);
        Field code = i.fields.get(1);

        // The target address in memory becomes the new Program Counter.
        // It's expected the developer has loaded in a program that starts at the target address
        // that's for this particular trap code.
        short targetAddress = (short) (tableAddress + code.value);
        return new ExecutionResult(targetAddress);
    }

    /**
     * AMR r, x, address[,I]
     * 16(octal)
     * Add Memory To Register, r = 0..3
     * r<− c(r) + c(EA)
     */
    public ExecutionResult execute_amr(Computer computer, RXIA_Instruction i) {
        errorHandling.resetCC(computer);

        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        short ea = computer.calculateEA(i);
        short newValue = errorHandling.detectOverUnderflow(computer, targetReg.read() + ea);
        targetReg.write(newValue);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * SMR r, x, address[,I]
     * 17(octal)
     * Subtract Memory From Register, r = 0..3
     * r <− c(r) – c(EA)
     */
    public ExecutionResult execute_smr(Computer computer, RXIA_Instruction i) {
        errorHandling.resetCC(computer);

        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        short ea = computer.calculateEA(i);
        short newValue = errorHandling.detectOverUnderflow(computer, targetReg.read() - ea);
        targetReg.write(newValue);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * AIR r, immed
     * 17(octal)
     * Subtract Memory From Register, r = 0..3
     * r <− c(r) – c(EA)
     */
    public ExecutionResult execute_air(Computer computer, RXIA_Instruction i) {
        errorHandling.resetCC(computer);

        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        short immediate = i.getA().value; //the Address portion is considered to be the Immediate value
        short newValue = errorHandling.detectOverUnderflow(computer, targetReg.read() + immediate);
        targetReg.write(newValue);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * SIR r, immed
     * 17(octal)
     * Subtract Memory From Register, r = 0..3
     * r <− c(r) – c(EA)
     */
    public ExecutionResult execute_sir(Computer computer, RXIA_Instruction i) {
        errorHandling.resetCC(computer);

        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);
        short immediate = i.getA().value; //the Address portion is considered to be the Immediate value
        short newValue = errorHandling.detectOverUnderflow(computer, targetReg.read() - immediate);
        targetReg.write(newValue);
        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * SRC r, count, L/R, A/L
     * 30(octal)
     * Shift Register by Count
     * c(r) is shifted left (L/R =1) or right (L/R = 0) either logically (A/L = 1) or arithmetically (A/L = 0)
     * XX, XXX are ignored
     * Count = 0…15
     * If Count = 0, no shift occurs
     */
    public ExecutionResult execute_src(Computer computer, Bitwise_Instruction i) {
        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);

        boolean shiftLeft = (i.getLeftRight().value == 1);
        boolean shiftLogically = (i.getArithmeticLogical().value == 1);
        short count = i.getCount().value;

        short value = targetReg.read();
        if (shiftLeft) {
            value = (short) (value << count);
        } else {
            if (shiftLogically) {
                value = (short) (value >>> count); // Signed
            } else {
                // arithmetic
                value = (short) (value >> count); // Unsigned
            }
        }
        targetReg.write(value);

        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * RRC r, count, L/R, A/L
     * 31(octal)
     * Rotate Register by Count
     * c(r) is rotated left (L/R = 1) or right (L/R =0) either logically (A/L =1)
     * XX, XXX is ignored
     * Count = 0…15
     * If Count = 0, no rotate occurs
     */
    public ExecutionResult execute_rrc(Computer computer, Bitwise_Instruction i) {
        Register targetReg = computer.cpu.regfile.getGPR(i.getR().value);

        boolean shiftLeft = (i.getLeftRight().value == 1);
        boolean shiftLogically = (i.getArithmeticLogical().value == 1);
        short count = i.getCount().value;

        String valueAsStr = targetReg.toString_Binary().replace("_", "");

        if (shiftLeft) {
            for (int j = 0; j < count; j++) {
                valueAsStr = Utility.rotateLeftOne(valueAsStr);
            }
        } else {
            // shift right
            for (int j = 0; j < count; j++) {
                valueAsStr = Utility.rotateRightOne(valueAsStr);
            }
        }

        short newValue = Utility.binaryToShort(valueAsStr);
        targetReg.write(newValue);

        return new ExecutionResult(computer.currentPcPlus1());
    }
}
