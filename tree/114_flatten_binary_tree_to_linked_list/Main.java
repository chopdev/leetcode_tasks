public class Main {

    public static void main(String[] args) {
	    TreeNode n1 = new TreeNode(1);
	    TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);
        TreeNode n11 = new TreeNode(11);
        TreeNode n12 = new TreeNode(12);

        n1.left = n2;
        n1.right = n9;

        n2.left = n3;
        n2.right = n6;

        n6.left = n7;
        n6.right = n8;

        n3.left = n4;
        n3.right = n5;

        n9.right = n10;

        n10.left = n11;
        n10.right = n12;

        Solution sol = new Solution();
        sol.flatten2222(n1);

        TreeNode root = n1;
        while (root != null) {
            System.out.println(root.val);
            root = root.right;
        }
    }
}
