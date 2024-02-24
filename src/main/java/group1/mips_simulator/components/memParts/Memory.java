/*
single port memory in Part I
Upon powering up your system, all elements of memory should be set to zero.
Your memory simulation should accept an address from the MAR on one cycle.
It should then accept a value in the MBR to be stored in memory on the next cycle or place a
value in the MBR that is read from memory on the next cycle.
Remember, your machine can have up to 2048 words maximum! What considerations must you make?
 */
//readme ^

package group1.mips_simulator.components.memParts;

import group1.mips_simulator.components.Value;

/**
 * A Memory class represents the entire memory lookup for our computer simulator.
 */
public class Memory extends Storage{
    private int size_;
    public Memory(int size){
        super(size);
    }

    public short read(Value address) {
        return this.data[address.get()].get();
    }

    public void write(Value address, Value valueToWrite) {
        this.data[address.get()].set(valueToWrite);
    }
}
