package group1.mips_simulator.components.instructionParts.instruction;

import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.OpCode;

import java.util.Vector;

public class Reg2RegInstruction extends Instruction {
    public Reg2RegInstruction(OpCode opCode, Vector<Field> fields) {
        super(opCode, fields);
        if (fields.size() != 3) {
            throw new IllegalArgumentException("Register to Register Instruction must have 3 fields (2 reg + 1 blank). Instead got: " + fields.size());
        }
    }

    public Field getRX() {
        return super.fields.get(0);
    }
    public Field getRY() {
        return super.fields.get(1);
    }
}
