package group1.mips_simulator.components.memParts;

import group1.mips_simulator.Utility;
import  group1.mips_simulator.components.Config;
import group1.mips_simulator.components.Word;

import java.util.*;

public class Cache{
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
    Map<Short,CacheBlock> cacheBlocks; //<tag:block>


    public Cache(Memory memory_){
        //cache has a memory it references
        memory = memory_;
    }





    public void addLine(short tag){
        //get block of memory where tag is, create a cache block to be added to the cache
        cacheQueue.add(tag); //add the new tag to the cache
        cacheBlocks.put(tag, new CacheBlock(tag, memory));
    }

    public void removeLine(){
        // remove the oldest line from cache
        Short tagToRemove = cacheQueue.poll();              //remove top CacheLine from Cache
        CacheBlock blockToRemove = getBlock(tagToRemove);   //get block to be removed, initiate write-back
        blockToRemove.writeBlockToMemory(memory);           //write self back to memory
        cacheBlocks.remove(tagToRemove);                    //remove that tag from the lines Map
    }

    public Word getWordAtAddress(short address){
        short tag = calculateTag(address);
        short offset = calculateOffset(address);
        CacheBlock block = getBlock(tag);
        return block.get(tag);
    }

    public void setWordAtAddress(short address,Word value){
        short tag = calculateTag(address);
        short offset = calculateOffset(address);
        CacheBlock block = getBlock(tag);
        block.set(tag,value);
    }


    public CacheBlock getBlock(short tag){
        // get the full line of data at that line number
        CacheBlock returnBlock;

        if(cacheBlocks.containsKey(tag))
            returnBlock = cacheBlocks.get(tag);
        else{
            if(cacheQueue.size() < cacheSize) { addLine(tag); }
            else{ removeLine(); }
            returnBlock = cacheBlocks.get(tag);
        }
        return returnBlock;
    }



    // getter - fields
    public short calculateTag(short address){
        // get tag bits from memory location references.
        int mask = 0x0FF8; //mask for line tag
        return (short)((address & mask)>>3);
    }

    public short calculateOffset(short address){
        // get Word indexing bits from memory location references.
        int mask = 0b111; //mask for byte-indexing
        return (short)(address & mask);
    }



    // String Methods

    public String tagToOctalString(short address){
        short tag = calculateTag(address);
        return Utility.shortToOctalString(tag,3);
    }
    public String lineToString(short tag){
        //prints an entire line from cache as a string
        return Utility.shortToOctalString((short)tag,3) + cacheBlocks.get(tag).toString();
    }

//    public String printLine(short tag){
//        StringBuilder sb = new StringBuilder();
//        sb.append(tag);
//        CacheBlock b = this.getBlock(tag);
//        sb.append(b.toString());
//        return sb.toString();
//    }




}
