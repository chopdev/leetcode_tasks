/**
 * 113. Path Sum II
 * https://leetcode.com/problems/path-sum-ii/

 Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node
 values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

 Example 1:

 Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 Output: [[5,4,11,2],[5,8,4,5]]
 Explanation: There are two paths whose sum equals targetSum:
 5 + 4 + 11 + 2 = 22
 5 + 8 + 4 + 5 = 22


 Example 2:

 Input: root = [1,2,3], targetSum = 5
 Output: []
 Example 3:

 Input: root = [1,2], targetSum = 0
 Output: []


 Constraints:

 The number of nodes in the tree is in the range [0, 5000].
 -1000 <= Node.val <= 1000
 -1000 <= targetSum <= 1000
 * */
import java.util.ArrayList;
import java.util.List;

class Solution {
    class TreeNode {
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

    // My solution, O(N) time, O(N) extra space for recursion call stack
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, targetSum, 0, new ArrayList<>(), res);
        return res;
    }

    public void dfs(TreeNode node, int targetSum, int sum, List<Integer> rootToLeaf, List<List<Integer>> res) {
        sum += node.val;
        rootToLeaf.add(node.val);
        if (node.left == null && node.right == null) { // leaf node
            if (targetSum == sum) res.add(new ArrayList<>(rootToLeaf));
        } else {
            if (node.left != null) dfs(node.left, targetSum, sum, rootToLeaf, res);
            if (node.right != null) dfs(node.right, targetSum, sum, rootToLeaf, res);
        }

        rootToLeaf.remove(rootToLeaf.size() - 1);
    }
}