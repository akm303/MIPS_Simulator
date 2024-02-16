package group1.mips_simulator.model.instruction;

import java.util.Vector;

public class Instruction {

    public Instruction(OpCode opCode, Vector<Field> fields) {
        this.opCode = opCode;
        this.fields = fields;
    }

    public OpCode opCode;
    public Vector<Field> fields;

    @Override
    public String toString() {
        return "Instruction{" +
                "opCode=" + opCode +
                ", fields=" + fields +
                '}';
    }
}
