import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2096. Step-By-Step Directions From a Binary Tree Node to Another
 * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 *
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n.
 * You are also given an integer startValue representing the value of the start node s, and a different integer
 * destValue representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path
 * as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 *
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 *
 * Example 1:
 *
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 *
 *
 * Example 2:
 *
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 2 <= n <= 10^5
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * 1 <= startValue, destValue <= n
 * startValue != destValue
 * */
public class Solution {
    public class TreeNode {
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

    private TreeNode commonParent;

    /**
     * My solution
     * The idea is to find closest common parent of two nodes. Once we have common parent, just find path from it to start and dest nodes.
     * O(N) time and space
     *
     * I don't like to put commonParent into state, but it makes code simpler to look, not prod version.
     * */
    public String getDirections(TreeNode root, int startValue, int destValue) {
        commonParent = null;
        findLeastCommonParent(root, startValue, destValue);

        List<String> pathToStart = new ArrayList<>();
        findPath(commonParent, startValue, pathToStart);
        char[] upPath = new char[pathToStart.size()];
        Arrays.fill(upPath, 'U'); // replace all steps in the start direction to "U"

        List<String> pathToDest = new ArrayList<>();
        findPath(commonParent, destValue, pathToDest);

        return new String(upPath) + String.join("", pathToDest);
    }

    private boolean findLeastCommonParent(TreeNode node, int startValue, int destValue) {
        if (node == null) return false;
        boolean isCurrentNode = node.val == startValue || node.val == destValue;
        boolean isLeftSide = findLeastCommonParent(node.left, startValue, destValue);
        boolean isRightSide = findLeastCommonParent(node.right, startValue, destValue);

        if ((isCurrentNode && isLeftSide) || (isCurrentNode && isRightSide) || (isRightSide && isLeftSide)) {
            commonParent = node;
        }
        return isCurrentNode || isLeftSide || isRightSide;
    }

    private boolean findPath(TreeNode node, int value, List<String> path) {
        if (node == null) return false;
        if (node.val == value) return true;

        path.add("L");
        boolean isLeftSide = findPath(node.left, value, path);
        if (isLeftSide) return true;

        path.remove(path.size() - 1); // remove last "L"
        path.add("R");
        boolean isRightSide = findPath(node.right, value, path);
        if (isRightSide) return true;
        path.remove(path.size() - 1); // remove last "R"

        return false;
    }





    /**
     * Not my solution
     * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/solutions/1612105/3-steps/?orderBy=most_votes
     *
     * 1. Build directions for both start and destination from the root.
     * Say we get "LLRRL" and "LRR".
     *
     * 2. Remove common prefix path.
     * We remove "L", and now start direction is "LRRL", and destination - "RR"
     *
     * 3. Replace all steps in the start direction to "U" and add destination direction.
     * The result is "UUUU" + "RR".
     * */
    public String getDirections2222(TreeNode root, int startValue, int destValue) {
        StringBuilder s = new StringBuilder(), d = new StringBuilder();
        find(root, startValue, s);
        find(root, destValue, d);
        int i = 0, max_i = Math.min(d.length(), s.length());
        while (i < max_i && s.charAt(s.length() - i - 1) == d.charAt(d.length() - i - 1))
            ++i;
        return "U".repeat(s.length() - i) + d.reverse().substring(i);
    }

    private boolean find(TreeNode n, int val, StringBuilder sb) {
        if (n.val == val)
            return true;
        if (n.left != null && find(n.left, val, sb))
            sb.append("L");
        else if (n.right != null && find(n.right, val, sb))
            sb.append("R");
        return sb.length() > 0;
    }
}
