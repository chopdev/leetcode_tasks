public class Main {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        n1.left = new TreeNode(7);
        n1.left.left = new TreeNode(7);
        n1.left.right = new TreeNode(-8);
        n1.right = new TreeNode(0);

        Solution sol =new Solution();
        sol.maxLevelSum(n1);
    }
}
