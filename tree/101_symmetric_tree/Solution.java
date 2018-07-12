public class Solution {
    // mine solution
    // complexity?
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return  true;

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        rigthFirst(root.right, sb1);
        leftFirst(root.left, sb2);

        return sb1.toString().equals(sb2.toString());
    }

    private void rigthFirst(TreeNode node, StringBuilder sb) {
        if(node == null){
            sb.append("_");
            return;
        }

        sb.append(node.val);
        rigthFirst(node.right, sb);
        rigthFirst(node.left, sb);
    }

    private void leftFirst(TreeNode node, StringBuilder sb) {
        if(node == null){
            sb.append("_");
            return;
        }

        sb.append(node.val);
        leftFirst(node.left, sb);
        leftFirst(node.right, sb);
    }

    // Not mine solution
    public boolean isSymmetric2(TreeNode root) {
        return root==null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right){
        if(left==null || right==null)
            return left==right;
        if(left.val!=right.val)
            return false;
        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }
}
