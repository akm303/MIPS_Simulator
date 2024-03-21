package group1.mips_simulator.components.memParts;

import  group1.mips_simulator.components.Config;

public class Cache extends Memory{
    /*
    16 line
    fully associative
     */
    CacheLine[] blocks = new CacheLine[Config.CACHE_LINES];

    public Cache(){

    }

    public CacheLine getLine(int lineNumber){
        return blocks[lineNumber];
    }

    public void setLine(int lineNumber, Memory memory){
        CacheLine line = blocks[lineNumber];

    }
}
