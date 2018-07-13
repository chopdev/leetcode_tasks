/**
 110. Balanced Binary Tree
 https://leetcode.com/problems/balanced-binary-tree/
 Given a binary tree, determine if it is height-balanced.
 For this problem, a height-balanced binary tree is defined
 as a binary tree in which the depth of the two subtrees of
 every node never differ by more than 1.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // good explanation https://leetcode.com/problems/balanced-binary-tree/discuss/35691/The-bottom-up-O(N)-solution-would-be-better
class Solution {

    // class to return two values
    class ReturnValue {
        int depth;
        boolean isBalanced;

        public ReturnValue(int depth, boolean isBalanced) { this.depth = depth; this.isBalanced = isBalanced;}
    }

    // Mine solution
    // Runtime O(N)
    public boolean isBalanced(TreeNode root) {
        return isBalancedInternal(root, 0).isBalanced;
    }

    private ReturnValue isBalancedInternal(TreeNode node, int depth)
    {
        if(node == null) return new ReturnValue(depth, true);

        depth++;
        // check left child node depth
        ReturnValue r1 = isBalancedInternal(node.left, depth);
        if(!r1.isBalanced) return  r1; // if it is not balanced deeper, return result
        // check right child node depth
        ReturnValue r2 = isBalancedInternal(node.right, depth);
        if(!r2.isBalanced) return r2; // if it is not balanced deeper, return result

        return Math.abs(r1.depth-r2.depth) > 1 ? // if left and right depth diff is bigger than one - tree is not balanced
                new ReturnValue(0, false) :
                new ReturnValue(Math.max(r1.depth, r2.depth), true); // else return deepest branch length

    }


    // Shorter solution with state, but logic similar
    private boolean isBal = true;

    public boolean isBalanced11111(TreeNode root) {
        checkDepth(root);
        return isBal;
    }

    private int checkDepth(TreeNode root) {
        if (root == null) return 0;
        int lh = checkDepth(root.left);
        int lr = checkDepth(root.right);
        if (Math.abs(lh - lr)>1) isBal = false;
        return Math.max(lh, lr) + 1;
    }
}