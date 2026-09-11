/* 
98. Validate Binary Search Tree
https://leetcode.com/problems/validate-binary-search-tree/

Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Input: root = [2,1,3]
Output: true


Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/



class Solution {

    // Solved without hints after 8 failing submissions; initially missed the min-max range requirement.
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        long minValue = Integer.MIN_VALUE;
        long maxValue = Integer.MAX_VALUE;
        return !isInvalid(root, minValue - 1, maxValue + 1);
    }

    boolean isInvalid(TreeNode node, long min, long max) {
        if (node == null) return false;

        if (node.val <= min || node.val >= max) return true;

        boolean leftInvalid = isInvalid(node.left, min, node.val);
        boolean rightInvalid = isInvalid(node.right, node.val, max);

        return leftInvalid || rightInvalid;
    }

    static class TreeNode {
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

    // Solution 2: previous implementation using nullable bounds.
    public boolean isValidBST2(TreeNode node) {
        if (node == null) return true;
        return isValidBST(node.left, null, node.val) && isValidBST(node.right, node.val, null);
    }

    public boolean isValidBST(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        if ((min != null && node.val <= min) || (max != null && node.val >= max)) return false;
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }
}
