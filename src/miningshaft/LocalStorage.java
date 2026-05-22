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
//        // TODO Q 1.2
//        int tapeIdx = 0;
//        int size = intsToWrite.size();
//
//        while (true){
//            int currentIdx = localstore.getCurrentIndexTape(tapeIdx);
//            int remainedSpace = TapeBasedStorage.maxTapeSize - currentIdx;
//
//            if (remainedSpace >= size){
//                localstore.write(tapeIdx, intsToWrite, size);
//            }
//            tapeIdx++;
//        }
    }

    @Override
    public void write(Gem g) {
        ArrayList<Integer> intsToWrite = serialize(g);
        writeSerializedDataToTape(intsToWrite);
    }

    public ArrayList<Integer> readSerializedDataFromTape(int id){
        ArrayList<Integer> ints = new ArrayList<>();
        // TODO Q 1.2
        int tapeIdx = 0;

        while (true){
//            int currentIdx = localstore.getCurrentIndexTape(tapeIdx);
//            if (currentIdx == 0){
//                return ints;
//            }
            int offset = 0;
            while(offset < TapeBasedStorage.maxTapeSize){
                int gemId = localstore.read(tapeIdx, offset);
                int size = localstore.read(tapeIdx, offset+1);
                if (gemId == id){
                    for (int i = 0; i < size; i++){
                        ints.add(localstore.read(tapeIdx, offset + i));
                    }
                    return ints;
                }
                offset += size;
            }
            tapeIdx++;
        }
    }

    @Override
    public Gem read(int id) {
        ArrayList<Integer> ints = readSerializedDataFromTape(id);
        return deserialize(ints);
    }
}
