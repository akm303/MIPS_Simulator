package group1.mips_simulator.components.memParts;


import group1.mips_simulator.components.Word;

public class Storage {
    public Word[] data;

    public Storage(int size) {
        // Creates an array of values, the data held in storage
        data = new Word[size];
        for(int i = 0; i < data.length; i++){
            data[i] = new Word(0);
        }
    }
}
