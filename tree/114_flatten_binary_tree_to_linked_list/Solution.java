/**
 114. Flatten Binary Tree to Linked List
 https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

 Given a binary tree, flatten it to a linked list in-place.

 For example, given the following tree:

 1
 / \
 2   5
 / \   \
 3   4   6
 The flattened tree should look like:

 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6
 * */
public class Solution {

    // My good solution
    // O(N) time and space
    // I firstly solved it to flatten to left side
    // but turned out that it's needed to flatten to right side
    public void flatten(TreeNode root) {
        if(root == null) return;
        dfs(root);
        setRight(root);
    }

    private TreeNode dfs(TreeNode curr) {
        if(curr.left == null && curr.right == null)
            return curr;

        TreeNode last = curr;

        if(curr.left != null)
            last = dfs(curr.left);

        if(curr.right == null)
            return last;

        last.left = curr.right;
        curr.right = null;
        return dfs(last.left);
    }

    private void setRight(TreeNode node) {
        if(node == null) return;
        node.right = node.left;
        node.left = null;
        setRight(node.right);
    }



    // not mine cool solution
    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/36977/My-short-post-order-traversal-Java-solution-for-share
    public void flatten2222(TreeNode root) {
        flatten(root,null);
    }
    private TreeNode flatten(TreeNode root, TreeNode pre) {
        if(root==null) return pre;
        pre=flatten(root.right,pre);
        pre=flatten(root.left,pre);
        root.right=pre;
        root.left=null;
        pre=root;
        return pre;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
