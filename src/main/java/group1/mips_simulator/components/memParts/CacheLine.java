package group1.mips_simulator.components.memParts;

import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.memParts.Storage;
import group1.mips_simulator.components.Config;

import java.util.Arrays;

public class CacheLine{
    // one line has 8 words
    // tag is    vvv  for
    // octal [88 888 8]

    public Word[] entries; //8 words per block, each word is an entry

    public CacheLine(){
        entries = new Word[Config.ENTRIES_PER_BLOCK];
        Arrays.fill(entries,new Word(0));
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
