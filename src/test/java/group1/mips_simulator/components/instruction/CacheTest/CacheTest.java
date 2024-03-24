package group1.mips_simulator.components.instruction.CacheTest;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.memParts.CacheLine;
import group1.mips_simulator.components.memParts.Cache;
import group1.mips_simulator.components.memParts.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheTest {
    Memory mem = new Memory();
    Cache c = new Cache(mem);

    @Test
    void getLineTag(){
        short[] addresses = new short[]{0,1,1234,(short)0xFF12};
        for(short address : addresses){
            // uncomment to view outputs
            // System.out.println("Addr:" + Utility.shortToBinaryString(address,16));
            short tag = c.getLineTag(address);
            // System.out.println(" Tag:" + Utility.shortToBinaryString(tag,16));
            assertEquals((short)((address & 0xFFF8)>>3),tag);
        }
    }


}
