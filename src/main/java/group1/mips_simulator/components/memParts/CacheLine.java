package group1.mips_simulator.components.memParts;

import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.memParts.Storage;
import group1.mips_simulator.components.Config;

public class CacheLine{
    // one line has 8 words
    // tag is    vvv  for
    // octal [88 888 8]

    Word[] entries = new Word[Config.ENTRIES_PER_BLOCK]; //8 words per block, each word is an entry
    public CacheLine(){

    }

    public Word getEntry(int addressLocation){
        //todo: write unit tests
        int blockNumber = getBlockNumber(addressLocation);
        return entries[blockNumber];
    }

    public void setEntry(int addressLocation, Word entry){
        //todo: write unit tests
        int blockNumber = getBlockNumber(addressLocation);
        entries[blockNumber] = entry;
    }

    public int getBlockNumber(int addressLocation){
        //todo: write unit tests
        return (addressLocation | 0b111);
    }

}
