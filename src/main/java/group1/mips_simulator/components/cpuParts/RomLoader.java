package group1.mips_simulator.components.cpuParts;

public class RomLoader {
    /*
    read the boot program from the ROM and place it into memory in a location you designated.
    The ROM Loader then transfers control to the program which executes until completion or error
     */
    String ROMLocation;

    public RomLoader(){
        this.ROMLocation = null;
    }
    public RomLoader(String directory){
        this.ROMLocation = directory;
    }

    public void loadFrom(String directory){
        this.ROMLocation = directory;
    }
}
