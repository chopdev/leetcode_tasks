import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 1214. Two Sum BSTs
 https://leetcode.com/problems/two-sum-bsts/

 Given the roots of two binary search trees, root1 and root2, return true if and only if there is a node
 in the first tree and a node in the second tree whose values sum up to a given integer target.


 Example 1:


 Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
 Output: true
 Explanation: 2 and 3 sum up to 5.



 Example 2:


 Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
 Output: false

 Constraints:

 The number of nodes in each tree is in the range [1, 5000].
 -109 <= Node.val, target <= 109


 * */
public class Solution {


    /**
     * My solution
     * O(K+N) time
     * O(N+K) space  - space for set + recursion of another tree
     * */
    public boolean twoSumBSTs2222(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) return false;
        Set<Integer> diffs = new HashSet<>();
        getDiffs(root1, diffs, target);

        return searchForNode(root2, diffs, target);
    }

    private void getDiffs(TreeNode node, Set<Integer> diff, int target) {
        if (node == null) return;

        diff.add(target - node.val);
        getDiffs(node.left, diff, target);
        getDiffs(node.right, diff, target);
    }

    private boolean searchForNode(TreeNode node, Set<Integer> diff) {
        if (node == null) return false;

        if (diff.contains(node.val)) return true;

        if (searchForNode(node.left, diff)) return true;
        if (searchForNode(node.right, diff)) return true;

        return false;
    }




    /**
     * My solution
     * O(K*logN) time,
     * O(N) space because of the worst recursion possibility
     * */
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root1);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int diff = target - node.val;

            if (findNode(root2, diff)) return true;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return false;
    }

    private boolean findNode(TreeNode node, int searchValue) {
        if (node == null) return false;

        if (node.val == searchValue) return true;

        if (node.val < searchValue)
            return findNode(node.right, searchValue);
        else
            return findNode(node.left, searchValue);
    }
}
