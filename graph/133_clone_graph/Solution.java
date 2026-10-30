/*
133. Clone Graph
https://leetcode.com/problems/clone-graph/

Given a node in a connected undirected graph, return a deep copy of the graph.
Each node has an integer value and a list of neighboring nodes.
*/

import java.util.HashMap;
import java.util.Map;

/*
// Definition for a Node (provided by LeetCode).
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return node;

        Node clone = new Node();
        clone.val = node.val;

        populate(node, clone, new HashMap<>());

        return clone;
    }

    /*
     Time: O(V + E)
     Auxiliary space: O(V), excluding the cloned graph.
    */
    private void populate(Node node, Node clone, Map<Node, Node> map) {
        if (map.containsKey(node)) return;

        map.put(node, clone);
        for (Node next : node.neighbors) {
            if (map.containsKey(next)) {
                clone.neighbors.add(map.get(next)); // if node was already cloned, re-use it
            } else {
                Node nextClone = new Node();
                nextClone.val = next.val;
                clone.neighbors.add(nextClone);
                populate(next, nextClone, map);
            }
        }
    }

    // Solution 2: return the mapped copy directly from recursive calls.
    public Node cloneGraph2222(Node node) {
        if (node == null) return null;

        return clone(node, new HashMap<>());
    }

    private Node clone(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node copy = new Node(node.val);
        map.put(node, copy);

        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(clone(neighbor, map));
        }

        return copy;
    }
}
