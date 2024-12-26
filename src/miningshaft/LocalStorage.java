package miningshaft;

import java.util.ArrayList;

public class LocalStorage implements Storage {

    TapeBasedStorage localstore;

    public LocalStorage() {
        localstore = new TapeBasedStorage();
    }

    public ArrayList<Integer> serialize(Gem g){
        ArrayList<Integer> list = new ArrayList<>();
        // TODO Q 1.1
        return list;
    }

    public Gem deserialize(ArrayList<Integer> list){
        Gem g = new Gem();
        // TODO Q 1.1
        return g;
    }

    public void writeSerializedDataToTape(ArrayList<Integer> intsToWrite){
        // TODO Q 1.2
    }

    @Override
    public void write(Gem g) {
        ArrayList<Integer> intsToWrite = serialize(g);
        writeSerializedDataToTape(intsToWrite);
    }

    public ArrayList<Integer> readSerializedDataFromTape(int id){
        ArrayList<Integer> ints = new ArrayList<>();
        // TODO Q 1.2
        return ints;
    }

    @Override
    public Gem read(int id) {
        ArrayList<Integer> ints = readSerializedDataFromTape(id);
        return deserialize(ints);
    }
}
