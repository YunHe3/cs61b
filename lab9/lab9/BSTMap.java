package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */
    private V removeCollect;

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) return null;
        if (key.compareTo(p.key) > 0) {
            return getHelper(key, p.right);
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        }
        else {
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
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
        Set<K> s = new TreeSet<>();
        iteratorHelper(root, s);
        return s;
    }

    private void iteratorHelper(Node p, Set<K> s) {
        if (p == null) return;
        s.add(p.key);
        iteratorHelper(p.left, s);
        iteratorHelper(p.right, s);
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        root = removeHelper(key, root);
        return removeCollect;
    }

    /** Find the Node in the subtree of p, whose key is key.*/

    private Node removeHelper(K key, Node p) {
        if (p == null) {
            removeCollect = null;
            return null;
        }
        if (key.compareTo(p.key) > 0) {
            p.right = removeHelper(key, p.right);
        } else if (key.compareTo(p.key) < 0) {
            p.left = removeHelper(key, p.left);
        } else {
            removeCollect = p.value;
            return remove(p);
        }
        return p;
    }

    private Node remove(Node p) {
        if (p.left == null && p.right == null) {
            return null;
        } else if (p.right != null && p.left != null) {
            return findSub(p);
        } else {
            if (p.right != null) {
                return p.right;
            }
            else return p.left;
        }
    }

    private Node findSub(Node p) {
        Node sp = p.left;
        Node pre_sp = p;
        while (sp.right != null) {
            pre_sp = sp;
            sp = sp.right;
        }
        sp.right = p.right;
        sp.left = p.left;
        pre_sp.right = null;
        return sp;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value) {
            return remove(key);
        }
        return null;
    }
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String args[]) {
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        bstMap.put("hi" + 1, 1);
        bstMap.get("hi"+ 1);
    }

}
