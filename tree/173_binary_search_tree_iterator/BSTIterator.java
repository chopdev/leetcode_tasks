import java.util.Stack;

public class BSTIterator {

    private Stack<TreeNode> stack = new Stack<>();


    public BSTIterator(TreeNode root) {
        pushSmaller(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode curr = stack.pop();
        pushSmaller(curr.right);

        return curr.val;
    }

    private void pushSmaller(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }
}