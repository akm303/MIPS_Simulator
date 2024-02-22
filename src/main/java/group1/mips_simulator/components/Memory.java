package group1.mips_simulator.components;

/**
 * A Memory class represents the entire memory lookup for our computer simulator.
 */
public class Memory {

    public short read(Value address) {
        return read(address.get());
    }

    public short read(int address) {
        return read((short) address);
    }

    public short read(short address) {
        // todo
        return 0;
    }

    public void write(Value address, Value valueToWrite) {
        write(address.get(), valueToWrite.get());
    }

    public void write(short address, Value valueToWrite) {
        write(address, valueToWrite.get());
    }

    public void write(Value address, short valueToWrite) {
        write(address.get(), valueToWrite);
    }

    public void write(int address, int valueToWrite) {
        write((short) address, (short) valueToWrite);
    }

    public void write(short address, short valueToWrite) {
        // todo;
    }
}
