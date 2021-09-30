import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                Node curr = queue.poll();

                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }

                if (i != levelSize - 1) {
                    curr.next = queue.peek();
                }

            }
        }
        return root;
    }
}
