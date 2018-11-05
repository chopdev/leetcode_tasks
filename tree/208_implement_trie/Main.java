public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        boolean res1 = trie.search("apple");   // returns true
        boolean res2 = trie.search("app");     // returns false
        boolean res3 = trie.startsWith("app"); // returns true
        trie.insert("app");
        boolean res5 = trie.search("app");     // returns true
    }
}
