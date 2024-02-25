package group1.mips_simulator.components;

import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.instructionParts.Field;
import group1.mips_simulator.components.memParts.Memory;
import org.junit.jupiter.api.Test;


class ComputerTest {

    @Test
    void calculateEA() {
        Field inputIx = new Field(0, 2);
        Field inputAddress = new Field(100, 5);
        Field inputI = new Field(0, 1);

        Computer underTest = new Computer();
        underTest.memory = new Memory();
        underTest.memory.write((short) 100, (short) 50);

        // c(Address Field)
        // c(100)
        // 50
        // assertEquals(50, underTest.calculateEA(inputIx, inputAddress, inputI).get());
    }

    @Test
    void calculateEA_indirection() {
        Field inputIx = new Field(0, 2);
        Field inputAddress = new Field(100, 5);
        Field inputI = new Field(1, 1);

        Computer underTest = new Computer();
        underTest.memory = new Memory();
        underTest.memory.write((short) 100, (short) 50);
        underTest.memory.write((short) 50, (short) 25);

        // c(c(Address Field))
        // c(c(100))
        // c(50)
        // 25
        // assertEquals(25, underTest.calculateEA(inputIx, inputAddress, inputI).get());
    }

    @Test
    void calculateEA_indexReg() {
        Field inputIx = new Field(2, 2);
        Field inputAddress = new Field(100, 5);
        Field inputI = new Field(0, 1);

        Computer underTest = new Computer();

        // Set up index registers
        underTest.cpu.regfile.IXR = new Register[]{
                new Register((short) 5),  // 00
                new Register((short) 10), // 01
                new Register((short) 15), // 10 **
                new Register((short) 20) // 11
        };

        underTest.memory = new Memory();
        underTest.memory.write((short) 100, (short) 50);

        // c(IX) + c(Address Field)
        // c(2) + c(100)
        // 15 + 50
        // assertEquals(65, underTest.calculateEA(inputIx, inputAddress, inputI).get());
    }

    @Test
    void calculateEA_indexReg_Indirect() {
        Field inputIx = new Field(1, 2);
        Field inputAddress = new Field(100, 5);
        Field inputI = new Field(1, 1);

        Computer underTest = new Computer();

        // Set up registers
        underTest.cpu.regfile.IXR = new Register[]{
                new Register((short) 5),  // 00
                new Register((short) 10), // 01 **
                new Register((short) 15), // 10
                new Register((short) 20)  // 11
        };

        underTest.memory = new Memory();
        underTest.memory.write((short) 100, (short) 50);
        underTest.memory.write((short) (50 + 10), (short) 33);

        // c(c(IX) + c(Address Field))
        // c(c(1) + c(100))
        // c(10 + 50)
        // c(60)
        // 33
        // assertEquals(33, underTest.calculateEA(inputIx, inputAddress, inputI).get());
    }
}