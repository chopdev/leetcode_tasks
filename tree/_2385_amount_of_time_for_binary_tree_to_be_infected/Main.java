public class Main {
    public static void main(String[] args) {
        Solution.TreeNode node = new Solution.TreeNode(1);
        node.left = new Solution.TreeNode(5);
        node.right = new Solution.TreeNode(3);

        node.left.right = new Solution.TreeNode(4);
        node.left.right.left = new Solution.TreeNode(9);
        node.left.right.right = new Solution.TreeNode(2);

        node.right.left = new Solution.TreeNode(10);
        node.right.right = new Solution.TreeNode(6);

        Solution solution = new Solution();

        System.out.println(solution.amountOfTime(node, 3));


        Solution.TreeNode root1 = new Solution.TreeNode(5);
        root1.left = new Solution.TreeNode(2);
        root1.left.left = new Solution.TreeNode(4);
        root1.left.left.left = new Solution.TreeNode(1);
        root1.right = new Solution.TreeNode(3);

        System.out.println(solution.amountOfTime(root1, 4));
    }
}
