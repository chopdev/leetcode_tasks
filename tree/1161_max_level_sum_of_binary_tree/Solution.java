import java.util.*;
import java.util.stream.IntStream;

/**
 1161. Maximum Level Sum of a Binary Tree
 https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/

 Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

 Return the smallest level X such that the sum of all the values of nodes at level X is maximal.



 Example 1:



 Input: [1,7,0,7,-8,null,null]
 Output: 2
 Explanation:
 Level 1 sum = 1.
 Level 2 sum = 7 + 0 = 7.
 Level 3 sum = 7 + -8 = -1.
 So we return the level with the maximum sum which is level 2.


 Note:

 The number of nodes in the given tree is between 1 and 10^4.
 -10^5 <= node.val <= 10^5

 * */
public class Solution {

    // My solution
    // O(N) time, O(N) space
    public int maxLevelSum(TreeNode root) {
        HashMap<Integer, Integer> levelToSum = new HashMap<>();
        int maxLevel = dfs(root, levelToSum, 1);

        int res = 1, sum = Integer.MIN_VALUE;
        for (int i = 1; i <= maxLevel; i++) {
            if(sum < levelToSum.get(i)) {
                res = i;
                sum = levelToSum.get(i);
            }
        }
        return res;
    }

    // returns max level
    private int dfs(TreeNode node, HashMap<Integer, Integer> levelToSum, int level) {
        if(node == null) return level - 1;

        levelToSum.putIfAbsent(level, 0);
        levelToSum.put(level, levelToSum.get(level) + node.val);

        int levLeft = dfs(node.left, levelToSum, level + 1);
        int levRight = dfs(node.right, levelToSum, level + 1);
        return Math.max(levLeft, levRight);
    }


    // Interesting BFS approach
    // https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/discuss/360968/JavaPython-3-Two-codes-language%3A-BFS-level-traversal-and-DFS-level-sum.
    public int maxLevelSum2222(TreeNode root) {
        int max = Integer.MIN_VALUE, maxLevel = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        for (int level = 1; !q.isEmpty(); ++level) {
            int sum = 0;
            for (int sz = q.size(); sz > 0; --sz) {
                TreeNode n = q.poll();
                sum += n.val;
                if (n.left != null) {
                    q.offer(n.left);
                }
                if (n.right != null) {
                    q.offer(n.right);
                }
            }
            if (max < sum) {
                max = sum;
                maxLevel = level;
            }
        }
        return maxLevel;
    }

// https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/discuss/360968/JavaPython-3-Two-codes-language%3A-BFS-level-traversal-and-DFS-level-sum.
    public int maxLevelSum3333(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs3333(root, list, 0);
        return 1 + IntStream.range(0, list.size()).reduce(0, (a, b) -> list.get(a) < list.get(b) ? b : a);
    }
    private void dfs3333(TreeNode n, List<Integer> l, int level) {
        if (n == null) { return; }
        if (l.size() == level) { l.add(n.val); } // never reach this level before, add first value.
        else { l.set(level, l.get(level) + n.val); } // reached the level before, accumulate current value to old value.
        dfs3333(n.left, l, level + 1);
        dfs3333(n.right, l, level + 1);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
