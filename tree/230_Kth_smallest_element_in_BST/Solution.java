/**
https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:
Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?
**/
public class Solution {
    // Mine solution O(logN) time, O(logN) space (because of recursion deepness)
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1;
        TreeNode res = new TreeNode(-1);
        getKthSmallest(root, k, new NodeInfo(), res);
        return res.val;
    }

    private void getKthSmallest(TreeNode root, int k, NodeInfo info, TreeNode res) {
        if(root == null) return;

        getKthSmallest(root.left, k, info, res);

        info.numb++;
        if(info.numb == k) {
            res.val = root.val;
            return;
        }

        getKthSmallest(root.right, k, info, res);
    }
}
