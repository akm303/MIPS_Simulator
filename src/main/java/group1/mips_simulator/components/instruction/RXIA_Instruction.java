package group1.mips_simulator.components.instruction;

import java.util.Vector;

public class RXIA_Instruction extends Instruction {

    public RXIA_Instruction(OpCode opCode, Vector<Field> fields) {
        super(opCode, fields);

    }

    public Field getR() {
        return this.getIndexRegister();
    }

    public Field getGeneralPurposeRegister() {
        return this.fields.get(0);
    }

    public Field getIX() {
        return this.getIndexRegister();
    }

    public Field getIndexRegister() {
        return this.fields.get(1);
    }

    public Field getI() {
        return this.getIndirectionBit();
    }

    public Field getIndirectionBit() {
        return this.fields.get(2);
    }

    public Field getA() {
        return this.getAddress();
    }

    public Field getAddress() {
        return this.fields.get(3);
    }
}
