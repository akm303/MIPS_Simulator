package group1.mips_simulator.components.memParts;

import group1.mips_simulator.Utility;
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

    //data
    int cacheSize = Config.CACHE_LINES;
    Memory memory; //reference to memory that cache is linked to
    //data structures
    Queue<Short> cacheQueue; //going to use list as a queue for now
    Map<Short,CacheLine> cacheLines; //<tag:block>


    public Cache(Memory _memory){
        //cache has a memory it references
        memory = _memory;
    }

    public void addLine(short tag){
        //get block of memory where tag is, create a cache block to be added to the cache
        cacheQueue.add(tag); //add the new tag to the cache
        cacheLines.put(tag, new CacheLine(tag, memory));
    }

    public void removeLine(){
        // remove the oldest line from cache
        Short tagToRemove = cacheQueue.poll(); //remove top CacheLine from Cache
        cacheLines.remove(tagToRemove); //remove that tag from the lines Map
    }


    public CacheLine getLine(short dataAddr){
        // get the full line of data at that line number
        CacheLine returnLine;
        short tag = getTag(dataAddr);

        if(cacheLines.containsKey(tag))
            returnLine = cacheLines.get(tag);
        else{
            if(cacheQueue.size() < cacheSize) { addLine(tag); }
            else{ removeLine(); }
            returnLine = cacheLines.get(tag);
        }
        return returnLine;
    }

    public String printLine(short tag){
        StringBuilder sb = new StringBuilder();
        sb.append(tag);
        CacheLine l = this.getLine(tag);
        sb.append(l.blockString());
        return sb.toString();
    }


    // getter - fields
    public short getTag(short address){
        // get tag bits from memory location references.
        int mask = 0x0FF8; //mask for line tag
        return (short)((address & mask)>>3);
    }


    public String getTag_octalString(short address){
        short tag = getTag(address);
        return Utility.shortToOctalString(tag,3);
    }

    public short getEntry(int address){
        // get Word indexing bits from memory location references.
        int mask = 0b111; //mask for byte-indexing
        return (short)(address & mask);
    }




}
