import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 428. Serialize and Deserialize N-ary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/submissions/836595473/
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * For example, you may serialize the following 3-ary tree
 * as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.
 *
 * Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.
 *
 * For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 * You do not necessarily need to follow the above-suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.
 *
 * Example 1:
 *
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *
 * Example 2:
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * */

public class Solution {

    /**
     *
     * My implementation, idea is taken from here:
     * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/solutions/151421/java-preorder-recursive-solution-using-queue/
     *
     * Use BFS to serialize the tree in the format:
     * node.value,node.counOfChildren,...
     *
     *  e.g.  1,3,3,2,2,0,4,0,5,0,6,0   - node 1 has 3 children, node 3 has 2 children etc
     *
     *  Deserialization is straightforward, we read pairs. Second value helps us to detect how many of input nodes add to children after this node.
     *  1,3, 3,2 ,2,0 ,4,0 ,5,0 ,6,0
     *
     *  Node 1 has 3 children, so read
     *  3,2 ,2,0 ,4,0
     *
     *  Node 3 has 2 children, so read next
     *  5,0 ,6,0
     *
     *  Node 2 has 0 children
     *  etc.
     * */

    class Pair {
        Node node;
        int childCount;

        public Pair(Node node, int childCount) {
            this.node = node;
            this.childCount = childCount;
        }
    }


    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) return "";
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            sb.append(node.val);
            sb.append(",");
            sb.append(node.children.size());
            sb.append(",");

            for (Node child : node.children) {
                queue.add(child);
            }
        }
        sb.deleteCharAt(sb.length() - 1); // remove last ',' although it's not obligatory
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] splitData = data.split(",");
        Queue<Pair> inputQueue = new LinkedList<>();
        for (int i = 0; i < splitData.length; i += 2) {
            Node node = new Node(Integer.parseInt(splitData[i]), new ArrayList<>());
            int childCount = Integer.parseInt(splitData[i + 1]);
            inputQueue.add(new Pair(node, childCount));
        }

        Queue<Pair> bfsQueue = new LinkedList<>();
        Pair pair = inputQueue.poll();
        Node head = pair.node;
        bfsQueue.add(pair);

        while (!bfsQueue.isEmpty()) {
            Pair current = bfsQueue.poll();
            for (int i = 0; i < current.childCount; i++) {
                Pair child = inputQueue.poll();
                current.node.children.add(child.node);
                bfsQueue.add(child);
            }
        }

        return head;
    }

}
