import java.util.LinkedList;
import java.util.Queue;

/**
 * 298. Binary Tree Longest Consecutive Sequence
 * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/?envType=study-plan-v2&id=premium-algo-100
 *
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 * A consecutive sequence path is a path where the values increase by one along the path.
 * Note that the path can start at any node in the tree, and you cannot go from a node to its parent in the path.
 *
 * Example 1:
 *
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 *
 *
 * Example 2:
 *
 * Input: root = [2,null,3,2,null,1]
 * Output: 2
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 10^4].
 * -3 * 10^4 <= Node.val <= 3 * 10^4
 * */
public class Solution {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

     /**
      * My DFS solution
      * O(N) time and space
      * */
    public int longestConsecutive(TreeNode root) {
         if (root == null) return 0;
        int leftLen = getMaxLen(root.left, root.val, 1);
        int rightLen = getMaxLen(root.right, root.val, 1);

        return Math.max(leftLen, rightLen);
    }

    private int getMaxLen(TreeNode node, int parentVal, int currLen) {
        if (node == null) return currLen;

        int nextLen = parentVal + 1 == node.val ? currLen + 1 : 1;
        int leftLen = getMaxLen(node.left, node.val, nextLen);
        int rightLen = getMaxLen(node.right, node.val, nextLen);

        return Math.max(Math.max(nextLen, currLen), Math.max(leftLen, rightLen));
    }

    /**
     * Not my BFS approach
     * O(N) space and time
     * */
    public int longestConsecutive_1(TreeNode root) {
        int res = 1;

        Queue<Pair<TreeNode,Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            if (node.left != null) {
                int newLen = node.left.val == node.val + 1 ? pair.getValue() + 1 : 1;
                res = Math.max(res, newLen);
                queue.add(new Pair<>(node.left, newLen));
            }

            if (node.right != null) {
                int newLen = node.right.val == node.val + 1 ? pair.getValue() + 1 : 1;
                res = Math.max(res, newLen);
                queue.add(new Pair<>(node.right, newLen));
            }
        }
        return res;
    }

    private class Pair<T, U> {
        private T first;
        private U second;

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getKey() {
            return first;
        }

        public U getValue() {
            return second;
        }
    }
}
