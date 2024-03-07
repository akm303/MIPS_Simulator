package group1.mips_simulator.components.instructionParts.instruction;

import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.instructionParts.OpCode;
import group1.mips_simulator.components.instructionParts.instruction.Instruction;

import java.util.Vector;

/**
 * An RXIA Instruction is a specific type of Instruction, where there
 * are 4 expected fields:
 * - General Purpose Register (2 bits)
 * - Index Register (2 bits)
 * - Indirection (1 bit)
 * - Address (5 bits)
 * <p>
 * This class is just convince wrapper around an Instruction type.
 */
public class RXIA_Instruction extends Instruction {

    public RXIA_Instruction(OpCode opCode, Vector<Field> fields) {
        super(opCode, fields);
        if (fields.size() != 4) {
            throw new IllegalArgumentException("RXIA Instruction must have 4 fields. Instead got: " + fields.size());
        }
    }

    public Field getR() {
        return this.getGeneralPurposeRegister();
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
