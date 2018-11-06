package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Sherry Nie
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

        @Override
        public String toString() {
            String s = "";
            if (this.left != null)
                s += this.left.toString() + ",";
            s += "(" + this.key + ", "+ this.value + ")";
            if (this.right != null) {
                s += "," + this.right.toString() ;
            }
            return s;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }


    @Override
    public String toString() {
        return root.toString();
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
        if (p == null)
            return null;
        else if (p.key.compareTo(key) == 0) {
            return p.value;
        }
        else if (p.key.compareTo(key) > 0) {
            if (p.left != null)
            return getHelper(key, p.left);
        }
        else {
            if (p.right != null)
            return getHelper(key, p.right);
        }
        return null;
    }



    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }


//    private Node searchhelper (K key, Node p) {
//        if (p == null)
//            return null;
//        else if (p.key.compareTo(key) == 0) {
//            return p;
//        }
//        else if (p.key.compareTo(key) > 0) {
//            if (p.left != null)
//                return searchhelper(key, p.left);
//        }
//        else {
//            if (p.right != null)
//                return searchhelper(key, p.right);
//        }
//        return null;
//    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            p = new Node(key, value);
            size++;
//            System.out.println(p.value);
            return p;
        }
        else if (p.key.compareTo(key) == 0) {
            if (p.value.equals(value))
                return null;
            else {
                p.value = value;
                return p;
            }
        }
        else if (p.key.compareTo(key) > 0) {
            if (p.left == null) {
                p.left = new Node(key, value);
                size++;
                return p;
            }
            else {
                return putHelper(key, value, p.left);
            }

        }
        else if (p.key.compareTo(key) < 0) {
            if (p.right == null) {
                p.right = new Node(key, value);
                size++;
                return p;
            }
            else {
                return putHelper(key, value, p.right);
            }
        }
        return null;
    }

    /**
     * Returns a BSTMap rooted in p with all (KEY, VALUE) of Node n added as a key-value mapping.
     * @param n
     * @param p
     * @return
     */
    private Node PutHelper (Node n, Node p) {
        if (n == null) {
            return p;
        }
        else {
            putHelper(n.key, n.value, p);
            if (n.left != null) {
                return PutHelper(n.left, p);
            }
            if (n.right != null) {
                return PutHelper(n.right, p);
            }
        }
        return p;
    }
    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (root == null)
            root = putHelper(key, value, root);
        else
        putHelper(key, value, root);

//        System.out.println(root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    private Set<K> keySetHelper (Node p) {
        Set<K> keys = new HashSet<>();
        Node n = p;
        if (n != null) {
            keys.add(n.key);
            if (n.left != null) {
                keys.addAll(keySetHelper(p.left));
            }
            if (n.right != null) {
                keys.addAll(keySetHelper(p.right));
            }

        }
        return keys;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySetHelper(root);
    }


    private Node GetHelper (K key, Node p, boolean detach) {
        if (p == null)
            return null;
        else if (p.key.compareTo(key) == 0) {
            return p;
        }
        else if (p.key.compareTo(key) > 0) {
            if (p.left != null){
                Node n = GetHelper(key, p.left,detach);
                if (n == p.left && detach) {
                    p.left = null;
                }
                return n;
            }

        }
        else {
            if (p.right != null){
                Node n = GetHelper(key, p.right,detach);
                if (n == p.right && detach) {
                    p.right = null;
                }
                return n;
            }
        }
        return null;
    }
    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node n = GetHelper(key, root,true);
          if (n != null) {
            if (n.left != null) {
                Node left = n.left;
                PutHelper(left, root);
            }
            if (n.right != null) {
                Node right = n.right;
                PutHelper(right, root);
            }
            V value = n.value;
            size--;
            return value;
        }
        return null;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        Node n = GetHelper(key, root,false);
        if (n != null && n.value == value){
            return remove(key);
        }
        return null;
    }

//    public K Next (Node p, ArrayList<K> list) {
//        if (p == null)
//            return  null;
//        if (p.left != null) {
//            K key = Next(p.left, list);
//            list.add(key);
//        }
//        if (p.right != null) {
//            K key = Next(p.right, list);
//            list.add(key);
//        }
//        return p.key;
//    }
//
//    public class KeyIterator<K> implements Iterator<K> {
//        int counter = 0;
//        @Override
//        public boolean hasNext() {
//            return counter != size - 1;
//        }
//
//        @Override
//        public K next() {
//            return
//        }
//    }
    @Override
    public Iterator<K> iterator() {
       return keySet().iterator();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("starChild", 5);
        b.put("KISS", 5);
        b.put("KISS",4);
        b.put("MISS",1);
        b.put("MUCH",2);
        b.put("yOU", 3);
        b.put("Apple", 8);
//        for(String s : b.keySet()) {
//            System.out.println(s);
//        }
//        System.out.println();
        Iterator<String> keys = b.iterator();
        while (keys.hasNext()){
            System.out.println(keys.next());
        }
//        System.out.println("size is " + b.size);
//        System.out.println(b.toString());
//        System.out.println();
//        b.remove("MISS",2);
//        System.out.println("size is " + b.size);
//        System.out.println(b.toString());
//        System.out.println();
//        b.remove("MISS",1);
////        System.out.println("The value removed is " + b.remove("MISS"));
//        System.out.println("size is " + b.size);
//        System.out.println(b.toString());
    }
}
