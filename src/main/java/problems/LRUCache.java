package problems;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class ListNode {
        public int val;
        public int key;
        public ListNode next;
        public ListNode prev;
        public ListNode(int val, int key, ListNode next, ListNode prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
            this.key = key;
        }

        public ListNode(int key, int val) {
            this.val = val;
            this.key = key;
        }
    }

    private int capacity;
    private Map<Integer, ListNode> map;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    public void removeNode(ListNode node) {

        if (node == tail) {
            tail = tail.prev;
        }

        if (node == head) {
            head = head.next;
        }

        ListNode prev = node.prev;
        ListNode next = node.next;

        if (prev != null) {
            prev.next = next;
        }

        if (next != null) {
            next.prev = prev;
        }
        node.next = null;
        node.prev = null;
    }

    public void addNode(ListNode node) {
        if (head != null && tail != null) {
            node.next = head;
            head.prev = node;
            head = node;
        } else {
            head = node;
            tail = node;
        }
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            if (head != node) {
                removeNode(node);
                addNode(node);
            }
            return node.val;
        }

        return -1;
    }

    public void put(int key, int value) {
        ListNode node = new ListNode(key, value);

        if (map.containsKey(key)) {
            ListNode toRemove = map.get(key);
            removeNode(toRemove);
            map.remove(key);
        }

        if (map.size() < capacity) {
            map.put(key, node);
            addNode(node);
        } else {
            ListNode toRemove = map.get(tail.key);
            map.remove(tail.key);
            removeNode(toRemove);
            map.put(key, node);
            addNode(node);
        }

    }
}
