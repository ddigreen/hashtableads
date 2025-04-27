public class MyHashTable<K, V> {
    private class HashNode {
        private K key;
        private V value;
        private HashNode next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode[] chainArray;
    private int M;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        this(11);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        return (key == null ? 0 : (key.hashCode() & 0x7fffffff) % M);
    }

    public void put(K key, V value) {
        int idx = hash(key);
        HashNode head = chainArray[idx];
        for (HashNode cur = head; cur != null; cur = cur.next) {
            if ((cur.key == null && key == null) ||
                    (cur.key != null && cur.key.equals(key))) {
                cur.value = value;
                return;
            }
        }
        HashNode node = new HashNode(key, value);
        node.next = head;
        chainArray[idx] = node;
        size++;
    }

    public V get(K key) {
        int idx = hash(key);
        for (HashNode cur = chainArray[idx]; cur != null; cur = cur.next) {
            if ((cur.key == null && key == null) ||
                    (cur.key != null && cur.key.equals(key))) {
                return cur.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int idx = hash(key);
        HashNode cur = chainArray[idx];
        HashNode prev = null;
        while (cur != null) {
            if ((cur.key == null && key == null) ||
                    (cur.key != null && cur.key.equals(key))) {
                if (prev != null) prev.next = cur.next;
                else chainArray[idx] = cur.next;
                size--;
                return cur.value;
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (HashNode head : chainArray) {
            for (HashNode cur = head; cur != null; cur = cur.next) {
                if ((cur.value == null && value == null) ||
                        (cur.value != null && cur.value.equals(value))) {
                    return true;
                }
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (HashNode head : chainArray) {
            for (HashNode cur = head; cur != null; cur = cur.next) {
                if ((cur.value == null && value == null) ||
                        (cur.value != null && cur.value.equals(value))) {
                    return cur.key;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int[] bucketSizes() {
        int[] counts = new int[M];
        for (int i = 0; i < M; i++) {
            int cnt = 0;
            for (HashNode cur = chainArray[i]; cur != null; cur = cur.next) cnt++;
            counts[i] = cnt;
        }
        return counts;
    }
}
