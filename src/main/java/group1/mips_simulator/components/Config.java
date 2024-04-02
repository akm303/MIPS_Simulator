package group1.mips_simulator.components;

public class Config {
    public static final int MEM_SIZE = 2048; //2048 words, expandable to 4096 words
    public static final int INSTR_OFFSET = 1024; // location in Memory to start load instructions from ROM
    public static final int GPR_COUNT = 4; // 4 general purpose registers
    public static final int IXR_COUNT = 3; // 3 index registers
    public static final short WORD_SIZE = 16;
    public static final int ENTRIES_PER_BLOCK = 8;
    public static final int CACHE_LINES = 16;
    public static final int MAX_16_BIT_VALUE = 0b1111_1111_1111_1111;
}
