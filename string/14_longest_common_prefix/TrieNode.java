import java.util.HashMap;

public class TrieNode {

    public boolean ending;
    public Character symbol;
    public HashMap<Character, TrieNode> children;

    public TrieNode(Character character) {
        symbol = character;
        children = new HashMap<>();
    }

    public void append(String str) {
        if(str == null || str.length() == 0) return;

        Character ch = str.charAt(0);
        TrieNode chNode = null;
        if(children.containsKey(ch)) {
            chNode = children.get(ch);
        }
        else {
            chNode = new TrieNode(ch);
            children.put(ch, chNode);
        }

        if(str.length() == 1)
            chNode.ending = true;
        else
            chNode.append(str.substring(1));
    }
}
