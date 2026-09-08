/*
199. Binary Tree Right Side View
https://leetcode.com/problems/binary-tree-right-side-view/

Given the root of a binary tree, return the values visible from its right side,
ordered from top to bottom.
*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int layerSize = q.size();

            for (int i = 0; i < layerSize; i++) {
                TreeNode node = q.poll();
                if (i == layerSize - 1) {
                    res.add(node.val);
                }

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }

        return res;
    }

    // Solution 2: DFS visits the rightmost node at each level first.
    public List<Integer> rightSideView2(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);

        return res;
    }

    private void dfs(TreeNode node, int layer, List<Integer> res) {
        if (node == null) return;

        if (res.size() - 1 < layer) {
            res.add(node.val);
        }

        dfs(node.right, layer + 1, res);
        dfs(node.left, layer + 1, res);
    }
}
