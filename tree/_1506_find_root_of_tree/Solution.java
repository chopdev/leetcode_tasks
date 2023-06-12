import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    /*
    * My solution
    * O(N) memory and time.
    * Each child can be found only once in children list, that's why time is O(N)
    * */
    public Node findRoot(List<Node> tree) {
        if (tree.size() == 1) return tree.get(0);

        HashSet<Node> rootCandidates = new HashSet<>();
        for (Node node : tree) {
            if (node.children.size() == 0) continue;
            rootCandidates.add(node);
        }

        for (Node node : tree) {
            for (Node child : node.children) {
                rootCandidates.remove(child);
                if (rootCandidates.size() == 1) return rootCandidates.iterator().next();
            }
        }

        return null;
    }


/**
 * Not my, O(N) time, O(1) space
 *  https://leetcode.com/problems/find-root-of-n-ary-tree/solutions/726453/java-o-n-time-with-o-n-space-and-o-1-space-follow-up/?envType=study-plan-v2&envId=premium-algo-100
 *
 *  Great idea, as long as all values are unique we can use "sum" variable to find only one parent node
 * */
    public Node findRoot2222(List<Node> tree) {
        // Edge Case
        if (tree==null || tree.size()==0) return null;

        long sum=0;

        // For each node in the tree
        for (Node node : tree){
            // Add current node value to sum
            sum+=node.val;

            // For each child - reduce value of child from sum
            for (Node child : node.children)
                sum-=child.val;
        }

        // Remaining value in sum is the only node that doesn't have a parent (meaning isn't a child of any other node) - which is the root.
        for (Node node : tree)
            if (node.val==sum) return node;

        return null;
    }


    /**
     * Not my
     *
     * There's actually a slightly faster way to calculate the sum using bit operations.
     * Instead of using "+" and "-" we can simply XOR the values.
     * If you remember, doing XOR between same number will result in 0 (2 XOR 2 = 0) thus XORing all the nodes with their children will eventually leave us with the only node that wasn't a child - the root.
     * */
    public Node findRoot3333(List<Node> tree) {
        // Edge Case
        if (tree==null || tree.size()==0) return null;

        long sum=0;

        // For each node in the tree
        for (Node node : tree){
            // Add current node value to sum
            sum^=node.val;

            // For each child - reduce value of child from sum
            for (Node child : node.children)
                sum^=child.val;
        }

        // Remaining value in sum is the only node that doesn't have a parent (meaning isn't a child of any other node) - which is the root.
        for (Node node : tree)
            if (node.val==sum) return node;

        return null;
    }
}
