import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/*
236. Lowest Common Ancestor of a Binary Tree
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two
nodes p and q as the lowest node in T that has both p and q as descendants
(where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]


Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.

QUESTIONS:
1) Is it a binary search tree?
2) What if one ore two values are missing?
3) where we allow a node to be a descendant of itself - means that we can return node p or q if one of them is ancestor of another?

SOLUTION
https://leetcode.com/articles/lowest-common-ancestor-of-a-binary-tree/
* */
public class Solution {

    // My BAD partially working solution
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        return preOrder(root, p, q, new SearchInfo(), new Stack<>());
    }

    private TreeNode preOrder(TreeNode node, TreeNode p, TreeNode q, SearchInfo info, Stack<TreeNode> parents) {
        if(node == null) return null;
        if(node == p || node == q) {
            if(info.oneFound && info.inStack) return  node == p ? q : p;
            else if(info.oneFound) return parents.pop();
            else info.oneFound = true;
            info.inStack = true;
        }

        parents.push(node);

        TreeNode res = preOrder(node.left, p, q, info, parents);
        if(res != null) return res;
        res = preOrder(node.right, p, q, info, parents);
        if(res != null) return res;

        TreeNode popNode = parents.pop();
        if(popNode == p || popNode == q) info.inStack = false;
        return null;
    }

    private class SearchInfo {
        boolean oneFound = false;
        boolean inStack = false;
    }


    // Not mine recursive solution
    // https://leetcode.com/articles/lowest-common-ancestor-of-a-binary-tree/
    // Our recursion function returns true if we found p or q node
    // if on some node we have two true flags, that means that this node is the LCA
    private  TreeNode result;
    public TreeNode lowestCommonAncestor2222(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;
        preOrder(root, p, q);
        return result;
    }

    private boolean preOrder(TreeNode node, TreeNode p, TreeNode q) {
        if(node == null) return false;

        int foundCurrent = node == p || node == q ? 1 : 0;
        int foundInLeft = preOrder(node.left, p, q) ? 1 : 0;
        int foundInRight = preOrder(node.right, p, q) ? 1 : 0;

        if(foundCurrent + foundInLeft + foundInRight == 2) result = node;
        return foundCurrent + foundInLeft + foundInRight > 0;
    }



    // Not mine iterative
    public TreeNode lowestCommonAncestor3333(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) return null;

        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        parents.put(root, null);

        while (!parents.containsKey(p) || !parents.containsKey(q)) {
            TreeNode curr = stack.pop();

            if(curr.left != null) {
                parents.put(curr.left, curr);
                stack.push(curr.left);
            }

            if(curr.right != null) {
                parents.put(curr.right, curr);
                stack.push(curr.right);
            }
        }

        HashSet<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parents.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parents.get(q);
        }

        return q;
    }
}
