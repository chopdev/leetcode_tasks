import java.util.Arrays;
import java.util.LinkedList;

/**
 https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 297. Serialize and Deserialize Binary Tree

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
 in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same
 or another computer environment.
 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be
 serialized to a string and this string can be deserialized to the original tree structure.

 Example:
 You may serialize the following tree:

   1
 / \
 2   3
    / \
    4   5

 as "[1,2,3,null,null,4,5]"

 Clarification: The above format is the same as how LeetCode serializes a binary tree.
 You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

 SOLUTIONS
 https://leetcode.com/articles/serialize-and-deserialize-binary-tree/
 */
public class Solution {

    // My solution
    // Pre-order traversal
    // on null nodes set * to string
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString().trim();
    }

    // O(N) time, O(N) space
    private void serialize(TreeNode node, StringBuilder builder) {
        if(node == null) {
            builder.append("* ");
            return;
        }

        builder.append(node.val);
        builder.append(" ");
        serialize(node.left, builder);
        serialize(node.right, builder);
    }

    // O(N) time, O(N) space
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()) return null;
        LinkedList<String> strs = new LinkedList<>(Arrays.asList(data.split(" ")));
        TreeNode root = new TreeNode(Integer.parseInt(strs.removeFirst()));
        deserializeRecurs(strs, root);
        return root;
    }

    public void deserializeRecurs(LinkedList<String> strs, TreeNode node) {
        if(strs.isEmpty()) return;
        String symbLeft = strs.removeFirst();
        if(!symbLeft.equals("*")) {
            node.left = new TreeNode(Integer.parseInt(symbLeft));
            deserializeRecurs(strs, node.left);
        }

        if(strs.isEmpty()) return;
        String symbRight = strs.removeFirst();
        if(!symbRight.equals("*")) {
            node.right = new TreeNode(Integer.parseInt(symbRight));
            deserializeRecurs(strs, node.right);
        }
    }
}
