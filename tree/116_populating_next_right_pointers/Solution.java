/*
116. Populating Next Right Pointers in Each Node
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/


Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
Example:

Given the following perfect binary tree,

     1
   /  \
  2    3
 / \  / \
4  5  6  7

After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
* */

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

public class Solution {

    // My solution
    // Time O(N), O(logN) space - deepness of recursion
    public void connect(TreeLinkNode root) {
        traverse(root, null);
    }

    public void traverse(TreeLinkNode curr, TreeLinkNode right) {
        if(curr == null) return;
        curr.next = right;
        traverse(curr.right, right == null ? null : right.left);
        traverse(curr.left, curr.right);
    }

    // Not mine solution
    // here we reuse next pointer for childs
    public void connect2222(TreeLinkNode root) {
        if(root == null)
            return;

        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
    }

    // Not mine iterative solution
    public void connect3333(TreeLinkNode root) {
        TreeLinkNode level_start=root; // memorize start of the level (the most left node of level)
        while(level_start!=null){
            TreeLinkNode cur=level_start;
            while(cur!=null){ // set values for childs and move curr pointer one step right from the most left pointer
                if(cur.left!=null) cur.left.next=cur.right;
                if(cur.right!=null && cur.next!=null) cur.right.next=cur.next.left;

                cur=cur.next;
            }
            level_start=level_start.left; // set next level_start
        }
    }
}
