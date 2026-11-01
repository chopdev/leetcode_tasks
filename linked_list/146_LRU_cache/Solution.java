/*
146. LRU Cache
https://leetcode.com/problems/lru-cache/

Implement a fixed-capacity least recently used cache. get returns the stored
value or -1; put inserts or updates a value and evicts the least recently used
key when necessary. Both operations must run in average O(1) time.
*/

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;

    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    long capacity;
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1); // dummy values
        tail = new Node(-2, -2);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);

        if (node == null) return -1;

        removeFromTheMiddle(node);
        putToTheFront(node);

        return map.get(key).value;
    }

    public void put(int key, int value) {
        if (map.size() >= capacity && map.get(key) == null) {
            removeLastNode();
        }

        Node node;
        if (!map.containsKey(key)) {
            node = new Node(key, value);
            putToTheFront(node);
        } else {
            node = map.get(key);
            node.value = value;
            removeFromTheMiddle(node);
            putToTheFront(node);
        }

        map.put(key, node);
    }

    private void removeFromTheMiddle(Node node) {
        // remove node from the middle
        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
    }

    private void putToTheFront(Node node) {
        // put to the front
        node.next = head.next;
        node.prev = head;
        // update head and next to head refs
        head.next.prev = node;
        head.next = node;
    }

    private void removeLastNode() {
        Node last = tail.prev;
        last.prev.next = tail;
        tail.prev = last.prev;
        last.prev = null;
        last.next = null;

        map.remove(last.key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
