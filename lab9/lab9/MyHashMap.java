package lab9;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }


    private void resize (int size) {
        ArrayMap<K, V>[] nbuckets = new ArrayMap[size];
        for (int i = 0; i < buckets.length; i++) {
            nbuckets[i] = buckets[i];
        }
        for (int i = buckets.length; i < nbuckets.length; i++) {
            nbuckets[i] = new ArrayMap<>();
        }
        buckets = nbuckets;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return buckets[hash(key)].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Null key not allowed.");
        }
        if (value == null) {
            throw new IllegalArgumentException("Null values not allowed.");
        }
        if (loadFactor() > MAX_LF) {
            resize(buckets.length * 2);
        }
        if (get(key) == value) {
            return;
        }

        if (get(key) != null) {
            buckets[hash(key)].put(key, value);
        }
        else {
            buckets[hash(key)].put(key, value);
            size++;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            keyset.addAll(buckets[i].keySet());
        }
        return keyset;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (buckets[hash(key)].remove(key) != null) {
            size--;
            return buckets[hash(key)].remove(key);
        }
        else {
            return null;
        }
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (buckets[hash(key)].remove(key, value) != null) {
            size--;
            return buckets[hash(key)].remove(key, value);
        }
        else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();
        hashMap.put("i", 1);
        hashMap.put("love", 2);
        hashMap.put("you", 3);
        hashMap.put("!", 4);
        hashMap.remove("you");
        hashMap.remove("i", 1);
        System.out.println(hashMap.size);
    }
}
