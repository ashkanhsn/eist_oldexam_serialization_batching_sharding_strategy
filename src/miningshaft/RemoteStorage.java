package miningshaft;

import java.util.ArrayList;
import java.util.HashMap;

public class RemoteStorage implements Storage{

    static int ThresholdManualToAutomaticReplicas = 5;
    static int batchSize = 25;

    int nbShards;
    boolean batching;
    ReplicationStrategy manualReplicationStrategy;
    ReplicationStrategy automaticReplicationStrategy;
    
    HashMap<Integer, ArrayList<Replica>> replicas;
    HashMap<Integer, HashMap<Integer, Gem>> batches;

    RemoteStorage(int nbShards, int replicasPerShard){
        this.nbShards = nbShards;
        replicas = new HashMap<>();
        batches = new HashMap<>();
        for(int i=0; i<nbShards; i++){
            ArrayList<Replica> list = new ArrayList<>();
            for(int j=0; j<replicasPerShard; j++){
                list.add(new Replica());
            }
            replicas.put(i, list);
            batches.put(i, new HashMap<>());
        }
        manualReplicationStrategy = new ManualReplicationStrategy();
        automaticReplicationStrategy = new AutomaticReplicationStrategy();
        batching = false;
    }

    @Override
    public void write(Gem g) {
        // TODO Q 2.3 - implement the strategy pattern.
        // Hint: use the `ThresholdManualToAutomaticReplicas` to determine the
        // correct strategy to use.

        // TODO Q 3.1 - adjust your implementation to also enable batching behaviour.
        // Hint: use the `batches` property to accumulate batches until
        // they reach the `batchSize` threshold. Use the `batching` property
        // to determine whether batching should be used.

        // TODO Q 3.2 - adjust your implementation to also make use of sharding.
        // Hint: Make use of the `getShardLocation` method to determine which shard a
        // Gem belongs to.
    }

    public int getShardLocation(int id){
        // TODO Q 3.2 - implement this helper method to determine the shard a Gem belongs to.
        return 0;
    }

    public ArrayList<Replica> getShardReplicas(int shardID){
        return replicas.get(shardID);
    }

    @Override
    public Gem read(int id) {
        // You don't have to implement this.
        // Tests directly access the replica anyway.
        return null;
    }
}
