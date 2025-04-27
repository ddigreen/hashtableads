import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K,V>.NodeEntry> {
    private class Node {
        K key;
        V val;
        Node left, right;
        Node(K key, V val) { this.key = key; this.val = val; }
    }

    public class NodeEntry {
        private final K key;
        private final V value;
        public NodeEntry(K key, V value) {
            this.key = key; this.value = value;
        }
        public K getKey()   { return key; }
        public V getValue() { return value; }
        @Override
        public String toString() {
            return "{" + key + " = " + value + "}";
        }
    }

    private Node root;
    private int size;

    public void put(K key, V val) {
        root = put(root, key, val);
    }
    private Node put(Node x, K key, V val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        return x;
    }

    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }
    private Node delete(Node x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            size--;
            if (x.left == null)  return x.right;
            if (x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left  = t.left;
        }
        return x;
    }
    private Node min(Node x)       { return x.left == null ? x : min(x.left); }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<NodeEntry> iterator() {
        List<NodeEntry> list = new ArrayList<>();
        inorder(root, list);
        return list.iterator();
    }
    private void inorder(Node x, List<NodeEntry> out) {
        if (x == null) return;
        inorder(x.left, out);
        out.add(new NodeEntry(x.key, x.val));
        inorder(x.right, out);
    }
}
