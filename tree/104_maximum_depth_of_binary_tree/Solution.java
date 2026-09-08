/*
104. Maximum Depth of Binary Tree
https://leetcode.com/problems/maximum-depth-of-binary-tree/

Given the root of a binary tree, return the number of nodes on its longest
path from the root to a leaf.
*/

class Solution {
    public int maxDepth(TreeNode root) {
        return dfs(root);
    }

    int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftDepth = dfs(node.left);
        int rightDepth = dfs(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
