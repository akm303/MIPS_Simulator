package group1.mips_simulator.components.CacheTest;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.Word;
import group1.mips_simulator.components.memParts.Cache;
import group1.mips_simulator.components.memParts.Block;
import group1.mips_simulator.components.memParts.Memory;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheTest {
    Memory mem = new Memory(Config.MEM_SIZE);
    Cache c;


    @Test
    void calculateTag(){
        //test that getTag extracts correct bits from a short
        System.out.println("Testing: Cache.calculateTag()");
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
            //System.out.println("Addr:" + Utility.shortToBinaryString(d,16)+", "+d);
            short tag = c.calculateTag(d);
            //System.out.println(" Tag:" + Utility.shortToBinaryString(tag,16)+", "+tag);
            assertEquals(expected[i],tag);
        }
        System.out.println("passed\n");
    }

    @Test
    void calculateOffset(){
        //test that we extract the correct offset bits from a short
        System.out.println("Testing: Cache.calculateOffset()");
        c = new Cache(mem);
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
        System.out.println("passed\n");
    }

    @Test
    void tagToOctalString(){
        //test that calculateTag extracts correct bits from a short
        System.out.println("Testing: Cache.tagToOctalString(tag)");
        c = new Cache(mem);
        short[] addresses = new short[]{
                0,              //0b0000_0000_0000_0000
                1,              //0b0000_0000_0000_0001
                1234,           //0b0000_0100_1101_0010
                (short)0xFF12,  //0b1111_1111_0001_0010
                (short)0x7FAB   //0b0111_1111_1010_1011
        };
        String[] expected = new String[]{
                "000",          //0      0b0000 0000_0000_0 000 -> 000 000 000
                "000",          //1      0b0000 0000_0000_0 001 -> 000 000 000
                "232",          //1234   0b0000 0100_1101_0 010 -> 010 011 010
                "742",          //FF12   0b1111 1111_0001_0 010 -> 111 100 010
                "765",          //7FAB   0b0111 1111_1010_1 011 -> 111 110 101
        };
        for(short i = 0; i < addresses.length; i++){
            short a = addresses[i];
            String tagString = c.tagToOctalString(c.calculateTag(a));
            //System.out.println("Addr:" + Utility.shortToOctalString(a,6)+", "+a);
            //System.out.println(" Tag Str:"+tagString);
            assertEquals(expected[i],tagString);
        }
        System.out.println("passed\n");
    }



    @Test
    void block_toString(){
        // test that we can print a whole block line correctly
        System.out.println("Testing: Cache.Block.toString()");
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
        System.out.println("passed\n");
    }


    @Test
    void lines_toString(){
        // Create a Cache, add some values to the cache. Test if cache prints its line correctly
        System.out.println("Testing: Cache.lineToOctalString()");
        setup1();

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
        System.out.println("passed\n");
    }



    @Test
    void cacheAfterMemory(){
        /* Test that Cache Lines properly reads its values from memory */
        System.out.println("Testing: Cache Created After Memory Written");

        setup1(); //write memory before cache created
        c = new Cache(mem);
        for(short t = 0; t < 16; t++){
            c.addLine(t);
        }
        printCache(c);
        System.out.println("passed\n");
    }

    @Test
    void cacheBeforeMemory(){
        /* Test that Cache Lines properly keeps its values equal to memory */
        System.out.println("Testing: Cache Created Before Memory Written");

        c = new Cache(mem);
        for(short t = 0; t < 16; t++){
            c.addLine(t);
        }
        setup1(); //write memory after cache created

        printCache(c);
        System.out.println();
        System.out.println("passed\n");
    }


    @Test
    void getBlock(){
        // testing getBlock()
        System.out.println("Testing Cache.getBlock()");
        setup2();

        short tag;
        c = new Cache(mem);
        short[] addresses = new short[]{
                0,1,2,3,4,5,6,7,
                8,15,
                16,17,
                48,
                200
        };
        for(short i = 0; i < addresses.length; i++){
            tag =  c.calculateTag(addresses[i]);
            Block b = c.getBlock(tag);
            System.out.println(b.toString());
        }
        System.out.println("passed\n");
    }


    @Test
    void cache_get(){
        System.out.println("Testing Cache.get() on multiple same values");
        setup2();

        short tag,a;
        c = new Cache(mem);
        short[] addresses = new short[]{
                0,1,2,3,4,5,6,7,
                8,15,
                16,17,
                48,
                200
        };
        for(short i = 0; i < addresses.length; i++){
            tag =  c.calculateTag(addresses[i]);
            c.get(addresses[i]);
            printCache(c);
        }
        System.out.println("passed\n");
    }

    @Test
    void cacheSizeIntegrityTest(){
        // testing cache maintains size
        System.out.println("Test that Cache maintains proper size");
        setup2();

        c = new Cache(mem);
        Word w;

        // add two lines totals, even when getting every word in (addresses 0 - 15)
        for(short i = 0; i < 16; i++){
            w = c.get(i);
            assertEquals(i,w.get()); //setup2, word value should equal its address for every word in memory
        }
        assertEquals(2,c.size());
        //printCache(c);

        w = c.get((short) 17); // address 16 is on a new line
        //System.out.println(w.toString_Oct());
        assertEquals(17,w.get());
        assertEquals(3,c.size());
        //printCache(c);

        // make sure size remains same when accessing block already in cache
        w = c.get((short) 5); // random address line with tag 000, after new line added
        assertEquals(5,w.get());
        assertEquals(3,c.size());

        w = c.get((short) 20); // random address in new line with tag 002
        assertEquals(20,w.get());
        assertEquals(3,c.size());


        // get out-of-order address
        w = c.get((short)48); // should get a new line with tag 006
        assertEquals(48,w.get());
        assertEquals(4,c.size());
        //System.out.println(w.toString_Oct());
        //printCache(c);

        w = c.get((short) 5); // random address within prior cache, after new line added
        assertEquals(5,w.get());
        assertEquals(4,c.size());

        //fill up the rest of cache, keep adding lines, make sure size always <= 16
        for (short i = 0; i < 1000; i++) {
            c.get(chooseRandomShort(mem.size())); // Assuming memory size is 2048 Words
            assert(c.size() <= 16);
        }
        System.out.println("passed\n");
    }


    @Test
    void cacheRandomReadWriteTest(){
        // testing random read writes operate properly
        setup2();
        int tests = 1000;

        boolean willRead;
        short addr;
        short testValue;

        short[] testArray = new short[mem.size()];
        for(short i = 0; i < testArray.length; i++ ){
            testArray[i] = i;
        }

        c = new Cache(mem);
        for(int i = 0; i < tests; i++){
            willRead = chooseRandomBool();  // randomly select whether to read/write
            addr = chooseRandomShort(mem.size()); //choose random address to read/write from/to

            if(willRead){
                testValue = c.read(addr);
                assertEquals(testArray[addr], testValue, "Test value mismatch on read after " + i + " iterations at address " + addr);
            }else{
                short newValue = chooseRandomShort(mem.size());

                testArray[addr] = newValue; // Record the written value
                c.write(addr,newValue);

                testValue = c.read(addr); // Read current value and make sure its retrieved correctly
                assertEquals(testArray[addr], testValue, "Test value mismatch on write after " + i + " iterations at address " + addr);
            }
        }

        // Ensure cache contains the most recently written values
        for (short i = 0; i < mem.size(); i++) {
            assertEquals(testArray[i], mem.read(i), "Memory value mismatch at address " + i);
        }

        System.out.println("passed\n");
    }



    /* HELPER FUNCTIONS FOR TESTING */

    // Setup functions
    void setup1(){
        // write specified values to memory
        // for use in testing macro-cache function
        System.out.println("running setup 1");

        short[] val2write_1 = new short[]{  // write to mem addresses:             addresses in octal:
                1,0,1,0,2,0,2,0,            // 0,  1,  2,  3,  4,  5,  6,  7       // 0,  1,  2,  3,  4,  5,  6,  7
                8,9,10,11,12,13,14,15,      // 8,  9, 10, 11, 12, 13, 14, 15       //10, 11, 12, 13, 14, 15, 16, 17
                9,10,12,13,14,15,16,24      //16, 17, 18, 19, 20, 21, 22, 23       //20, 21, 22, 23, 24, 25, 26, 27
        };
        for(short i = 0; i < val2write_1.length; i++){
            mem.write(i,val2write_1[i]);
        }

        short[] val2write_2 = new short[]{  // write to mem addresses:             addresses in octal:
                (short)0xFFFF, (short)0x1111,       // 27, 28                       //33, 34
                0, (short)0xABAB,                   // 29, 30                       //35, 36
                (short)0xCCDD,(short)0x4321,        // 31, 32                       //37, 40
                (short)0x0111,(short)0xABCD         // 33, 34                       //41, 42
        };
        short base = 27;
        for(short i = 0; i < val2write_2.length;i++)
            mem.write((short) (i+base), val2write_2[i]);
    }

    void setup2(){
        //write address to the memory at its location
        //for use in testing block accesses
        System.out.println("running setup 2");

        for(short i = 0; i < mem.size(); i++) {
            mem.write(i, i);
        }
    }

    // Random functions
    private boolean chooseRandomBool(){
        Random random = new Random();
        return random.nextBoolean();
    }
    private short chooseRandomShort(int n){
        Random random = new Random();
        return (short) random.nextInt(n);
    }

    // String Functions
    private void printCache(Cache c){
        System.out.println(getCacheString(c));
    }

    private String getCacheString(Cache c){
        // print each line from cache
        StringBuilder sb = new StringBuilder();
        for(short key : c.getLineTags()){
            sb.append(c.lineToOctalString(key)).append('\n');
        }
        return sb.toString();
    }

}
