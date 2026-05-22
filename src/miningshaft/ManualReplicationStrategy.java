package miningshaft;

import java.util.ArrayList;
import java.util.HashMap;

public class ManualReplicationStrategy implements ReplicationStrategy {
    @Override
    public void writeToRemotes(Gem g, ArrayList<Replica> replicas) {
        // TODO Q 2.1
        for (Replica r : replicas){
                r.sendGem(g.id, g);
        }
    }

    @Override
    public void writeBatchToRemotes(HashMap<Integer, Gem> g, ArrayList<Replica> replicas) {
        // TODO Q 2.1
        for (Replica r:replicas){
                r.sendBatch(g);
        }
    }
}
