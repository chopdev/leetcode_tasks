import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

	    TreeNode root = new TreeNode(1);
	    TreeNode r2 = new TreeNode(2);
	    TreeNode r4 = new TreeNode(4);
	    TreeNode r8 = new TreeNode(8);
	    TreeNode r9 = new TreeNode(9);
	    TreeNode r5 = new TreeNode(5);
	    TreeNode r6 = new TreeNode(6);
	    TreeNode r10 = new TreeNode(10);
	    TreeNode r7 = new TreeNode(7);
	    TreeNode r3 = new TreeNode(3);
	    TreeNode r41 = new TreeNode(41);

	    root.left = r2;
	    root.right = r3;

	    r3.right = r41;

	    r2.left = r4;
	    r2.right = r5;

	    r4.left = r8;
	    r4.right = r9;

	    r5.left = r6;
	    r5.right = r7;

	    r6.left = r10;

	    List<Integer> res = s.inorderTraversal(root);

		for(int t : res)
			System.out.println(t);
    }
}
