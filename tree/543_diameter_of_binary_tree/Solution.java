/*
543. Diameter of Binary Tree
https://leetcode.com/problems/diameter-of-binary-tree/description/

Return the number of edges in the longest path between any two tree nodes.
The path does not have to pass through the root.
*/

class Solution {
    class Result {
        int diameter;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        Result res = new Result();
        dfs(root, res);
        return res.diameter;
    }

    int dfs(TreeNode node, Result res) {
        if (node == null) return 0;

        int leftDiameter = dfs(node.left, res);
        int rightDiameter = dfs(node.right, res);

        res.diameter = Math.max(res.diameter, leftDiameter + rightDiameter);
        int maxDepth = Math.max(leftDiameter, rightDiameter) + 1; // return max depth of this branch
        return maxDepth;
    }
}
