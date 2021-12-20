public class Program {
    public static void main(String[] args) {
        Solution sol = new Solution();
        Solution.TreeNode node = new Solution.TreeNode(-2147483648);
        node.right = new Solution.TreeNode(2147483647);

        System.out.println(sol.isValidBST(node));;
    }
}
