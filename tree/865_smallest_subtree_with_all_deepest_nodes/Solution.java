import javafx.util.Pair;

/**
 865. Smallest Subtree with all the Deepest Nodes
 https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/

 Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

 A node is deepest if it has the largest depth possible among any node in the entire tree.

 The subtree of a node is that node, plus the set of all descendants of that node.

 Return the node with the largest depth such that it contains all the deepest nodes in its subtree.



 Example 1:

 Input: [3,5,1,6,2,0,8,null,null,7,4]
 Output: [2,7,4]
 Explanation:

 We return the node with value 2, colored in yellow in the diagram.
 The nodes colored in blue are the deepest nodes of the tree.
 The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
 The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
 Both the input and output have TreeNode type.


 Note:

 The number of nodes in the tree will be between 1 and 500.
 The values of each node are unique.
 * */
public class Solution {

    // My good O(N) time and space solution
    // Uses help class as return value in recursion
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if(root == null) return null;
        NodeInfo res = dfs(root);
        return res.node;
    }

    private NodeInfo dfs(TreeNode node) {
        if(node == null) return null;
        NodeInfo left = dfs(node.left);
        NodeInfo right = dfs(node.right);
        if(left == null && right == null) return new NodeInfo(node, 0);
        if(left == null) {
            right.val ++;
            return right;
        }
        if(right == null) {
            left.val++;
            return left;
        }
        if(right.val == left.val) return new NodeInfo(node, right.val + 1);
        if(right.val > left.val) {
            right.val ++;
            return right;
        }
        else {
            left.val++;
            return left;
        }
    }


    /**
        Solution similar to mine, but uses state
     https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/discuss/146868/Simple-Java-dfs-recursion-function-with-explanation
    * */
    int deepestLevel = 0;
    TreeNode res = null;

    public TreeNode subtreeWithAllDeepest2222(TreeNode root) {
        dfs2222(root, 0);
        return res;
    }

    private int dfs2222(TreeNode root, int level) {
        if (root == null) return level;

        int leftLevel = dfs2222(root.left, level + 1);
        int rightLevel = dfs2222(root.right, level + 1);

        int curLevel = Math.max(leftLevel, rightLevel);
        deepestLevel = Math.max(deepestLevel, curLevel);
        if (leftLevel == deepestLevel && rightLevel == deepestLevel)
            res = root;
        return curLevel;
    }


    // same as mine but concise and neat
    // https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/discuss/146808/One-pass
    public TreeNode subtreeWithAllDeepest3333(TreeNode root) {
        return deep(root).getValue();
    }

    public Pair<Integer, TreeNode> deep(TreeNode root) {
        if (root == null) return new Pair(0, null);
        Pair<Integer, TreeNode> l = deep(root.left), r = deep(root.right);

        int d1 = l.getKey(), d2 = r.getKey();
        return new Pair(Math.max(d1, d2) + 1, d1 == d2 ? root : d1 > d2 ? l.getValue() : r.getValue());
    }
}
