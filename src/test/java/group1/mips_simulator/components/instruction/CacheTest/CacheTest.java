package group1.mips_simulator.components.instruction.CacheTest;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.memParts.Cache;
import group1.mips_simulator.components.memParts.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheTest {
    Memory mem = new Memory(Config.MEM_SIZE);
    Cache c;
//    CacheLine l = new CacheLine((short) 3,mem);
//    CacheBlock b = new CacheBlock((short) 3,mem);



//    private void setupTest(){
//        short[] valuesToWrite = new short[]{ //values to be written to memory
//                0,   (short)0xFFFF,(short)0x1234,(short)0xBEEF,
//                0,   (short)0xA8A8,    0,  0,
//                9827,(short)0xC922, 0,(short)0x7FFF,
//                0,  0,  8,  0
//        };
//
//        for(int i=0;i<TEST_MEM_SIZE;i++){ // writing those values to memory
//            mem.write((short) i, valuesToWrite[i]);
//        }
//
//        c = new Cache(mem); //create an empty cache from memory
//    }

    @Test
    void getLineTag(){
        //test that getTag extracts correct bits from a short
        c = new Cache(mem);
        short[] data = new short[]{0,1,1234,(short)0xFF12,(short)0x7FAB};
        for(short d : data){
            // // uncomment to view outputs
//             System.out.println("Addr:" + Utility.shortToBinaryString(d,16)+", "+d);
             short tag = c.getTag(d);
//             System.out.println(" Tag:" + Utility.shortToBinaryString(tag,16)+", "+tag);
            assertEquals((short)((d & 0x0FF8)>>3),tag);
        }
    }

    @Test
    void getBlockIndex(){
        c = new Cache(mem);
        //test that getIndex extracts correct bits from a short
        short[] data = new short[]{0,1,1234,(short)0xFF12,(short)0x7FAB};
        for(short d : data){
            // // uncomment to view outputs
            // System.out.println("Addr:" + Utility.shortToBinaryString(d,16)+", "+d);
            short i = c.getEntry(d);
            // System.out.println(" Tag:" + Utility.shortToBinaryString(i,16)+", "+i);
            assertEquals((short)(d & 0x0007),i);
        }
    }

    @Test
    void getTag_OctalString(){
        //test that getTag extracts correct bits from a short
        c = new Cache(mem);
        short[] data = new short[]{0,1,1234,(short)0xFF12,(short)0x7FAB};
        for(short d : data){
            // // uncomment to view outputs
            //System.out.println("Addr:" + Utility.shortToOctalString(d)+", "+d);
            String tag = c.getTag_octalString(d);
            //System.out.println(" Tag:"+tag);
            d = (short)((d & 0x0FF8)>>3);
            String addrStr = Utility.shortToOctalString(d,3);
            assertEquals(addrStr,tag);
        }
    }



    @Test
    void blocksString(){
        short[] val2write = new short[]{
                1,0,1,0,2,0,2,0,
                1,2,3,4,5,6,7,8,
                9,10,12,13,14,15,16,24
        };
        for(short i = 0; i < val2write.length; i++){
            mem.write(i,val2write[i]);
        }

        // test cache lines, should
//        CacheLine cl1 = new CacheLine(0,mem);
        System.out.println(cl1.blockString());
        assertEquals(cl1.blockString()," 000001 000000 000001 000000 000002 000000 000002 000000");
        System.out.println(cl1.block.toString());

//        CacheLine cl2 = new CacheLine(1,mem);
        System.out.println(cl2.blockString());
        assertEquals(cl2.blockString()," 000001 000002 000003 000004 000005 000006 000007 000010");
        System.out.println(cl2.block.toString());

//        CacheLine cl3= new CacheLine(2,mem);
        System.out.println(cl3.blockString());
        assertEquals(cl3.blockString()," 000011 000012 000014 000015 000016 000017 000020 000030");
        System.out.println(cl3.block.toString());

//        CacheLine cl4 = new CacheLine(9,mem);
        System.out.println(cl4.blockString());
        assertEquals(cl4.blockString()," 000000 000000 000000 000000 000000 000000 000000 000000");
        System.out.println(cl4.block.toString());

    }

    @Test
    void cacheLines(){
        short[] val2write = new short[]{
                1,0,1,0,2,0,2,0,
                8,9,10,11,12,13,14,15,
                9,10,12,13,14,15,16,24
        };
        for(short i = 0; i < val2write.length; i++){
            mem.write(i,val2write[i]);
        }

        // test cache lines, should
//        CacheLine cl1 = new CacheLine(0,mem);
        System.out.println(cl1.lineString(0));
        assertEquals(cl1.lineString(0),"000 000001 000000 000001 000000 000002 000000 000002 000000");

//        CacheLine cl2 = new CacheLine(1,mem);
        System.out.println(cl2.lineString(1));
        assertEquals(cl2.lineString(1),"001 000010 000011 000012 000013 000014 000015 000016 000017");

//        CacheLine cl3= new CacheLine(2,mem);
        System.out.println(cl3.lineString());
//        assertEquals(cl3.blockString()," 000011 000012 000014 000015 000016 000017 000020 000030");

//        CacheLine cl4 = new CacheLine(9,mem);
        System.out.println(cl4.lineString());
//        assertEquals(cl4.blockString()," 000000 000000 000000 000000 000000 000000 000000 000000");

    }



//    @Test
//    void cacheBlocks(){
////        setupTest();
//
//        CacheBlock b = l.block;
//        System.out.println(b.toString());
//    }
//
//    @Test
//    void Cache(){
////        setupTest();
//        c.getLine((short) 0x1234);
//
//        for(int i = 0; i < TEST_MEM_SIZE; i++){
//            short data = mem.read(i);
//            // System.out.println("Addr:" + Utility.shortToBinaryString(data,16));
//            short tag = c.getTag(data);
//            // System.out.println(" Tag:" + Utility.shortToBinaryString(tag,16));
//            assertEquals((short)((data & 0x0FF8)>>3),tag);
//        }
//    }
//
//
//    @Test
//    void getCacheLines(){
////        setupTest();
//        CacheLine l;
//        for(int i = 0; i < TEST_MEM_SIZE; i++) {
//            l = c.getLine((short) i);
//            for (int j = 0; j < 8;j++) {
//                System.out.println(l.get(i));
//            }
//        }
//    }


}
