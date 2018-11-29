import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 Interview question

 You have a function f(p) that returns an array of linked pages e.g f(homepage) = {page1, page2, page3}.
 Or f(page1) = {page4, page5}. If there is no linked pages function will return null. And we say one site is
 a good site if we can get to any page of that site in a less than 3 clicks. The task was to write a function
 that will check is it a good site or not using f(p).

 https://www.glassdoor.com/Interview/Booking-com-Interview-RVW18273114.htm

 (The solution mentioned by Alexey Matveev has a flaw. What if later during the traversal we found shorter path to
 a page. To allow this we need to keep track of distance for each node and visit the node event it is already visited
 e.g. by using Map. Finally we need to check the distance of each node to see if it is greater than 5.)


 IMPORTANT TO ASK:
 pages could have a loops? page4 reference on homepage,  homepage refs page4

 Because if not - we have a tree (in-order traversal to detect height from the home)
 If yes - we have a graph (Dijkstras algorithm)

 */
public class Solution {
    /**
    We say that BFS is the algorithm to use if we want to find the shortest path in an undirected, unweighted graph.
    The claim for BFS is that the first time a node is discovered during the traversal, that distance from
    the source would give us the shortest path.
    Why?
     Because to do BFS we need to check if node was visited (in order to not be in loop)

    * */


    // My solution
    // If tree
    public boolean siteIsGood(int[][] pages) {
        if(pages == null || pages.length == 0) return false;

        HashMap<Integer, TreeNode> map = new HashMap<>();
        for (int i = 0; i < pages.length; i++) {
            if(!map.containsKey(i)) map.put(i, new TreeNode(i));

            for (int ch : pages[i]) {
                if(!map.containsKey(ch)) map.put(ch, new TreeNode(ch));
                map.get(i).child.add(map.get(ch));
            }
        }

        TreeNode head = map.get(0);
        int depth = dfs(head);
        return depth > 3 ? false : true;
    }

    int dfs(TreeNode node) {
        if(node.child.isEmpty()) return 1;

        int max = Integer.MIN_VALUE;
        for (TreeNode child : node.child) {
            max = Math.max(max, dfs(child) + 1);
        }

        return max;
    }

    class TreeNode {
        int val;
        List<TreeNode> child;

        TreeNode(int val) {
            child = new ArrayList<>();
            this.val = val;
        }
    }
}
