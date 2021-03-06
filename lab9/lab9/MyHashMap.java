package lab9;

import edu.princeton.cs.algs4.In;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.assertTrue;

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

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        return buckets[index].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int index = hash(key);
        if (buckets[index].get(key) == null) size++;
        if (buckets[index].size() == 0) {
            if (loadFactor() > MAX_LF)
                incSize();
        }
        index = hash(key);
        buckets[index].put(key, value);
    }

    private void incSize() {
        ArrayMap<K, V>[] nb = new ArrayMap[buckets.length * 2];
        for (int i = 0; i < nb.length; i++) {
            nb[i] = new ArrayMap<>();
        }
        for (int i = 0; i < buckets.length; i++) {
            for (Object key : buckets[i]) {
                int index = hash((K) key);
                nb[index].put((K) key, buckets[i].remove((K) key));
            }
        }
        buckets = nb;
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
        Set<K> ks = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            for (Object key : buckets[i]) {
                ks.add((K) key);
            }
        }
        return ks;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int index = hash(key);
        V val = buckets[index].remove(key);
        if (val != null) size--;
        return val;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        int index = hash(key);
        V val = buckets[index].remove(key, value);
        if (val != null) size--;
        return val;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String args[]) {
        MyHashMap<String, Integer> mhm = new MyHashMap<>();
        mhm.put("hi" + 1, 1);
        System.out.println(mhm.get("hi" + 1));
        System.out.println(mhm.containsKey("hi" + 1));
        for (int i = 0; i < 455; i++) {
            mhm.put("hi" + i, 1);
            //make sure put is working via containsKey and get
            boolean test = mhm.get("hi" + i) != null && mhm.containsKey("hi" + i);
            int index = mhm.hash("hi" + 1);
            System.out.println(test);
        }
    }
}
