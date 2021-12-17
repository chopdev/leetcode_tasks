/**
 * Given a root of a tree and List of tree node. Find least common ancestor for all these nodes
 */
import java.util.*;

 public class Solution {

    public class Node {
        public Node(int val) {
            this.val = val;
        }
        public Node left;
        public Node right;
        public int val;    
    }

    // My solution, O(N) time and space O(N+K)
    private Node lca;
    public Node getLeastCommonAncestor(Node root, Node[] nodes) {
        this.lca = null;
        Set<Node> nodesSet = new HashSet<>();
        for (Node n : nodes) nodesSet.add(n);
        dfs(root, nodesSet);
        return lca;
    }

    public int dfs(Node node, Set<Node> nodes) {
        int foundNodes = 0;
        if (node.left != null)
            foundNodes += dfs(node.left, nodes);
        if (node.right != null)
            foundNodes += dfs(node.right, nodes);
        
        if (nodes.contains(node))
            foundNodes++;

        if (lca == null && foundNodes == nodes.size())
            lca = node;
        
        return foundNodes;
    }
 }