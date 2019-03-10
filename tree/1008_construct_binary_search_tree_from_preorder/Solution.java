/**
 1008. Construct Binary Search Tree from Preorder Traversal
 https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

 Return the root node of a binary search tree that matches the given preorder traversal.
 (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has
 a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal
 displays the value of the node first, then traverses node.left, then traverses node.right.)

 Example 1:

 Input: [8,5,1,7,10,12]
 Output: [8,5,10,1,7,null,12]

 Note:

 1 <= preorder.length <= 100
 The values of preorder are distinct.
* */
public class Solution {

    private class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }

    // My solution O(N*logN) time
    public TreeNode bstFromPreorder(int[] preorder) {
        if(preorder == null || preorder.length == 0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);
        }
        return root;
    }

    private void insert(TreeNode parent, int val) {
        if(parent.val > val) {
            if(parent.left == null)
                parent.left = new TreeNode(val);
            else
                insert(parent.left, val);
        }
        else {
            if(parent.right == null)
                parent.right = new TreeNode(val);
            else
                insert(parent.right, val);
        }
    }

    // Not mine O(N) solution
    // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/submissions/
    // Give the function a bound the maximum number it will handle.
    //The left recursion will take the elements smaller than node.val
    //The right recursion will take the remaining elements smaller than bound
    int i = 0;
    public TreeNode bstFromPreorder2222(int[] A) {
        return bstFromPreorder(A, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorder(int[] A, int bound) {
        if (i == A.length || A[i] > bound) return null;
        TreeNode root = new TreeNode(A[i++]);
        root.left = bstFromPreorder(A, root.val);
        root.right = bstFromPreorder(A, bound);
        return root;
    }
}
