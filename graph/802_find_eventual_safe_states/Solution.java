import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


/**
 802. Find Eventual Safe States
 https://leetcode.com/problems/find-eventual-safe-states/


 There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i]
 is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
 A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node.
 Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.


 Example 1:

 Illustration of graph
 Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 Output: [2,4,5,6]
 Explanation: The given graph is shown above.
 Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
 Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.


 Example 2:

 Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 Output: [4]
 Explanation:
 Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.


 Constraints:

 n == graph.length
 1 <= n <= 104
 0 <= graph[i].length <= n
 0 <= graph[i][j] <= n - 1
 graph[i] is sorted in a strictly increasing order.
 The graph may contain self-loops.
 The number of edges in the graph will be in the range [1, 4 * 104].

 * */
class Solution {


    /**
      My solution, O(N+E) time, E - is total number of edges, O(N) space
     **/
    public List<Integer> eventualSafeNodes(int[][] graph) {
        Set<Integer> safeNodes = new HashSet<>();
        Set<Integer> unsafeNodes = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if(isSafeNodeDfs(graph, i, safeNodes, unsafeNodes, new HashSet<>())) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isSafeNodeDfs(int[][] graph, int node, Set<Integer> safeNodes, Set<Integer> unsafeNodes, Set<Integer> visited) {
        if (safeNodes.contains(node)) return true;
        if (unsafeNodes.contains(node)) return false;

        if (graph[node].length == 0) { // terminal node
            visited.add(node);
            safeNodes.add(node);
            return true;
        }

        if (visited.contains(node)) { // there is a loop
            unsafeNodes.add(node);
            return false;
        }

        visited.add(node);

        for (int i = 0; i < graph[node].length; i++) {
            boolean childIsSafe = isSafeNodeDfs(graph, graph[node][i], safeNodes, unsafeNodes, visited);
            if (!childIsSafe) {
                unsafeNodes.add(node); // one of childs contains loop
                return false;
            }
        }

        safeNodes.add(node); // all childs are safe
        return true;
    }



    // Not mine, clean solution. Similar to my, but visited[] is used to substitute hash sets. It's called graph coloring
    // https://leetcode.com/problems/find-eventual-safe-states/discuss/119871/Straightforward-Java-solution-easy-to-understand!
    public List<Integer> eventualSafeNodes222(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(graph, i, visited)) {
                ret.add(i);
            }
        }
        return ret;
    }

    private boolean dfs(int[][] graph, int cur, int[] visited) {
        if (visited[cur] == 1) return true;
        if (visited[cur] == 2) return false;
        visited[cur] = 2;
        for (int next : graph[cur]) {
            if (!dfs(graph, next, visited)) {
                return false;
            }
        }
        visited[cur] = 1;
        return true;
    }



    // Not my BFS solution, not very clean, but interesting approach with reverse of graph
    // https://leetcode.com/problems/find-eventual-safe-states/solution/
    public List<Integer> eventualSafeNodes3333(int[][] G) {
        int N = G.length;
        boolean[] safe = new boolean[N];

        List<Set<Integer>> graph = new ArrayList();
        List<Set<Integer>> rgraph = new ArrayList();
        for (int i = 0; i < N; ++i) {
            graph.add(new HashSet());
            rgraph.add(new HashSet());
        }

        Queue<Integer> queue = new LinkedList();

        for (int i = 0; i < N; ++i) {
            if (G[i].length == 0)
                queue.offer(i);
            for (int j: G[i]) {
                graph.get(i).add(j);
                rgraph.get(j).add(i);
            }
        }

        while (!queue.isEmpty()) {
            int j = queue.poll();
            safe[j] = true;
            for (int i: rgraph.get(j)) {
                graph.get(i).remove(j);
                if (graph.get(i).isEmpty())
                    queue.offer(i);
            }
        }

        List<Integer> ans = new ArrayList();
        for (int i = 0; i < N; ++i) if (safe[i])
            ans.add(i);

        return ans;
    }

}