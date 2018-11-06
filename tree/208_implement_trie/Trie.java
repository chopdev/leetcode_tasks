/**
 https://leetcode.com/problems/implement-trie-prefix-tree/description/
 208. Implement Trie (Prefix Tree)

 Implement a trie with insert, search, and startsWith methods.

 Example:

 Trie trie = new Trie();

 trie.insert("apple");
 trie.search("apple");   // returns true
 trie.search("app");     // returns false
 trie.startsWith("app"); // returns true
 trie.insert("app");
 trie.search("app");     // returns true

 Note:

 You may assume that all inputs are consist of lowercase letters a-z.
 All inputs are guaranteed to be non-empty strings.


 SOLUTION DESCRIPTION
 https://leetcode.com/articles/implement-trie-prefix-tree/
 */

// My solution
public class Trie {

    TreeNode parent;

    /** Initialize your data structure here. */
    public Trie() {
        parent = new TreeNode('*');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        parent.add(word);
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word == null || word.length() == 0) return false;

        TreeNode node = findLastNode(word);
        return node != null && node.isEnding();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix == null || prefix.length() == 0) return false;

        TreeNode node = findLastNode(prefix);
        return node != null;
    }

    private TreeNode findLastNode(String word) {
        if(word == null || word.length() == 0) return null;
        TreeNode node = parent;

        while (node != null) {
            Character ch  = word.charAt(0);
            node = node.getChild(ch);
            if(word.length() > 1) word = word.substring(1);
            else break;
        }

        return node;
    }
}
