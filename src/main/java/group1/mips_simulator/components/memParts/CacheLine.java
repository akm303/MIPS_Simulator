package group1.mips_simulator.components.memParts;

import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.memParts.Storage;


public class CacheLine{
    // one line has 8 words
    // tag is    vvv  for
    // octal [88 888 8]

    int blockSize = Config.ENTRIES_PER_BLOCK;
    public Word[] entries; //8 words per block, each word is an entry

    public CacheLine(int memAddr, Memory memory){
        int startOfBlock = memAddr - (memAddr % blockSize);
        entries = new Word[blockSize];

        for(int i = 0; i < entries.length; i++){
            // set entry at block index i to be
            // memory's entry at address i
            setEntry(i, memory.get(startOfBlock + i));
        }
    }

    public Word getEntry(int addressLocation){
        //todo: write unit tests
        int blockIdx = getIndex(addressLocation);
        return entries[blockIdx];
    }

    public void setEntry(int addressLocation, Word entry){
        //todo: write unit tests
        // if we are doing write through, i need to know if
        // java considers entries[blockIdx] and memory[addressLocation] to be references
        // to the same block of memory
        // otherwise need to do a second write directly to memory, within this function
        int blockIdx = getIndex(addressLocation);
        entries[blockIdx] = entry;
    }


    public int getIndex(int addressLocation){
        //todo: write unit tests
        // also pretty sure its not right, but need to check spec
        return (addressLocation | 0b111);
    }



}