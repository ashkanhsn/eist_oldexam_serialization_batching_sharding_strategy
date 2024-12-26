package miningshaft;

import java.util.ArrayList;
import java.util.HashMap;

interface ReplicationStrategy {
    public void writeToRemotes(Gem g, ArrayList<Replica> replicas);
    public void writeBatchToRemotes(HashMap<Integer, Gem> g, ArrayList<Replica> replicas);
}
