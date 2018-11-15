import java.util.HashMap;

public class Trie {
    public final TrieNode parent;

    public Trie() {
        parent = new TrieNode('*');
    }

    public void insert(String str) {
        parent.append(str);
    }

    public String getLongestPrefix() {
        StringBuilder builder = new StringBuilder();
        TrieNode tmp = parent;
        while (tmp.children.size() == 1) {
            tmp = tmp.children.entrySet().iterator().next().getValue();
            builder.append(tmp.symbol);
            if(tmp.ending) break;
        }

        return builder.toString();
    }
}

