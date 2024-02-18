package group1.mips_simulator.components;

/**
 * A Memory class represents the entire memory lookup for our computer simulator.
 */
public class Memory {

    public short read(Value address) {
        return read(address.get());
    }

    public short read(int address) {
        return read((short)address);
    }

    public short read(short address) {
        // todo
        return 0;
    }

    public void write(Value address, Value valueToWrite) {
        // todo;
    }
}
