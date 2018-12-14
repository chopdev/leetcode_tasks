import java.util.HashMap;

public class Node {
    HashMap<Integer, Node> adj;
    Status status;
    int val;

    public Node(int val) {
        adj = new HashMap<>();
        status = Status.Init;
        this.val = val;
    }
}
