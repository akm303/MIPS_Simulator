package group1.mips_simulator.components.memParts;

import  group1.mips_simulator.components.Config;

public class Cache extends Memory{
    /*
    16 line
    fully associative
     */
    CacheLine[] blocks = new CacheLine[Config.CACHE_LINES];
    short[] lastAccessedArray = new short[Config.CACHE_LINES];
    // for fully-associative cache, each line has a counter to let you know
    // when it was last accessed (to decide which line to overwrite when needed)
    // every time a memory access occurs (load/store), all non-accessed memory
    // increments by 1
    // the last memory to be accessed (either written in, or read from) has their
    // count set to 0

    public Cache(){
        for(int i = 0; i < lastAccessedArray.length; i++)
            lastAccessedArray[i] = 0; //set all counts to -
    }

    public CacheLine getLine(int lineNumber){
        return blocks[lineNumber];
    }

    public void setLine(int lineNumber, Memory memory){
        CacheLine line = blocks[lineNumber];

    }

    public void updateAccessCounts(int lineNumber){
        for(int i = 0; i < lastAccessedArray.length; i++){
            lastAccessedArray[i] += 1; //update all
        }
        lastAccessedArray[lineNumber] = 0; //then set current line count to 0
    }
}
