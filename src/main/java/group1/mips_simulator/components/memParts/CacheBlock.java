package group1.mips_simulator.components.memParts;

import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.Word;

public class CacheBlock extends Memory{
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

    public CacheBlock(int tag_, Memory memory_){
        super(Config.ENTRIES_PER_BLOCK); // 8 words per block
        tag = tag_;
        readBlockFromMemory(memory_);
    }


    public void readBlockFromMemory(Memory memory_){
        int startOfBlock = tag << 3;
        for(short i = 0; i < data.length; i++){
            // set entry at block index i to be memory's entry at address i
            write(i, memory_.read(startOfBlock + i));
        }
    }

    public void writeBlockToMemory(Memory memory_){
        // write every value of the block to memory
        short startOfBlock = (short)(tag << 3);
        for(short i = 0; i < data.length; i++){
            memory_.write((short)(startOfBlock + i),read(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            sb.append(" ").append(get(i).toString_Oct());
        }
        return sb.toString();
    }

}

