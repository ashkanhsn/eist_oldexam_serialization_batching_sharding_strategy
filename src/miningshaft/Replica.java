package miningshaft;

import java.util.HashMap;

public class Replica {
    static int nextAvailableID = 0;
    private
        HashMap<Integer, Gem> storage;
        final int ID;
        boolean broken;
    public
        Replica(){
            ID = nextAvailableID++;
            storage = new HashMap<>();
        }

        public boolean sendGem(int id, Gem g) {
            if(broken)
                return false;
            storage.put(id, g);
            return true;
        }

        public boolean sendBatch(HashMap<Integer, Gem> gems){
            for(Integer i: gems.keySet()){
                if(!sendGem(i, gems.get(i)))
                    return false;
            }
            return true;
        }

        public int getID() {
            return ID;
        }

        public boolean isBroken(){
            return broken;
        }

        public Gem read(int id){
            Gem g = storage.get(id);
            if(g == null){
                throw new IndexOutOfBoundsException("Cannot find gem with id " + id);
            }
            return g;
        }
}
