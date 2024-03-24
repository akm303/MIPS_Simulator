package group1.mips_simulator.components;

import java.util.HashSet;
import java.util.Set;

public class Config {
    public static final int MEM_SIZE = 2048; //2048 words, expandable to 4096 words
    public static final int INSTR_OFFSET = 1024; // location in Memory to start load instructions from ROM
    public static final int GPR_COUNT = 4; // 4 general purpose registers
    public static final int IXR_COUNT = 3; // 3 index registers
    public static final short WORD_SIZE = 16;

    //region Device IDs

    public static final int CONSOLE_KEYBOARD_DEVID = 0;
    public static final int CONSOLE_PRINTER_DEVID = 1;

    public static final Set<Integer> IN_DEVICES = new HashSet<>() {{
        add(CONSOLE_KEYBOARD_DEVID);
    }};

    public static final Set<Integer> OUT_DEVICES = new HashSet<>() {{
        add(CONSOLE_PRINTER_DEVID);
    }};
    //endregion


}
