import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;
import com.google.common.hash;

public class ConsistentHash {

  private final HashFunction hashFunction;
  private final int numberOfReplicas;
  private final SortedMap<Integer, DistributedCacheService> circle =
    new TreeMap<Integer, T>();

  public ConsistentHash(HashFunction hashFunction,
    int numberOfReplicas, Collection<DistributedCacheService> nodes) {

    this.hashFunction = hashFunction;
    this.numberOfReplicas = numberOfReplicas;

    for (DistributedCacheService node : nodes) {
      add(node);
    }
  }

  public void add(DistributedCacheService node) {
    for (int i = 0; i < numberOfReplicas; i++) {
      circle.put(hashFunction.hash(node.toString() + i),
        node);
    }
  }

  public void remove(DistributedCacheService node) {
    for (int i = 0; i < numberOfReplicas; i++) {
      circle.remove(hashFunction.hash(node.toString() + i));
    }
  }

  public DistributedCacheService get(Object key) {
    if (circle.isEmpty()) {
      return null;
    }
    int hash = hashFunction.hash(key);
    if (!circle.containsKey(hash)) {
      SortedMap<Integer, T> tailMap =
        circle.tailMap(hash);
      hash = tailMap.isEmpty() ?
             circle.firstKey() : tailMap.firstKey();
    }
    return circle.get(hash);
  } 

}