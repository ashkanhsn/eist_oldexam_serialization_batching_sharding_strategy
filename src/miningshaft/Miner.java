package miningshaft;

public class Miner {
    LocalStorage blockStorage;
    Storage localStorage;
    Storage remoteStorage;

    Miner(int nbShards, int replicasPerShard){
        localStorage = new LocalStorage();
        remoteStorage = new RemoteStorage(nbShards, replicasPerShard);
    }

    public int registerGem(Gem g){
        localStorage.write(g);
        remoteStorage.write(g);
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Starting Miner...");
    }
}
