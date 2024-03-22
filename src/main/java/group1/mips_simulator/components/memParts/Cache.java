package group1.mips_simulator.components.memParts;

import  group1.mips_simulator.components.Config;

import java.util.Arrays;

public class Cache extends Memory{
    /*
    16 line
    fully associative
    Cache is accessed usually by seeing if a certain data location is in a cache
    i.e. if the binary of a memory location has a tag that matches a line tag in cache.
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
        //set all counts to 0
        Arrays.fill(lastAccessedArray, (short) 0);
    }

    public CacheLine getLine(int dataAddress){
        // get the full line of data at that line number
        int lineNumber = getLineTag(dataAddress);
        updateAccessCounts(lineNumber);
        return blocks[lineNumber];
    }

    public void setLine(int dataAddress,Memory memory){
        // todo: set the line with highest access count with the data from memory at those locations.
        // make sure correct items in memory are getting pulled,

        int lineNumber = getLineTag(dataAddress);
        CacheLine line = blocks[lineNumber];
        for(int i = 0; i < Config.ENTRIES_PER_BLOCK; i++)
            line.setEntry(i,memory.get(dataAddress));
        updateAccessCounts(lineNumber);
    }

    public void updateAccessCounts(int lineNumber){
        for(int i = 0; i < lastAccessedArray.length; i++)
            lastAccessedArray[i] += 1;      //update all counters (will reset appropriate one to 0 at end)
        lastAccessedArray[lineNumber] = 0;  //then set current line count to 0
    }

    private int getLineTag(int dataAddress){
        //todo: get tag bits of memory location references.
        // return an integer of it
        int tag = 0;
        return tag;
    }

    private boolean inCache(int dataAddress) {
        int tag = getLineTag(dataAddress);
        for(int i = 0; i < lastAccessedArray.length; i++){

        }
    }
}
