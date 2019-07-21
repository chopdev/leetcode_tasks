import java.util.HashMap;

public class Node {
    public int val;
    public char symbol;
    public HashMap<Character, Node> childs;

    public  Node(char val) {
        this.symbol = val;
        childs = new HashMap<>();
    }

    public void add(String prefix, int plus, int minus) {
        val += plus;
        val -= minus;
        if(prefix.isEmpty()) {
            return;
        }

        char head = prefix.charAt(0);
        String rest = prefix.substring(1);
        if(!childs.containsKey(head))
            childs.put(head, new Node(head));

        childs.get(head).add(rest, plus, minus);
    }
}
