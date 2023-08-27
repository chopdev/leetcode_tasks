public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        TreeNode node = new TreeNode(0);
        node.left = new TreeNode(-10);
        node.right = new TreeNode(10);

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(7);

        sol.twoSumBSTs(node, root, 18);
    }
}
