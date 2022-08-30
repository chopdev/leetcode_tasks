import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 432. All O`one Data Structure
 * https://leetcode.com/problems/all-oone-data-structure/submissions/
 *
 *
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 *
 * Implement the AllOne class:
 *
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 *
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 * */

/**
 * My solution (hard to implement without mistakes, but faster than 89% of submissions)
 * Double linked list + hashMap. Each node of linked list contains count of times words were increased + hash set of these words
 *
 * O(1) time for all operations
 * O(N) space
 * */
public class AllOne {

    // each Node contains all the keys with the same count
    class Node {
        int count;
        HashSet<String> words;
        Node prev;
        Node next;

        public Node() {
            words = new HashSet<>();
        }
    }

    Map<String, Node> wordToNodeMap = new HashMap<>();
    Node head;
    Node last;

    public AllOne() {
        head = new Node(); // dummy node
        head.count = 0;
        last = new Node(); // dummy nodde
        last.count = -1;
        head.next = last;
        last.prev = head;
    }

    public void inc(String key) {
        if (wordToNodeMap.containsKey(key)) {
            Node currentNode = wordToNodeMap.get(key);
            currentNode.words.remove(key);

            if (currentNode.next.count == currentNode.count + 1) { // add in existing node
                currentNode.next.words.add(key);
                wordToNodeMap.put(key, currentNode.next);
            } else { // add new node
                Node newNode = insertNextNode(currentNode, key, currentNode.count + 1);
                wordToNodeMap.put(key, newNode);
            }

            if (currentNode.words.isEmpty()) {
                // remove node without words
                removeNode(currentNode);
            }
        } else {
            if (head.next.count != 1) {
                Node newNode = insertNextNode(head, key, 1);
                wordToNodeMap.put(key, newNode);
            } else {
                head.next.words.add(key);
                wordToNodeMap.put(key, head.next);
            }
        }
    }

    public void dec(String key) {
        if (!wordToNodeMap.containsKey(key))
            return;
        Node currentNode = wordToNodeMap.get(key);
        currentNode.words.remove(key);

        if (currentNode.count == 1) {
            wordToNodeMap.remove(key);
        } else if (currentNode.prev != head && currentNode.prev.count == currentNode.count - 1) {
            currentNode.prev.words.add(key);
            wordToNodeMap.put(key, currentNode.prev);
        } else {
            Node newNode = insertNextNode(currentNode.prev, key, currentNode.count - 1);
            wordToNodeMap.put(key, newNode);
        }

        if (currentNode.words.isEmpty()) {
            // remove node without words
            removeNode(currentNode);
        }
    }

    public String getMaxKey() {
        if (last.prev == head)
            return ""; // no words in the data structure

        return last.prev.words.stream().findFirst().get();
    }

    public String getMinKey() {
        if (last.prev == head)
            return ""; // no words in the data structure
        return head.next.words.stream().findFirst().get();
    }

    private Node insertNextNode(Node currentNode, String key, int count) {
        Node outdatedNextNode = currentNode.next;
        Node newNode = new Node();
        newNode.count = count;
        newNode.words.add(key);
        newNode.prev = currentNode;
        newNode.next = outdatedNextNode;
        currentNode.next = newNode;
        outdatedNextNode.prev = newNode;

        return newNode;
    }

    private void removeNode(Node currentNode) {
        Node prev = currentNode.prev;
        Node next = currentNode.next;
        prev.next = next;
        next.prev = prev;
        currentNode.prev = null;
        currentNode.next = null;
    }
}
