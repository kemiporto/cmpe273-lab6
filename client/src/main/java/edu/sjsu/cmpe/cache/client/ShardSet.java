package edu.sjsu.cmpe.cache.client;

public class ShardSet implements CacheServiceInterface {

    private int size = 0;
    private CacheServiceInterface[] servers;

    public ShardSet(CacheServiceInterface[] servers) {
	size = servers.length;
	this.servers = servers;
    }

    public String get(long key) {
	return servers[(int)(key % size)].get(key);
    }

    public void put(long key, String value) {
	servers[(int)(key % size)].put(key, value);
    }
	
}