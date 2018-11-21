public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);

        root.left = root2;
        root.right = root3;
        root3.left = root4;
        root3.right = root5;

        String s = sol.serialize(root);
        TreeNode res  = sol.deserialize(s);
    }
}
