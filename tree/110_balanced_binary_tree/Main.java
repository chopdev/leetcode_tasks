public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        TreeNode l = root.left;
        l.right = new TreeNode(5);
        l.left = new TreeNode(7);

        TreeNode r = root.right;
        r.right = new TreeNode(5);
        r.left = new TreeNode(7);

        Solution s = new Solution();
        boolean balanced = s.isBalanced(root);

        TreeNode lll = l.left;
        lll.left = new TreeNode(66);
        lll.left.left = new TreeNode(555);

        boolean balanced2 = s.isBalanced(root);

        System.out.println(balanced);
        System.out.println(balanced2);
    }
}
