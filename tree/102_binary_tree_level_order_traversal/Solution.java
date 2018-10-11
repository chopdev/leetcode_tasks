import java.util.ArrayList;
import java.util.List;

/**
 Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],

  3
 / \
 9  20
 /  \
 15   7

 return its level order traversal as:

 [
     [3],
     [9,20],
     [15,7]
 ]

 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>(getSize(root));
        traverse(res, root, 0);
        return res;
    }

    private void traverse(List<List<Integer>> res, TreeNode node, int level) {
        if(node == null) {
            return;
        }

        traverse(res, node.left, level + 1);
        if(res.get(level) == null)
            res.add(level, new ArrayList<>());
        res.get(level).add(node.val);
        traverse(res, node.right, level + 1);
    }

    private int getSize(TreeNode node) {
        if(node == null) return 1;

        int left = getSize(node.left);
        int right = getSize(node.right);
        return Math.max(left, right);
    }
}
