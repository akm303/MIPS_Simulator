package group1.mips_simulator.components.instructionParts.instruction;

import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.OpCode;

import java.util.Vector;

public class IO_Instruction extends Instruction {
    public IO_Instruction(OpCode opCode, Vector<Field> fields) {
        super(opCode, fields);
        if (fields.size() != 3) {
            throw new IllegalArgumentException("IO Instruction must have 3 fields. Instead got: " + fields.size());
        }
    }

    public Field getR() {
        return this.fields.get(0);
    }

    public Field getRegister() {
        return this.getR();
    }

    public Field getDeviceID() {
        return this.fields.get(2);
    }

    public Field getDevID() {
        return this.getDeviceID();
    }
}
