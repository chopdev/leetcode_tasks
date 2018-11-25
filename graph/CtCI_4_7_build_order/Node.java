import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    List<Node> child;
    State state;

    public Node(String projectName) {
        name = projectName;
        child = new ArrayList<>();
        state = State.New;
    }
}

enum State {
    New,
    Visiting,
    Visited
}
