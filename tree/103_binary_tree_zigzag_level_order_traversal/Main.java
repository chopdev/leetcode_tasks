import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

	    TreeNode lev3 = new TreeNode(3);
        TreeNode lev9 = new TreeNode(9);
        TreeNode lev20 = new TreeNode(20);
        TreeNode lev15 = new TreeNode(15);
        TreeNode lev7 = new TreeNode(7);
        TreeNode lev6 = new TreeNode(6);
        TreeNode lev4 = new TreeNode(4);

        lev3.left = lev9;
        lev3.right = lev20;

        lev20.left = lev15;
        lev20.right = lev7;

        lev15.left = lev6;
        lev7.right = lev4;

	    List<List<Integer>> res = sol.zigzagLevelOrder(lev3);
    }
}
