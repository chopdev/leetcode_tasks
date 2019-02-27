import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 103. Binary Tree Zigzag Level Order Traversal
 https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

 Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]

* */
public class Solution {
    // My solution
    // Use level order traversal + reverse each odd list in result list
    // O(N) time and space
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traversal(root, 0, res);

        for (int i = 1; i < res.size(); i+= 2) {
            Collections.reverse(res.get(i));
        }
        return res;
    }

    private void traversal(TreeNode curr, int level, List<List<Integer>> res) {
        if(curr == null) return;

        if(res.size() <= level) res.add(new ArrayList<>());
        res.get(level).add(curr.val);
        traversal(curr.left, level + 1, res);
        traversal(curr.right, level + 1, res);
    }


    // Not mine
    // Cool solution, uses linked list in order to insert to beginning when level is odd
    // O(N) time and space
    public List<List<Integer>> zigzagLevelOrder2222(TreeNode root)
    {
        List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);
        return sol;
    }

    private void travel(TreeNode curr, List<List<Integer>> sol, int level)
    {
        if(curr == null) return;

        if(sol.size() <= level)
        {
            List<Integer> newLevel = new LinkedList<>();
            sol.add(newLevel);
        }

        List<Integer> collection  = sol.get(level);
        if(level % 2 == 0) collection.add(curr.val);
        else collection.add(0, curr.val);

        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }
}
