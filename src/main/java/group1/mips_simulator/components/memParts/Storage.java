package group1.mips_simulator.components.memParts;
import group1.mips_simulator.components.Value;

public class Storage {
    public Value[] data;
    public Storage(int size){
        // Creates an array of values, the data held in storage
        data = new Value[size];
        for (Value value : data){
            value.set(0);
        }
    }
}