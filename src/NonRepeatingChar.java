import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NonRepeatingChar {
    // create a doubly linked node
    static class Node {
        char value;
        Node prev;
        Node next;
        public Node(char value) {
            this.value = value;
        }
    }
    Set<Character> set;
    Map<Character, Node> map;
    Node head;
    Node tail;
    public NonRepeatingChar() {
        set = new HashSet<>();
        map = new HashMap<>();
    }

    public void read(char ch) {
        if (set.contains(ch)) return;
        if (map.containsKey(ch)) {
            set.add(ch);
            Node node = map.get(ch);
            remove(node);
        } else {
            Node newNode = new Node(ch);
            map.put(ch, newNode);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }
    }

    public Character firstNonRepeating() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    private void remove(Node node) {
        map.remove(node.value);
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (head == node) {
            head = head.next;
        }
        if (tail == node) {
            tail = tail.prev;
        }
    }
}
