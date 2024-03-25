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

import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.Word;

/**
 * A Memory class represents the entire memory lookup for our computer simulator.
 * <p>
 * Memory has a static size, defined during init
 * Other classes can read/write Values from/to Memory.
 * Memory addresses are either Values or integers
 */
public class Memory extends Storage {
    private int size_;

    public Memory() {
        // init memory with standard size from config
        this(Config.MEM_SIZE);
    }

    public Memory(int size) {
        // init memory with custom size
        super(size);
    }


    // GETTERS
    /* get() will get the Value from memory */

    public Word get(short address) {
        // main get method (all get() and read() methods routed through here)
        // get the Value from memory address as a short
        return this.data[address];
    }

    public Word get(int address) {
        // get the Value from memory address as an int
        return get((short) address);
    }



    /* read() will read the short data from memory */

    public short read(Word address) {
        // read the data from address in a Value
        return get(address.get()).get();
    }

    public short read(short address) {
        // read the data from address as a short
        return get(address).get();
    }

    public short read(int address) {
        // read the data from address as a int
        return get(address).get();
    }


    // SETTERS
    /* set() will set the Value into memory at an address */

    public void set(short address, Word valueToWrite) {
        // main set method (all set() and write() methods routed through here)
        // set the Word in memory at a short address
        this.data[address].set(valueToWrite);
    }

    public void set(Word address, Word valueToWrite) {
        // set the Word in memory at a Word address
        set(address.get(),valueToWrite);
    }



    /* write() will write the short data to memory */
    public void write(short address, short valueToWrite) {
        // write the short to memory at the short address
        set(address,new Word(valueToWrite));
    }

    public void write(Word address, short valueToWrite) {
        //god forbid
        // write the short to memory at the word address
        set(address.get(),new Word(valueToWrite));
    }


    public int size(){
        return this.size_;
    }

}
