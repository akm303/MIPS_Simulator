package group1.mips_simulator.components.memParts;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.memParts.Storage;


public class CacheLine {
    // one line has a block of 8 words;
    // we need a block offset of 3 (8 words = 2^3 words)
    // tag is    vvv  for
    // octal [88 888 8]
    // Cache line handles access to the Cache block

    int tag;
    public CacheBlock block;

    public CacheLine(int tag_, Memory memory){
        tag = tag_;
        block = new CacheBlock(tag,memory);
    }

    public int getTag(){
        return tag;
    }

    public String blockString(){
        return block.toString();
    }

    public String lineString(){
        return Utility.shortToOctalString((short)tag,3) + blockString();
    }



//    // getters
//    public Word get(int address){
//        int blockIdx = getIndex(address);
//        return block.get(blockIdx);
//    }
//
//    public short read(int address){
//        return get(address).get();
//    }
//
//    // setters
//    public void set(int address, Word entry){
//        short idx = getIndex(address);
//        block.set(idx,entry);
//    }
//
//    public void write(int address, short entry){
//        set(address,new Word(entry));
//    }








}
