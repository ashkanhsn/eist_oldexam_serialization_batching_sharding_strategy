package miningshaft;

import java.util.ArrayList;
import java.util.HashMap;

public class ManualReplicationStrategy implements ReplicationStrategy {
    @Override
    public void writeToRemotes(Gem g, ArrayList<Replica> replicas) {
        // TODO Q 2.1
        for (Replica r : replicas){
                boolean success = r.sendGem(g.id, g);
                if (!success){
                    throw new IllegalArgumentException("gem is broken");
                }
        }
    }

    @Override
    public void writeBatchToRemotes(HashMap<Integer, Gem> g, ArrayList<Replica> replicas) {
        // TODO Q 2.1
        for (Integer id : g.keySet()) {
            Gem gem = g.get(id);

            for (Replica r : replicas) {
                boolean success = r.sendGem(id, gem);
                if (!success){
                    throw new IllegalArgumentException("gem is broken");
                }
            }
        }
    }
}
