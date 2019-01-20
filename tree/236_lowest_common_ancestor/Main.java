public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    TreeNode root = new TreeNode(3);

	    TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t2 = new TreeNode(2);
        TreeNode t7 = new TreeNode(7);
        TreeNode t4 = new TreeNode(4);
        TreeNode t18 = new TreeNode(18);

        TreeNode t1 = new TreeNode(1);
        TreeNode t0 = new TreeNode(0);
        TreeNode t8 = new TreeNode(8);

        root.left = t5;
        root.right = t1;

        t1.left = t0;
        t1.right = t8;

        t5.left = t6;
        t5.right = t2;

        t2.left = t7;
        t2.right  = t4;

        t4.left = t18;

        TreeNode res1 = sol.lowestCommonAncestor(root, t5, t1);  // 3
        TreeNode res2 = sol.lowestCommonAncestor(root, t5, t4); // 5
    }
}
