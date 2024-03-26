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
    Map<Short, Block> cacheBlocks; //<tag:block>


    // CONSTRUCTOR
    public Cache(Memory memory_){
        //cache has a memory it references
        memory = memory_;
    }


    // LINE OPERATIONS
    public void addLine(short tag){
        /** Add a new line to cache
         1. Add a new tag to end of Queue
         2. Pass tag and memory reference to a Cache Block to have it generate itself
         3. Add the new block to the dictionary
         */
        cacheQueue.add(tag); //add the new tag to the cache
        cacheBlocks.put(tag, new Block(tag, memory));
    }

    public void removeLine(){
        /** remove the oldest line from cache
         1. get the oldest tag from Queue,
         2. write the corresponding block back to memory
         3. then delete that block from the dictionary
         */
        Short tagToRemove = cacheQueue.poll();
        if(tagToRemove == null) //redundant, will never remove a block unless there are already 16 lines in cache
            return;
        Block blockToRemove = getBlock(tagToRemove);
        blockToRemove.writeBlockToMemory(memory); //write back
        cacheBlocks.remove(tagToRemove);
    }

    // BLOCK ACCESS
    public Block getBlock(short tag){
        // get the block of data from memory corresponding to that tag, create a new block
        Block returnBlock;
        if(cacheBlocks.containsKey(tag))
            returnBlock = cacheBlocks.get(tag);
        else{
            if(cacheQueue.size() < cacheSize) { addLine(tag); }
            else{ removeLine(); }
            returnBlock = cacheBlocks.get(tag);
        }
        return returnBlock;
    }

    // WORD OPERATIONS GET/SET
    public Word getWordAtAddress(short address){
        short tag = calculateTag(address);
        short offset = calculateOffset(address);
        Block block = getBlock(tag);
        return block.get(offset);
    }

    public void setWordAtAddress(short address, Word value){
        short tag = calculateTag(address);
        short offset = calculateOffset(address);
        Block block = getBlock(tag);
        block.set(offset,value);
    }

    /* short OPERATIONS READ/WRITE */
    public short readShortAtAddress(short address){
        // read a short value from address
        return getWordAtAddress(address).get();
    }

    public void writeShortToAddress(short address, short value){
        // write a short value to address
        setWordAtAddress(address,new Word(value));
    }



    /* FIELD OPERATIONS TAG/OFFSET */
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



    /* STRING METHODS */
    public String tagToOctalString(short tag){
        return Utility.shortToOctalString(tag,3);
    }

    public String blockToOctalString(short tag){
        return cacheBlocks.get(tag).toString();
    }

    public String lineToOctalString(short tag){
        //prints an entire line from cache as a string
        return tagToOctalString(tag) + blockToOctalString(tag);
    }


}
