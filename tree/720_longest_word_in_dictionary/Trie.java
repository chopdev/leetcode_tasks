import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {
    public TrieNode head;

    public Trie() {
        this.head = new TrieNode('*');
    }

    public void insert(String str) {
        if(str == null || str.isEmpty()) return;
        head.insert(str);
    }

    public boolean isOneAtTime(String word) {
        if(word == null || word.isEmpty()) return false;

        TrieNode node = head;

        for (int i = 0; i < word.length(); i++) {
            if(!node.childs.containsKey(word.charAt(i))) return false;
            if(!node.childs.get(word.charAt(i)).end) return false;
            node = node.childs.get(word.charAt(i));
        }

        return true;
    }

    public String getLongest() {
        List<String> res = new ArrayList<>();
        getLongestReccursively(head, "", res);
        Collections.sort(res);
        return res.get(0);
    }

    private String getLongestReccursively(TrieNode node, String str, List<String> longest) {
        if(node.childs.size() == 0) return str;

        int max = 0;
        String res = str;
        for (TrieNode ch : node.childs.values()) {
            if(!ch.end) continue;
            res = getLongestReccursively(ch, str + ch.val, longest);
            if(res.length() > max) {
                max = res.length();
                longest.clear();
                longest.add(res);
            }
            else if(max == res.length())
                longest.add(res);
        }

        return res;
    }
}
