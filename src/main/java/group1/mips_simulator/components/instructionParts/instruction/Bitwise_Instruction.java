package group1.mips_simulator.components.instructionParts.instruction;

import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.OpCode;

import java.util.Vector;

public class Bitwise_Instruction extends Instruction {
    public Bitwise_Instruction(OpCode opCode, Vector<Field> fields) {
        super(opCode, fields);
        if (fields.size() != 5) {
            throw new IllegalArgumentException("Bitwise Instruction must have 5 fields (4 + 1 blank). Instead got: " + fields.size());
        }
    }

    public Field getR() {
        return this.fields.get(0);
    }

    public Field getArithmeticLogical() {
        return this.fields.get(1);
    }
    public Field getLeftRight() {
        return this.fields.get(2);
    }
    public Field getCount() {
        return this.fields.get(4);
    }
}
