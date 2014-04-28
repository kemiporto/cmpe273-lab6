package edu.sjsu.cmpe.cache.client;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
	CacheServiceInterface[] servers = new CacheServiceInterface[3];
	servers[0] = new DistributedCacheService(
						 "http://localhost:3000");
	servers[1] = new DistributedCacheService(
						 "http://localhost:3001");
	servers[2] = new DistributedCacheService(
						 "http://localhost:3002");

	ShardSet shardSet = new ShardSet(servers);
        shardSet.put(1, "a");
        shardSet.put(2, "b");
        shardSet.put(3, "c");
        shardSet.put(4, "d");
        shardSet.put(5, "e");
        shardSet.put(6, "f");
        shardSet.put(7, "g");
        shardSet.put(8, "h");
        shardSet.put(9, "i");
        shardSet.put(10, "j");
        System.out.println("put(1 => foo)");

        String value = shardSet.get(1);
        System.out.println("get(1) => " + value);

        System.out.println("Existing Cache Client...");
    }

}
