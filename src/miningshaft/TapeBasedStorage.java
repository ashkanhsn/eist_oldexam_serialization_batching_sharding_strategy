package miningshaft;

import java.util.ArrayList;

public class TapeBasedStorage {

    private class Tape {
        int currentIndex;
        int[] content;

        public Tape(){
            currentIndex = 0;
            content = new int[maxTapeSize];
        }
    }

    ArrayList<Tape> storage;
    static int maxTapeSize = 64;

    public TapeBasedStorage(){
        storage = new ArrayList<>();
    }

    void write(int tapeIndex, ArrayList<Integer> ints, int size){
        if(tapeIndex > storage.size()){
            throw new IndexOutOfBoundsException("Trying to access too far in the local storage. TapeIndex " + tapeIndex + " while current storage contains " + storage.size() + " tapes.");
        }
        if(tapeIndex == storage.size()){
            storage.add(new Tape());
        }
        Tape tape = storage.get(tapeIndex);
        for(int i=0; i<size; i++){
            tape.content[tape.currentIndex+i] = ints.get(i);
        }
        tape.currentIndex += size;
    }

    int getCurrentIndexTape(int tapeIndex){
        if(tapeIndex > storage.size()){
            throw new IndexOutOfBoundsException("Trying to access too far in the local storage. TapeIndex " + tapeIndex + " while current storage contains " + storage.size() + " tapes.");
        }
        if(tapeIndex == storage.size()){
            storage.add(new Tape());
            return 0;
        }
        return storage.get(tapeIndex).currentIndex;
    }

    int read(int tapeIndex, int offset){
        if(tapeIndex >= storage.size() || offset >= maxTapeSize)
            throw new IndexOutOfBoundsException("Cannot read on tape "+ tapeIndex + " with offset " + offset);
        return storage.get(tapeIndex).content[offset];
    }

}
