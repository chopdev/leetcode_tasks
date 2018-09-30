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
}
