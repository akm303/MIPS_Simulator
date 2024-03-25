package group1.mips_simulator.components.memParts;

import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.Word;

public class CacheBlock extends Memory{
    /*
    Is the actual unit of memory for the cache
    Each line has a cache block
     */

    public CacheBlock(int tag, Memory memory){
        super(Config.ENTRIES_PER_BLOCK); // 8 words per block
        int startOfBlock = tag<<3;
        for(short i = 0; i < data.length; i++){
            // set entry at block index i to be memory's entry at address i
            write(i, memory.read(startOfBlock + i));
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




/*
package group1.mips_simulator.components.memParts;
import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.memParts.Storage;

public class CacheLine {
    // one line has a block of 8 words;
    // we need a block offset of 3 (8 words = 2^3 words)
    // tag is    vvv  for
    // octal [88 888 8]
    // Cache line handles access to the Cache block

    public CacheBlock block;

    public CacheLine(short tag, Memory memory){
        block = new CacheBlock(tag,memory);
    }



    public Word get(int addressLocation){
        int blockIdx = getIndex(addressLocation);
        return block.get(blockIdx);
    }

    public void set(int addressLocation, Word entry){
        //todo: write unit tests
        short idx = getIndex(addressLocation);
        block.set(idx,entry);
    }




    public String blockString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < block.data.length; i++){
            sb.append(block.get(i));
        }
        return sb.toString();
    }


    public short getIndex(int addressLocation){
        //todo: write unit tests
        return (short)(addressLocation & 0b111);
    }


}


 */