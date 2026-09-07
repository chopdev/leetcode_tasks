/*
103. Binary Tree Zigzag Level Order Traversal
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Given the root of a binary tree, return the values level by level, alternating
between left-to-right and right-to-left order on successive levels.
*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        boolean inOrder = true;

        while (!q.isEmpty()) {
            int size = q.size();
            Deque<Integer> layer = new ArrayDeque<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (inOrder) layer.addLast(node.val);
                else layer.addFirst(node.val);

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }

            res.add(new ArrayList<>(layer));
            inOrder = !inOrder;
        }

        return res;
    }
}
