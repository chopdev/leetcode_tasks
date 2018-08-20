import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

	    //
	    Node res = s.createMinHeightTree(new int[] {1,2,3,4,5,6,7,8,9});

        Queue<Node> stack = new LinkedList<>();
        stack.add(res);

        while (!stack.isEmpty()) {
            Node curr = stack.remove();

            System.out.println(curr.val);
            if(curr.left != null) stack.add(curr.left);
            if(curr.right != null) stack.add(curr.right);
        }
    }
}
