import java.util.ArrayList;
import java.util.List;

public class Node {
    List<Edge> adjacent;
    int val;

    public Node(int value) {
        adjacent = new ArrayList<>();
        val = value;
    }
}
