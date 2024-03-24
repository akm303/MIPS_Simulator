package group1.mips_simulator.components.memParts;

import  group1.mips_simulator.components.Config;

import java.util.*;

public class Cache extends Memory{
    /*
    16 line, fully associative cache
    Write Through (writes to cache, and immediately writes to memory (shouldnt need to implement separately since
    in Java, variables names should be references to the same objects))

    Cache is accessed usually by seeing if a certain data location is in a cache
    (i.e. if the Word of a memory location has a tag that we extract, which should a line tag in cache.)

    Fully associative cache means we can write to any line in cache, and we do so by writing over the oldest line first
    (i.e. FIFO)
     */
    Memory memory; //reference to memory that cache is linked to
    int cacheSize = Config.CACHE_LINES;

    //data structures
    Queue<Short> cacheQueue; //going to use list as a queue for now
    Map<Short,CacheLine> cacheLines; //

    //    CacheLine[] lines = new CacheLine[cacheSize];

    public Cache(Memory _memory){
        //cache has a memory it references
        memory = _memory;
    }

    public void addLine(short dataAddr){
        //get block of memory within which data is, and create a cache line to be added to the cache
        short tag = getTag(dataAddr);
        cacheQueue.add(tag); //add the new tag to the cache
        cacheLines.put(tag, new CacheLine(dataAddr, memory));
    }

    public void removeLine(){
        // remove the oldest line from cache
        Short tagToRemove = cacheQueue.poll(); //remove top CacheLine from Cache
        cacheLines.remove(tagToRemove); //remove that tag from the lines Map
    }

    public CacheLine getLineFromCache(short dataAddr){
        // get the full line of data at that line number
        CacheLine returnLine;
        short tag = getTag(dataAddr);

        if(cacheLines.containsKey(tag))
            returnLine = cacheLines.get(tag);
        else{
            if(cacheQueue.size() < cacheSize) { addLine(dataAddr); }
            else{ removeLine(); }
            returnLine = cacheLines.get(tag);
        }
        return returnLine;
    }


//        CacheLine tagLine = new CacheLine(tag,memory); //get cache line with that tag from memory
//        // if tag is in cache
//        for(int i = 0; i < cacheQueue.size(); i++){
//
//        }

//    }


//    public CacheLine getLineFromMemory(short dataAddr, Memory memory){
//        short tag = getLineTag(dataAddr);
//        cacheQueue.add(tag);
//        return new CacheLine(dataAddr,memory);
//    }

    public short getTag(short data){
        // get tag bits of memory location references.
        int tagMask = 0x0FF8;
        return (short)((data & tagMask)>>3);
    }

}
