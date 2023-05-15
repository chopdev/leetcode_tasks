import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 582. Kill Process
 * https://leetcode.com/problems/kill-process/?envType=study-plan-v2&id=premium-algo-100
 *
 * You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.
 * Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).
 *
 * When a process is killed, all of its children processes will also be killed.
 * Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.
 *
 * Example 1
 * Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 * Output: [5,10]
 * Explanation: The processes colored in red are the processes that should be killed.
 *
 * Example 2:
 *
 * Input: pid = [1], ppid = [0], kill = 1
 * Output: [1]
 * */
public class Solution {
    /**
     * My solution
     * The idea is to transform data to tree, then traverse tree with DFS
     * O(N) time and space
     * */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i < pid.size(); i++)  tree.put(pid.get(i), new ArrayList<>());
        for (int i = 0; i < pid.size(); i++) {
            if (ppid.get(i) == 0) continue;
            tree.get(ppid.get(i)).add(pid.get(i));
        }
        List<Integer> res = new ArrayList<>();
        dfs(tree, kill, res); // start from the pid we want to kill
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> tree, int currNode, List<Integer> res) {
        res.add(currNode);
        for (int child : tree.get(currNode)) {
            dfs(tree, child, res);
        }
    }



    /**
     * Similar approach but BFS
     * https://leetcode.com/problems/kill-process/solutions/2547471/bfs-java/?q=bfs&orderBy=most_relevant
     * */
    public List<Integer> killProcess2222(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i<ppid.size(); ++i){
            map.computeIfAbsent(ppid.get(i), x->new ArrayList<Integer>()).add(pid.get(i));
        }

        //BFS
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(kill);
        while(!queue.isEmpty()){
            int t = queue.pop();
            res.add(t);

            ArrayList<Integer> temp = map.get(t);
            if(temp == null) continue;

            for(int i=0; i<temp.size(); ++i){
                queue.offer(temp.get(i));
            }
        }
        return res;
    }


}
