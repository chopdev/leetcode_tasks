import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    List<Node> nodes;
    HashMap<String, Node> nameToNode;

    public Graph() {
        nodes = new ArrayList<>();
        nameToNode = new HashMap<>();
    }

    public void addNode(String name) {
        Node curr = new Node(name);
        nodes.add(curr);
        nameToNode.put(name, curr);
    }
}
