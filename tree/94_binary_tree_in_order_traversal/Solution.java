import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree, return the inorder traversal of its nodes' values
Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]


EXPLANATION
https://leetcode.com/articles/binary-tree-inorder-traversal/
* */
public class Solution {

    // Mine solution, Time O(N), Space O(N)
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> res = new ArrayList<>();

        addLeft(stack, root);
        while (!stack.empty()) {
            root = stack.pop();
            res.add(root.val);
            addLeft(stack, root.right);
        }

        return res;
    }

    private void addLeft(Stack<TreeNode> stack, TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }


    // Mine default recursive solution
    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helpInorder(res, root);
        return res;
    }

    public void helpInorder(List<Integer> res, TreeNode node) {
        if(node == null) return;

        helpInorder(res, node.left);
        res.add(node.val);
        helpInorder(res, node.right);
    }


    // Not mine lit bit shorter solution
    public List < Integer > inorderTraversal2222(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    // Not mine, Morris traversal - changes initial tree
    // Space O(1) except for result list O(N)
    // time O(N)
    public List <Integer> inorderTraversal3333(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
