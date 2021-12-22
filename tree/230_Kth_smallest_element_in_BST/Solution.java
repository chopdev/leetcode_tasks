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
import java.util.*;

public class Solution {
    // Mine solution O(logN) time, O(logN) space (because of recursion deepness)
    public int kthSmallest2222(TreeNode root, int k) {
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



    /* 
    // Why it doesn't work for this input:
    [31,30,48,3,null,38,49,0,16,35,47,null,null,null,2,15,27,33,37,39,null,1,null,5,null,22,28,32,34,36,null,null,43,null,null,4,11,19,23,null,29,null,null,null,null,null,null,40,46,null,null,7,14,17,21,null,26,null,null,null,41,44,null,6,10,13,null,null,18,20,null,25,null,null,42,null,45,null,null,8,null,12,null,null,null,null,null,24,null,null,null,null,null,null,9]
    1

    int counter = 0; 
    public int kthSmallest(TreeNode root, int k) {
        counter = 0; 
        return dfs(root, k);
    }

    public int dfs(TreeNode node, int k) {
        if (node == null) return -1;
        int leftRes = dfs(node.left, k);
        if (leftRes > 0) return leftRes;
        counter ++;
        if (counter == k) {
            return node.val;
        }
        return dfs(node.right, k);
    } */


    // My solution, counter and result can be wrapped in a class
    int counter = 0; 
    TreeNode res = null;
    public int kthSmallest(TreeNode root, int k) {
        counter = 0; 
        res = null;
        dfs(root, k);
        return res.val;
    }

    public void dfs(TreeNode node, int k) {
        if (node == null || res != null) return;
        dfs(node.left, k);
        counter ++;
        if (counter == k) {
            res = node;
        }
        dfs(node.right, k);
    }


    // Not mine, iterative in order traversal
    public int kthSmallest3333(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
    
        while (true) {
          while (root != null) {
            stack.push(root);
            root = root.left;
          }
          root = stack.pop();
          if (--k == 0) return root.val;
          root = root.right;
        }
      }

    
}
