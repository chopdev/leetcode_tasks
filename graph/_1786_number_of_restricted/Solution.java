import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * 1786. Number of Restricted Paths From First to Last Node
 * https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/
 *
 * */

public class Solution {
    // given at the description
    private final static int mod = 1_000_000_007;
    Map<Integer, List<Edge>> graph;

    public class Edge {
        int target; // target node
        int weight; // distance

        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    /**
     * Partially my my solution, except DFS
     * O(E + V*logE + E) = O(V*logE + E)  - V*logE for dijkstras, E - for grapgh build and DFS
     * O(V) space
     * */
    public int countRestrictedPaths(int n, int[][] edges) {
        graph = buildGraph(n, edges);
        long[] dist = getDistancesToLastNode(n, graph);

        Integer[] dp = new Integer[n+1];
        return dfs(1, n, dist, dp);
    }


    // DFS part is taken from here https://leetcode.com/problems/number-of-restricted-paths-from-first-to-last-node/discuss/1097359/Java-Dijkstra's-%2B-DFS-%2B-Memoization-%2B-Explanation-of-problem-description
    // Because I wanted to solve it using BFS
    // getCountOfRestrictedPaths by dfs
    public int dfs(int node, int end, long[] dist, Integer[] dp) {
        if(node == end) return 1;
        if(dp[node] != null) return dp[node];
        long res = 0;
        for(Edge edge : graph.get(node)) {
            int nei = edge.target;
            if(dist[node] > dist[nei]) { //use our calculations from Dijkstra's to determine if we can travel to a neighbor.
                res = (res + (dfs(nei, end, dist, dp)) % mod);
            }
        }
        res = (res % mod);
        return dp[node] = (int) res; //memoize for looking up values that have already been computed.
    }

    // getCountOfRestrictedPaths by bfs
    private int bfs(Map<Integer, List<Edge>> graph, long[] dist, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        HashSet<Integer> visited = new HashSet<>();
        long res = 0;

        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            visited.add(currNode);
            if (currNode == n) {
                res ++;
                continue;
            }
            for (Edge edge : graph.get(currNode)) {
                if (visited.contains(edge)) continue;
                if (edge.target > currNode && dist[edge.target] <= dist[currNode]) {
                    queue.add(edge.target);
                }
            }
        }

        return ((int) res % mod);
    }

    private long[] getDistancesToLastNode(int n, Map<Integer, List<Edge>> graph) {
        // greedy algorithm (Dijkstra shortest pass), use heap to detect next closest node to the LAST node
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // [node, weight]
        queue.add(new int[] {n, 0});

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int currentNode = pair[0];
            int distToCurr = pair[1];

            if (dist[currentNode] < distToCurr) {
                continue; // we already found optimal dist
            }
            dist[currentNode] = distToCurr;

            for (Edge adjEdge : graph.get(currentNode)) {
                if (distToCurr + adjEdge.weight < dist[adjEdge.target]) { // add a candidate shortest pass to target node
                    queue.add(new int[] {adjEdge.target, distToCurr + adjEdge.weight});
                }
            }
        }

        return dist;
    }

    private Map<Integer, List<Edge>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] nodesAndWeight : edges) {
            if (!graph.containsKey(nodesAndWeight[0])) graph.put(nodesAndWeight[0], new ArrayList<>());
            if (!graph.containsKey(nodesAndWeight[1])) graph.put(nodesAndWeight[1], new ArrayList<>());

            graph.get(nodesAndWeight[0]).add(new Edge(nodesAndWeight[1], nodesAndWeight[2]));
            graph.get(nodesAndWeight[1]).add(new Edge(nodesAndWeight[0], nodesAndWeight[2]));
        }
        return graph;
    }
}
