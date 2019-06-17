/**
 222. Count Complete Tree Nodes
 Given a complete binary tree, count the number of nodes.

 Note:

 Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 Example:

 Input:
 1
 / \
 2   3
 / \  /
 4  5 6

 Output: 6

* */
public class Solution {

    // My solution, O(N) time
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


    // Not mine solution, but mine interpretation
    // O((logN)^2) - Since I halve the tree in every recursive step, I have O(log(n)) steps.
    // Finding a height costs O(log(n)). So overall O(log(n)^2).
    //https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)
    // 1 << n  == 2^n
    public int countNodes2222(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right); // get the height of the most left branch in right subtree

        if(leftHeight == rightHeight) // if they are equal, last node is located in right subtree
                                                                // left subtree has 2^h - 1 nodes. + add current root node
            return (1<<leftHeight) + countNodes2222(root.right); // 1 + 2^h - 1 + count of nodes in right subtree
        else // last node is located in left subtree, so add count of nodes in right subtree (one less) + recursively left subtree nodes
            return (1<<rightHeight) + countNodes2222(root.left);
    }

    // returns the height of the most left branch
    private int getHeight(TreeNode node) {
        if(node == null) return 0;
        return 1 + getHeight(node.left);
    }

}
