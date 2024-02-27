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
 *
 * Memory has a static size, defined during init
 * Other classes can read/write Values from/to Memory
 * Memory addresses are either Values or integers
 */
public class Memory extends Storage{
    private int size_;
    
    public Memory(int size){
        super(size);
    }

    public Value read(int address) {
        return this.data[address];
    }

//     public Value read(Value address) {
      // potentially redundant from merge conflict
//         return this.read(address.get());
//     }

    public short read(Value address) {
        return read(address.get());
    }




    public void write(Value address, Value valueToWrite) {
        this.data[address.get()].set(valueToWrite);
    }
}
