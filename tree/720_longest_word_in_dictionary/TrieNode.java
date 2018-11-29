import java.util.HashMap;

public class TrieNode {
    public Character val;
    public boolean end;
    public HashMap<Character, TrieNode> childs;

    public TrieNode(Character val) {
        this.val = val;
        this.childs = new HashMap<>();
    }

    public void insert(String str) {
        if(str == null) return;

        Character curr = str.charAt(0);
        if(!childs.containsKey(curr)) {
            childs.put(curr, new TrieNode(curr));
        }

        if(str.length() > 1) childs.get(curr).insert(str.substring(1));
        else childs.get(curr).end = true;
    }
}
