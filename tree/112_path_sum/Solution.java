/*
112. Path Sum
https://leetcode.com/problems/path-sum/

Determine whether a root-to-leaf path has node values that sum to targetSum.
A leaf has no children.
*/

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return dfs(root, targetSum, 0);
    }

    boolean dfs(TreeNode node, int targetSum, int currSum) {
        if (node == null) return false;

        currSum += node.val;

        if (node.left == null && node.right == null) return currSum == targetSum;

        boolean leftHasPath = dfs(node.left, targetSum, currSum);
        boolean rightHasPath = dfs(node.right, targetSum, currSum);

        return leftHasPath || rightHasPath;
    }
}
