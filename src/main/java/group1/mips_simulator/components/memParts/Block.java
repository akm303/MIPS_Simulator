package group1.mips_simulator.components.memParts;

import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.Word;

public class Block extends Memory{
    /**
     *  The unit of memory for each line of cache.
     *
     *  To Access data from cache block,
     *  pass the offset bits of a data address,
     *  rather than the data address itself.
     *
     *  get/set/read/write access will operate the same as
     *  any other memory object, just
     */
    private final int tag;

    public Block(int tag_, Memory memory){
        super(Config.ENTRIES_PER_BLOCK); // 8 words per block
        tag = tag_;
        readBlockFromMemory(memory);
    }

    public void update(Memory memory){
        readBlockFromMemory(memory);
    }

    public void readBlockFromMemory(Memory memory){
        // write every value in associated block of memory to self
        int startOfBlock = tag << 3;
        for(short i = 0; i < data.length; i++){
            write(i, memory.read(startOfBlock + i));
        }
    }

    public void writeBlockToMemory(Memory memory){
        // write every value of the block to memory
        short startOfBlock = (short)(tag << 3);
        for(short i = 0; i < data.length; i++){
            memory.write((short)(startOfBlock + i),read(i));
        }
    }



    @Override
    public String toString() {
        // generate string of every word in block
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            sb.append(" ").append(get(i).toString_Oct());
        }
        return sb.toString();
    }

}

