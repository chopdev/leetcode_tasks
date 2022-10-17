import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 2385. Amount of Time for Binary Tree to Be Infected
 * https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
 *
 * You are given the root of a binary tree with unique values, and an integer start.
 * At minute 0, an infection starts from the node with value start.
 *
 * Each minute, a node becomes infected if:
 *
 * The node is currently uninfected.
 * The node is adjacent to an infected node.
 * Return the number of minutes needed for the entire tree to be infected.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,5,3,null,4,10,6,9,2], start = 3
 * Output: 4
 * Explanation: The following nodes are infected during:
 * - Minute 0: Node 3
 * - Minute 1: Nodes 1, 10 and 6
 * - Minute 2: Node 5
 * - Minute 3: Node 4
 * - Minute 4: Nodes 9 and 2
 * It takes 4 minutes for the whole tree to be infected so we return 4.
 *
 *
 *
 * Example 2:
 *
 * Input: root = [1], start = 1
 * Output: 0
 * Explanation: At minute 0, the only node in the tree is infected so we return 0.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 105
 * Each node has a unique value.
 * A node with a value of start exists in the tree.
 * */
public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * My solution
     * O(N) space and time
     *
     * Two important points and questions to ask:
     * - Each node has a unique value. (Meaning there are no couple of sources of infections)
     * - A node with a value of start exists in the tree.
     * */
    public int amountOfTime2222(TreeNode root, int start) {
        HashMap<Integer, Integer> nodeToMinutes = new HashMap<>();
        dfs(root, start, nodeToMinutes);
        return nodeToMinutes.values().stream().max((x, y) -> x - y).get();
    }

    private void dfs(TreeNode node, int start, HashMap<Integer, Integer> nodeToMinutes) {
        if (node == null) return;
        if (node.val == start) { // found start
            nodeToMinutes.put(start, 0);
        }

        if (nodeToMinutes.containsKey(node.val)) { // infect childs
            if (node.left != null) nodeToMinutes.put(node.left.val, nodeToMinutes.get(node.val) + 1);
            if (node.right != null) nodeToMinutes.put(node.right.val, nodeToMinutes.get(node.val) + 1);
        }

        dfs(node.left, start, nodeToMinutes);
        dfs(node.right, start, nodeToMinutes);

        // current node is still not infected, check if left child is infected
        if (!nodeToMinutes.containsKey(node.val) && node.left != null && nodeToMinutes.containsKey(node.left.val)) {

                nodeToMinutes.put(node.val, nodeToMinutes.get(node.left.val) + 1);
                if (node.right != null) {// infect another side of the tree
                    nodeToMinutes.put(node.right.val, nodeToMinutes.get(node.val) + 1);
                    dfs(node.right, start, nodeToMinutes);
                }
        }

        // current node is still not infected, check if right child is infected
        if (!nodeToMinutes.containsKey(node.val) && node.right != null && nodeToMinutes.containsKey(node.right.val)) {
            nodeToMinutes.put(node.val, nodeToMinutes.get(node.right.val) + 1);
            if (node.left != null) {// infect another side of the tree
                nodeToMinutes.put(node.left.val, nodeToMinutes.get(node.val) + 1);
                dfs(node.left, start, nodeToMinutes);
            }
        }
    }


    /**
     * My implementation, but not my idea
     *
     * Idea taken from here
     * https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/solutions/2456601/bfs-after-creating-graph-full-explanation/
     * https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/solutions/2462842/java-clean/
     * */
    public int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(root, graph);

        HashSet<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int levels = -1;
        while (!queue.isEmpty()) {
            int countOnLevel = queue.size();
            levels ++;
            for (int i = 0; i < countOnLevel; i++) {
                int node = queue.poll();
                seen.add(node);
                for (int adjNode : graph.get(node)) {
                    if (!seen.contains(adjNode)) queue.add(adjNode);
                }
            }
        }

        return levels;
    }

    private void buildGraph(TreeNode node, Map<Integer, List<Integer>> graph) {
        if (node == null) return;
        graph.putIfAbsent(node.val, new ArrayList<>());

        if (node.left != null) {
            graph.get(node.val).add(node.left.val);
            graph.putIfAbsent(node.left.val, new ArrayList<>());
            graph.get(node.left.val).add(node.val);
            buildGraph(node.left, graph);
        }

        if (node.right != null) {
            graph.get(node.val).add(node.right.val);
            graph.putIfAbsent(node.right.val, new ArrayList<>());
            graph.get(node.right.val).add(node.val);
            buildGraph(node.right, graph);
        }
    }


}
