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

        list.add(g.id);
        list.add(g.size);
        list.add(g.type);
        list.add(g.provenance);
        list.addAll(g.modelPoints);
        return list;
    }

    public Gem deserialize(ArrayList<Integer> list){
        Gem g = new Gem();
        // TODO Q 1.1
        g.id = list.get(0);
        g.size = list.get(1);
        g.type = list.get(2);
        g.provenance = list.get(3);

        g.modelPoints = new ArrayList<>();
        for (int i=4 ; i < list.size(); i++){
            g.modelPoints.add(list.get(i));
        }

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
