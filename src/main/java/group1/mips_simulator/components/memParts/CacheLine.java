package group1.mips_simulator.components.memParts;

import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.memParts.Storage;
import group1.mips_simulator.components.Config;

public class CacheLine{
    // one line has 8 words
    // tag is    vvv  for
    // octal [88 888 8]

    Word[] blocks = new Word[Config.WORDS_PER_BLOCK]; //8 words per block
    public CacheLine(){

    }

    public Word getBlock(int blockNumber){
        return blocks[blockNumber];
    }

    public void setBlock(int blockNumber, Word entry){
        blocks[blockNumber] = entry;
    }

}
