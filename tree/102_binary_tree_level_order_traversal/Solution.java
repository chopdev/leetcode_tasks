import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],

  3
 / \
 9  20
 /  \
 15   7

 return its level order traversal as:

 [
     [3],
     [9,20],
     [15,7]
 ]

 */
public class Solution {
    // My solution
    // In order traversal + initialization of result list
    // O(N) - we visit all the nodes, Space O(logN) - deepness of recursion
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traverse(res, root, 0);
        return res;
    }

    private void traverse(List<List<Integer>> res, TreeNode node, int level) {
        if(node == null) {
            return;
        }

        traverse(res, node.left, level + 1);
        while (res.size() <= level) res.add(new ArrayList<>());
        res.get(level).add(node.val);
        traverse(res, node.right, level + 1);
    }


    // Not mine solution
    public List<List<Integer>> levelOrder2222(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, root, 0);
        return result;
    }

    public void dfs(List<List<Integer>> result, TreeNode root, int level) {
        if (root == null) return;
        if (level >= result.size())
            result.add(new ArrayList<>());
        result.get(level).add(root.val);
        dfs(result, root.left, level + 1);
        dfs(result, root.right, level + 1);
    }


    // Not mine solution
    public List<List<Integer>> levelOrder3333(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }
}
