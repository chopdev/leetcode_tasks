import java.util.HashMap;



/**
 677. Map Sum Pairs
 https://leetcode.com/problems/map-sum-pairs/

 Implement a MapSum class with insert, and sum methods.

 For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer
 represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
 For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs'
 value whose key starts with the prefix.

 Example 1:
 Input: insert("apple", 3), Output: Null
 Input: sum("ap"), Output: 3
 Input: insert("app", 2), Output: Null
 Input: sum("ap"), Output: 5


other solutions
 https://leetcode.com/problems/map-sum-pairs/discuss/107513/Java-solution-Trie
* */


    // My efficient solution
    // other solutions
    // https://leetcode.com/problems/map-sum-pairs/discuss/107513/Java-solution-Trie
public class MapSum {

    private HashMap<String, Integer> wordValue;
    private Node head;

    /** Initialize your data structure here. */
    public MapSum() {
        wordValue = new HashMap<>();
        head = new Node('*');
    }

    // O(K) space and time - length of K
    public void insert(String key, int val) {
        int minus = 0;
        if(wordValue.containsKey(key))
            minus = wordValue.get(key);
        else
            wordValue.put(key, val);

        head.add(key, val, minus);
    }

    // O(K) space and time - length of K
    public int sum(String prefix) {
        int sum = 0;
        Node curr = head;
        while (!prefix.isEmpty()) {
            char first = prefix.charAt(0);
            prefix = prefix.substring(1);
            if(!curr.childs.containsKey(first)) {
               sum = 0;
               break;
            }

            curr = curr.childs.get(first);
            sum = curr.val;
        }
        return sum;
    }
}
