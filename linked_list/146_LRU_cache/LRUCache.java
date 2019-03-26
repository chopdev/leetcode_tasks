/**
 146. LRU Cache
 https://leetcode.com/problems/lru-cache/

 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

 Follow up:
 Could you do both operations in O(1) time complexity?

 Example:
        LRUCache cache = new LRUCache(2);
         cache.put(1, 1);
         cache.put(2, 2);
         cache.get(1);       // returns 1
         cache.put(3, 3);    // evicts key 2
         cache.get(2);       // returns -1 (not found)
         cache.put(4, 4);    // evicts key 1
         cache.get(1);       // returns -1 (not found)
         cache.get(3);       // returns 3
         cache.get(4);       // returns 4
 */


import java.util.HashMap;
import java.util.LinkedList;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// Mine not efficient solution
public class LRUCache {

    HashMap<Integer, Integer> keyValue;
    LinkedList<Integer> keysList;
    int capacity;

    public LRUCache(int capacity) {
        keyValue = new HashMap<>();
        keysList = new LinkedList<>();
        this.capacity = capacity;
    }

    // O(N)
    public int get(int key) {
        if(!keyValue.containsKey(key))
            return -1;

        updatePriority(key);
        return keyValue.get(key);
    }

    // O(1) on insert, O(N) update
    public void put(int key, int value) {
        if(keyValue.containsKey(key)) {
            keyValue.put(key, value);
            updatePriority(key);
        }
        else {
            if(keyValue.size() == capacity) {
                int evicted = keysList.removeLast();
                keyValue.remove(evicted);
            }

            keyValue.put(key, value);
            keysList.add(0, key);
        }
    }

    private void updatePriority(int key) {
        keysList.removeFirstOccurrence(key);
        keysList.add(0, key);
    }
}
