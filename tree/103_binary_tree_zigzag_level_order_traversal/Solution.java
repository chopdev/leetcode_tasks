/*
103. Binary Tree Zigzag Level Order Traversal
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Given the root of a binary tree, return the values level by level, alternating
between left-to-right and right-to-left order on successive levels.
*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
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

    // Solution 2: recursive DFS followed by reversing every odd-numbered level.
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traversal(root, 0, res);

        for (int i = 1; i < res.size(); i += 2) {
            Collections.reverse(res.get(i));
        }

        return res;
    }

    private void traversal(TreeNode curr, int level, List<List<Integer>> res) {
        if (curr == null) return;

        if (res.size() <= level) res.add(new ArrayList<>());
        res.get(level).add(curr.val);

        traversal(curr.left, level + 1, res);
        traversal(curr.right, level + 1, res);
    }
}
