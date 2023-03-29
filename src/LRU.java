import java.util.HashMap;
import java.util.Map;

public class LRU<K, V> {
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;
        // no <K, V> after node
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        private void update(V value) {
            this.value = value;
        }
    }

    Node head;
    Node tail;
    int limit;
    Map<K, Node> map;
    // limit is the max capacity of the cache
    public LRU(int limit) {
        map = new HashMap<>();
        this.limit = limit;
    }

    public void set(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.update(value);
            remove(node);
            append(node);
        } else {
            if (map.size() < limit) {
                Node<K, V> newNode = new Node(key, value);
                map.put(key, newNode);
                append(newNode);
            } else {
                map.remove(tail.key);
                remove(tail);
                Node<K, V> newNode = new Node(key, value);
                map.put(key, newNode);
                append(newNode);
            }
        }
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node<K, V> node = map.get(key);
        remove(node);
        append(node);
        return node.value;
    }

    // no delete
    private void remove(Node node) {
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node == head) {
            head = head.next;
        }
        if (node == tail) {
            tail = tail.prev;
        }
    }

    private void append(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
}
