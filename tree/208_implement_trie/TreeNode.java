import java.util.HashMap;

public class TreeNode {
    private HashMap<Character, TreeNode> children;
    private Character value;
    private boolean isEnding;

    public TreeNode(Character val) {
        this.value = val;
        this.children = new HashMap<>();
    }

    public Character getValue() {
        return value;
    }

    public TreeNode getChild(Character ch) {
        return children.getOrDefault(ch, null);
    }

    public boolean isEnding() {
        return isEnding;
    }

    public void setIsEnding(boolean ending) {
        isEnding = ending;
    }

    public void add(String str) {
        if(str == null || str.length() == 0) return;

        Character first = str.charAt(0);
        TreeNode child = null;
        if(children.containsKey(first)) {
            child = children.get(first);
        }
        else {
            child = new TreeNode(first);
            this.children.put(first, child);
        }

        if(str.length() > 1) child.add(str.substring(1));
        else child.setIsEnding(true);
    }
}
