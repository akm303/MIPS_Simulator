package group1.mips_simulator.components.memParts;

import  group1.mips_simulator.components.Config;

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
        for(int i = 0; i < lastAccessedArray.length; i++)
            lastAccessedArray[i] = 0; //set all counts to -
    }

    public CacheLine getLine(int dataLocation){
        // get the full line of data at that line number
        int lineNumber = getTag(dataLocation);
        updateAccessCounts(lineNumber);
        return blocks[lineNumber];
    }

    public void setLine(int dataLocation,Memory memory){
        // todo: set the line with highest access count with the data from memory at those locations.
        // make sure correct items in memory are getting pulled,

        int lineNumber = getTag(dataLocation);
        CacheLine line = blocks[lineNumber];
        for(int i = 0; i < Config.ENTRIES_PER_BLOCK; i++)
            line.setEntry(i,memory.get(i + asdf));
        updateAccessCounts(lineNumber);
    }

    public void updateAccessCounts(int dataLocation){
        int lineNumber = getTag(dataLocation);
        for(int i = 0; i < lastAccessedArray.length; i++)
            lastAccessedArray[i] += 1; //update all counters (will reset appropriate one to 0 at end)
        lastAccessedArray[lineNumber] = 0; //then set current line count to 0
    }

    private int getTag(int dataLocation){
        //todo: get tag bits of memory location references.
        // return an integer of it
        int lineNumber = 0;

    }
}
