package group1.mips_simulator.components.instruction.CacheTest;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.memParts.CacheLine;
import group1.mips_simulator.components.memParts.Cache;
import group1.mips_simulator.components.memParts.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheTest {
    int TEST_MEM_SIZE = 16;
    Memory mem = new Memory(TEST_MEM_SIZE);

    Cache c;
    CacheLine l = new CacheLine(3,mem);

    private void setupTest(){
        short[] valuesToWrite = new short[]{
                0,   (short)0xFFFF,(short)0x1234,(short)0xBEEF,
                0,   (short)0xA8A8,    0,  0,
                9827,(short)0xC922, 0,(short)0x7FFF,
                0,  0,  8,  0
        };

        for(int i=0;i<TEST_MEM_SIZE;i++){
            mem.write((short) i, valuesToWrite[i]);
        }

        c = new Cache(mem);
    }

    @Test
    void getLineTag(){
        setupTest();
        short[] addresses = new short[]{0,1,1234,(short)0xFF12};
        for(short address : addresses){
            // uncomment to view outputs
            // System.out.println("Addr:" + Utility.shortToBinaryString(address,16));
            short tag = c.getTag(address);
            // System.out.println(" Tag:" + Utility.shortToBinaryString(tag,16));
            assertEquals((short)((address & 0xFFF8)>>3),tag);
        }


    }

    @Test
    void Cache(){
        setupTest();
        c.getLineFromCache((short) 0x1234);

        for(int i = 0; i < TEST_MEM_SIZE; i++){
            short data = mem.read(i);
            // System.out.println("Addr:" + Utility.shortToBinaryString(data,16));
            short tag = c.getTag(data);
            // System.out.println(" Tag:" + Utility.shortToBinaryString(tag,16));
            assertEquals((short)((data & 0xFFF8)>>3),tag);
        }
    }






}
