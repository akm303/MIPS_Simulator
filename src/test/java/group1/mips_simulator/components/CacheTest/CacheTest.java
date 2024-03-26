package group1.mips_simulator.components.CacheTest;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.memParts.Cache;
import group1.mips_simulator.components.memParts.Block;
import group1.mips_simulator.components.memParts.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheTest {
    Memory mem = new Memory(Config.MEM_SIZE);
    Cache c;


    @Test
    void calculateTag(){
        //test that getTag extracts correct bits from a short
        c = new Cache(mem);
        short[] data = new short[]{
                0,              //0b0000_0000_0000_0000
                1,              //0b0000_0000_0000_0001
                1234,           //0b0000_0100_1101_0010
                (short)0xFF12,  //0b1111_1111_0001_0010
                (short)0x7FAB   //0b0111_1111_1010_1011
        };
        short[] expected = new short[]{
                0b0000_0000_0000_0, //0
                0b0000_0000_0000_0, //1
                0b0000_0100_1101_0, //1234
                0b0000_1111_0001_0, //FF12
                0b0000_1111_1010_1, //7FAB
        };
        for(int i = 0; i<data.length; i++){
            // // uncomment to view outputs
            short d = data[i];
            // System.out.println("Addr:" + Utility.shortToBinaryString(d,16)+", "+d);
            short tag = c.calculateTag(d);
            // System.out.println(" Tag:" + Utility.shortToBinaryString(tag,16)+", "+tag);
            assertEquals(expected[i],tag);
        }
    }

    @Test
    void calculateOffset(){
        c = new Cache(mem);
        //test that getIndex extracts correct bits from a short
        short[] data = new short[]{
                0,              //0b0000_0000_0000_0000
                1,              //0b0000_0000_0000_0001
                1234,           //0b0000_0100_1101_0010
                (short)0xFF12,  //0b1111_1111_0001_0010
                (short)0x7FAB   //0b0111_1111_1010_1011
        };
        short[] expected = new short[]{
                0b000, //0 -> 0
                0b001, //1 -> 1
                0b010, //1234 -> 2
                0b010, //FF12 -> 2
                0b011, //7FAB -> 3
        };
        for(int i = 0; i<data.length; i++){
            // // uncomment to view outputs
            short d = data[i];
            // System.out.println("Addr:" + Utility.shortToBinaryString(d,16)+", "+d);
            short f = c.calculateOffset(d);
            // System.out.println(" Tag:" + Utility.shortToBinaryString(i,16)+", "+f);
            assertEquals(expected[i],f);
        }
    }

    @Test
    void tagToOctalString(){
        //test that getTag extracts correct bits from a short
        c = new Cache(mem);
        short[] data = new short[]{0,1,1234,(short)0xFF12,(short)0x7FAB};
        for(short d : data){
            // // uncomment to view outputs
            //System.out.println("Addr:" + Utility.shortToOctalString(d)+", "+d);
            String tag = c.tagToOctalString(d);
            //System.out.println(" Tag:"+tag);
            d = (short)((d & 0x0FF8)>>3);
            String addrStr = Utility.shortToOctalString(d,3);
            assertEquals(addrStr,tag);
        }
    }



    @Test
    void block_toString(){
        short[] val2write = new short[]{
                1,0,1,0,2,0,2,0,
                1,2,3,4,5,6,7,8,
                9,10,12,13,14,15,16,24
        };
        for(short i = 0; i < val2write.length; i++){
            mem.write(i,val2write[i]);
        }

        short[] tags = new short[]{0,1,2,9};
        String[] expected = new String[]{
                " 000001 000000 000001 000000 000002 000000 000002 000000",
                " 000001 000002 000003 000004 000005 000006 000007 000010",
                " 000011 000012 000014 000015 000016 000017 000020 000030",
                " 000000 000000 000000 000000 000000 000000 000000 000000"
        };

        // test each block prints correctly
        for(int i = 0; i < tags.length; i++){
            Block b = new Block(tags[i],mem);
            // System.out.println(b.toString());
            assertEquals(expected[i],b.toString());
        }
    }


    @Test
    void lines_toString(){
        /*
        Create a Cache. Add some values to the cache. See if cache blocks read properly
         */
        setup();

        // test cache lines print correctly
        short[] tags = new short[]{0,1,2,9};
        String[] expected = new String[]{
                "000 000001 000000 000001 000000 000002 000000 000002 000000",
                "001 000010 000011 000012 000013 000014 000015 000016 000017",
                "002 000011 000012 000014 000015 000016 000017 000020 000030",
                "011 000000 000000 000000 000000 000000 000000 000000 000000"
        };

        c = new Cache(mem);

        for(short tag:tags)
            c.getBlock(tag); //get the blocks based on the tags


        for(int i = 0; i < tags.length; i++){
            System.out.println(c.lineToOctalString(tags[i]));
            assertEquals(expected[i],c.lineToOctalString(tags[i]));
        }
    }

    @Test
    void cacheBeforeMemory(){
        /* todo: Test that Cache Lines properly equal values in memory */
        c = new Cache(mem);
        setup();

    }

    @Test
    void cacheAfterMemory(){
        /* todo: Test that Cache Lines properly equal values in memory */
        setup();
        c = new Cache(mem);

    }



    void setup(){
        short[] val2write = new short[]{
                1,0,1,0,2,0,2,0,
                8,9,10,11,12,13,14,15,
                9,10,12,13,14,15,16,24
        };
        for(short i = 0; i < val2write.length; i++){
            mem.write(i,val2write[i]);
        }
    }

}
