package miningshaft;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Gem {
    private static int latestId = 0;

    static final int unitialized = -1;
    static final int Emerald = 0;
    static final int Diamond = 1;
    static final int LapisLazuli = 2;
    static final int Quartz = 3;

    int id;
    int size;
    int type;
    int provenance;
    ArrayList<Integer> modelPoints;

    /// Creates an initialized instance of a Gem with the specified id.
    public Gem(int gemId){
        int sizePoints = ThreadLocalRandom.current().nextInt(2, 10);

        type = ThreadLocalRandom.current().nextInt(0, 4);;
        provenance = ThreadLocalRandom.current().nextInt(1, 50);
        modelPoints = new ArrayList<>(sizePoints);

        for(int i = 0; i < sizePoints; i++){
            modelPoints.add(ThreadLocalRandom.current().nextInt(1, 100));
        }

        id = gemId;
        size = 3 + modelPoints.size(); // includes this int itself
    }

    /// Creates a new uninitialized instance of a Gem.
    public Gem() {
        id = -1;
        type = -1;
        provenance = 0;
        modelPoints = new ArrayList<>(0);
        size = 4; // includes the size int itself
    }

    public String toString(){
        String res;
        res = "Gem - Size: " + Integer.toString(size) + ", type: " + type + ", provenance: " + Integer.toString(provenance) + ", 3D points: ";
        for(int i=0; i<modelPoints.size(); i++){
            res += Integer.toString(modelPoints.get(i));
            if(i != modelPoints.size()-1)
                res += ",";
        }
        res += "\n";
        return res;
    }

    public boolean equals(Gem other){
        if(this.type != other.type)
            return false;
        if(this.provenance != other.provenance)
            return false;
        if(this.modelPoints.size() != other.modelPoints.size())
            return false;
        for(int i=0; i<modelPoints.size(); i++){
            if(modelPoints.get(i) != other.modelPoints.get(i))
                return false;
        }
        return true;
    }
}
