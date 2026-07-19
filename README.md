Storage reliability for mining operations
A mining company tracks extracted gems using a computer system. Gems from various shafts are brought to a central sorting point, where a 3D scanner evaluates their value based on size and properties. Each gem’s type, origin shaft, and 3D model points must be stored. You are tasked with building a robust storage system that works reliably in harsh mining conditions and allows efficient storage and retrieval of gem data.

Part 1: Local Storage
The scanner located in the mine can store gem information locally on a storage medium. However, not every medium is suitable for the mine's harsh conditions. Therefore, the company uses storage tapes, each capable of holding a fixed-size list of integers. These tapes do not prevent data from being overwritten, so it is up to you to design a solution that stores variable-length objects safely and efficiently.

To do so, you should first carefully understand how the existing storage works. The TapeBasedStorage class presents its behavior. The image below illustrates how Gems should be stored.



<img width="683" height="178" alt="Screenshot 2026-07-08 at 13 32 31" src="https://github.com/user-attachments/assets/1ef65447-6568-4de6-8b56-ee563856821c" />

Then you should transform the Gem objects in a stream of integers that can be written onto the tapes.

Hint:

You should include a way to tell streams of integers belonging to different gems apart when reading them. One way could be to start or end the stream with a magic number. Another way could be to start the stream with the size of the stream.
Tasks:
 (De)Serialization 1 of 1 tests passing
Define and implement how the Gem objects are serialized and deserialized in LocalStorage.java.
 Storage Interactions 2 of 2 tests passing
Implement the writeSerializedDataToTape() and readSerializedDataFromTape() methods that interact with TapeBasedStorage.java. Attention: Storage must be used efficiently, so make sure there is no empty storage space between gems.
Part 2: Reliable Distributed Storage
To increase reliability, the company wants to use remote storage to maintain redundant copies of the gem information. This ensures that even if the local storage fails, the data can still be recovered from remote replicas. Your task is to implement the two replication strategies described below.

The Replica class implements the behavior of the remote replicas. In your strategy implementations, you should use the sendGem() method to interact with a Replica. This method returns true if the replica is working correctly and false if it is broken.

2.1 Manual Replication Strategy
In this approach, all replicas are treated equally, and the system should ensure that every replica contains the latest data before moving on to the next operation. Assume a crash model for the remote nodes, where nodes that fail will stay faulty.

Task:
 Replication Algorithm 0 of 1 tests passing
Inside ManualReplicationStrategy.java, implement a replication algorithm that ensures all operational replicas contain the new object before processing the next one. This involves coordinating writes to multiple replicas and handling failures gracefully.
2.2 Automatic Replication Strategy
When the number of replicas grows, the manual replication strategy becomes inefficient due to the increased coordination overhead. To address this, the company wants to use an automatic replication strategy, where replicas synchronize automatically. This means, submissions to one replica will automatically be propagated to the other replicas.

Hints:

Assume that the replication system will propagate replica changes automatically.
Be sure to handle the case where a replica fails. You may assume that faulty nodes will stay faulty.
Task:
 Primary/Secondary Replications 0 of 2 tests passing
Inside AutomaticReplicationStrategy.java, implement a replication algorithm where the system only waits for one of the replicas to acknowledge the new object before processing the next one.
2.3 Strategy Pattern
As the number of replicas varies across deployments, maintaining both performance and reliability requires flexibility in the replication approach. Using a single static strategy is no longer sufficient. To address this, the system must be able to dynamically select the appropriate replication strategy based on the current configuration.

Task:
 Strategy Pattern 0 of 1 tests passing
Inside RemoteStorage.java, implement the strategy pattern inside the write(Gem g) method to dynamically choose between the peer replica strategy and the hierarchical replica strategy. The choice should depend on the number of replicas: use the hierarchical strategy for a large number of replicas and the peer strategy for a smaller number. You should use the variable ThresholdManualToAutomaticReplicas (declared in RemoteStorage) to make that decision.
Part 3: Performant Distributed Storage
As the system scales, performance becomes a critical concern. The company wants to optimize the storage system to handle a high volume of write requests efficiently.

3.1 Batching
To improve write efficiency, the system should be able to aggregate multiple requests and send them as a single batch. This reduces the number of network calls, resulting in significantly higher throughput and lower latency under high load.

Task:
 Batching Mechanism 0 of 1 tests passing
Inside RemoteStorage.java, enhance the write(Gem g) method and implement a batching mechanism for write requests. Instead of sending each write request individually, group multiple requests together and process them as a batch if batching is enabled. This reduces the overhead of network communication and improves throughput. You should use the batchSize variable for accumulating the batches and the batching variable to determine whether batching is enabled.
3.2 Sharding
As the volume of data grows, having just a single storage node can become a bottleneck. Sharding addresses this by splitting the data across multiple storage nodes (shards), allowing for parallel processing and improved scalability. Each shard is responsible for a subset of the data and can operate independently.

Hint:

You may use any algorithm to distribute IDs onto shards. The simplest could be a simple round-robin distribution strategy, where incoming items are assigned to shards in a rotating sequence (e.g., first to shard 1, second to shard 2, third to shard 3, then back to shard 1, and so on).
Task:
 Sharding Mechanism 0 of 1 tests passing
Inside RemoteStorage.java, enhance the write(Gem g) method and implement a sharding mechanism to distribute the data across multiple storage nodes. Each shard is replicated in multiple replicas and the strategies implemented in Q2 should still work within each shard.
