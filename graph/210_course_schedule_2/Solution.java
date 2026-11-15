/**
 210. Course Schedule II
 https://leetcode.com/problems/course-schedule-ii/

 There are a total of n courses you have to take, labeled from 0 to n-1.
 Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed
 as a pair: [0,1]
 Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you
 should take to finish all courses.
 There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses,
 return an empty array.

 Example 1:

 Input: 2, [[1,0]]
 Output: [0,1]
 Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 course 0. So the correct course order is [0,1] .

 Example 2:

 Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 Output: [0,1,2,3] or [0,2,1,3]
 Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

 Note:

 The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
 Read more about how a graph is represented.
 You may assume that there are no duplicate edges in the input prerequisites.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class Solution {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>(); // how many nodes point to the current node

        // Register every course, including ones with no edges.
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }

        for (int[] pair : prerequisites) {
            adj.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
            inDegree.put(pair[0], inDegree.getOrDefault(pair[0], 0) + 1);
        }

        int[] res = new int[numCourses];
        int ind = 0;
        Queue<Integer> q = new ArrayDeque<>();
        // A visited set is not needed.

        for (Integer node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                q.offer(node);
            }
        }

        while (!q.isEmpty()) {
            Integer node = q.poll();
            res[ind++] = node;
            numCourses--;
            if (numCourses == 0) return res;

            for (Integer next : adj.get(node)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    q.offer(next);
                }
            }
        }

        for (Integer node : inDegree.keySet()) {
            if (inDegree.get(node) != 0) return new int[0];
        }

        return res;
    }

    // Solution 2: my previous solution using DFS.
    // time O(V+E)
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        if(numCourses <= 0) return null;
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++)
            graph.put(i, new ArrayList<>());

        for(int[] rel : prerequisites)
            graph.get(rel[0]).add(rel[1]);

        List<Integer> res = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        for(Integer node : graph.keySet()) {
            if(!visited.contains(node)) {
                boolean withoutLoops = dfs(node, graph, res, new HashSet<>(), visited);
                if(!withoutLoops) return new int[0];
            }
        }

        int[] resArr = new int[numCourses];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    private boolean dfs(Integer curr,
                        HashMap<Integer, ArrayList<Integer>> graph,
                        List<Integer> res,
                        HashSet<Integer> visiting,
                        HashSet<Integer> visited) {
        if(visiting.contains(curr)) return false;
        visiting.add(curr);

        for(int child : graph.get(curr)) {
            if(!visited.contains(child))
                if(!dfs(child, graph, res, visiting, visited)) return false;
        }

        visiting.remove(curr);
        visited.add(curr);
        res.add(curr);
        return true;
    }



    // Similar to mine
    // https://leetcode.com/problems/course-schedule-ii/discuss/59342/Java-DFS-double-cache-visiting-each-vertex-once-433ms
    public int[] findOrder2222(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) adj.add(i, new ArrayList<>());
        for (int i = 0; i < prerequisites.length; i++) adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
        boolean[] visited = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!topologicalSort(adj, i, stack, visited, new boolean[numCourses])) return new int[0];
        }
        int i = 0;
        int[] result = new int[numCourses];
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }
        return result;
    }

    private boolean topologicalSort(List<List<Integer>> adj, int v, Stack<Integer> stack, boolean[] visited, boolean[] isLoop) {
        if (visited[v]) return true;
        if (isLoop[v]) return false;
        isLoop[v] = true;
        for (Integer u : adj.get(v)) {
            if (!topologicalSort(adj, u, stack, visited, isLoop)) return false;
        }
        visited[v] = true;
        stack.push(v);
        return true;
    }
}
