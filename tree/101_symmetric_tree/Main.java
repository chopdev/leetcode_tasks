public class Main {

    public static void main(String[] args) {
	    TreeNode root = new TreeNode(1);
	    root.left = new TreeNode(2);
	    root.right = new TreeNode(2);

	    TreeNode l = root.left;
	    l.right = new TreeNode(5);
	    l.left = new TreeNode(7);

	    TreeNode r = root.right;
	    r.right = new TreeNode(7);
	    r.left = new TreeNode(5);

	    Solution s = new Solution();
	    boolean symmetric = s.isSymmetric(root);

		boolean symmetric2 = s.isSymmetric(new TreeNode(0));

	    System.out.println(symmetric);
		System.out.println(symmetric2);
    }
}
