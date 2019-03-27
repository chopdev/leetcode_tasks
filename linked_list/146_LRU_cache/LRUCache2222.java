import java.util.HashMap;

/**
 * Not mine solution
 * My interpretation of
 https://leetcode.com/problems/lru-cache/discuss/45911/Java-Hashtable-%2B-Double-linked-list-(with-a-touch-of-pseudo-nodes)
 */
public class LRUCache2222 {
    private class DoubleLinkNode {
        int key;
        int val;
        DoubleLinkNode next;
        DoubleLinkNode prev;
    }

    private HashMap<Integer, DoubleLinkNode> keyToNode = new HashMap<>();
    private int capacity;
    private DoubleLinkNode head;
    private DoubleLinkNode tail;

    public LRUCache2222(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!keyToNode.containsKey(key))
            return -1;

        updatePriority(keyToNode.get(key));
        return keyToNode.get(key).val;
    }

    public void put(int key, int value) {
        if(keyToNode.containsKey(key)) {
            keyToNode.get(key).val =  value;
            updatePriority(keyToNode.get(key));
        }
        else {
            if(keyToNode.size() == capacity) {
                keyToNode.remove(tail.key);
                removeNode(tail);
            }

            DoubleLinkNode newNode = new DoubleLinkNode();
            newNode.key = key;
            newNode.val = value;
            keyToNode.put(key, newNode);
            addNode(newNode);
        }
    }

    private void updatePriority(DoubleLinkNode node) {
        if(node == head) return;

        removeNode(node);
        addNode(node);
    }

    private void removeNode(DoubleLinkNode node) {
        if(tail == node)
            tail = node.prev;

        if(head == node)
            head = null;

        if(node.prev != null)
            node.prev.next = node.next;
        if(node.next != null)
            node.next.prev = node.prev;
    }

    private void addNode(DoubleLinkNode node) {
        if(head == null) {
            head = node;
            tail = node;
            return;
        }

        head.prev = node;
        node.next = head;
        head = node;
    }
}
