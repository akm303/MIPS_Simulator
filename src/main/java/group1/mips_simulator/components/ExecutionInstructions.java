package group1.mips_simulator.components;

import group1.mips_simulator.components.instruction.Instruction;
import group1.mips_simulator.components.instruction.RXIA_Instruction;

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
        computer.conditionCode.eBit = (regContents.get() == 0);
    }

    /**
     * JZ x, address[,I]
     * 06(octal)
     * Jump If Zero:
     * If the E bit of  condition code is 1, then PC <− EA
     * Else PC <- PC+1
     */
    public void execute_jz(MipsComputer computer, RXIA_Instruction i) {
        if (computer.conditionCode.eBit) {
            // if eBit is 1
            // PC <-- EA

        }
        // else PC <-- PC+1
    }

}
