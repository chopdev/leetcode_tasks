import java.util.ArrayList;
import java.util.List;

/*
* Build Order: You are given a list of projects and a list of dependencies (which is a list of pairs of
projects, where the second project is dependent on the first project). All of a project's dependencies
must be built before the project is. Find a build order that will allow the projects to be built. If there
is no valid build order, return an error.

EXAMPLE
Input:
projects: a, b, c, d, e, f
dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
Output: f, e, a, b, d, c
* */
public class Solution {
    public List<String> buildOrder(String[] projects, String[][] dependencies) {
        Graph graph = initializeGraph(projects, dependencies);
        List<String> order = new ArrayList<>();
        for (Node node : graph.nodes) {
            if(node.state == State.New)
                depthFirstSearch(node, order);
        }

        return order;
    }

    private void depthFirstSearch(Node node, List<String> order) {
        if(node.child.size() == 0) {
            node.state = State.Visited;
            order.add(node.name);
            return;
        }

        if(node.state == State.Visiting)
            throw new IllegalArgumentException();

        node.state = State.Visiting;
        for (int i = 0; i < node.child.size(); i++) {
            if(node.child.get(i).state != State.Visited)
                depthFirstSearch(node.child.get(i), order);
        }

        node.state = State.Visited;
        order.add(node.name);
    }

    private Graph initializeGraph(String[] projects, String[][] dependencies) {
        Graph gr = new Graph();
        for (String pr : projects) {
            gr.addNode(pr);
        }

        for(String[] deps : dependencies) {
            Node second = gr.nameToNode.get(deps[1]);
            Node first = gr.nameToNode.get(deps[0]);
            second.child.add(first);
        }
        return gr;
    }
}
