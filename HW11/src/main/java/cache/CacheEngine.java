package cache;

public interface CacheEngine<K, V> {

    public void put(K key, V value);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}