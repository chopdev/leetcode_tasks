import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1976. Number of Ways to Arrive at Destination
 * https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
 *
 * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections.
 * The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
 *
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between
 * intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.
 *
 * Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * Output: 4
 * Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
 * The four ways to get there in 7 minutes are:
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 *
 *
 * Example 2:
 *
 * Input: n = 2, roads = [[1,0,10]]
 * Output: 1
 * Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n - 1 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * 1 <= timei <= 109
 * ui != vi
 * There is at most one road connecting any two intersections.
 * You can reach any intersection from any other intersection.
 *
 * */
public class Solution {
    class Node {
        int val;
        List<Edge> edges = new ArrayList<>();

        public Node(int value) {
            val = value;
        }
    }

    class Edge {
        int distance;
        Node target;

        public Edge(int distance, Node target) {
            this.target = target;
            this.distance = distance;
        }
    }

    /**
     * Not completely my solution (I didn't figure out about DP), failing for one test case
     *
     * Complexity == to Dijkstras Algorithm, O(E * logV) where E - number of edges, V - number of vertices
     *
     * So it's O(R * logN)
     * */
    public int countPaths(int n, int[][] roads) {
        if (n <= 1) return n;

        Map<Integer, Node> graph = new HashMap<>();

        for (int[] road : roads) {
            if (!graph.containsKey(road[0])) graph.put(road[0], new Node(road[0]));
            if (!graph.containsKey(road[1])) graph.put(road[1], new Node(road[1]));

            graph.get(road[0]).edges.add(new Edge(road[2], graph.get(road[1])));
            graph.get(road[1]).edges.add(new Edge(road[2], graph.get(road[0])));
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1])); // int[] {node, dist to node}
        heap.add(new int[] {0, 0});

        int finalNode = n - 1;

        int[] dist = new int[n]; // final distance to node from 0 node
        long[] dp = new long[n]; // count of short paths to current node
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        dp[0] = 1;

        while (!heap.isEmpty()) {
            int[] pair = heap.poll();
            Node currNode = graph.get(pair[0]);
            int distToCurrentNode = pair[1];

            if(dist[currNode.val]< distToCurrentNode) // processed node, we have found minipal path
                continue;

            for (Edge edge : currNode.edges) {
                int distToTargetNode = distToCurrentNode + edge.distance;
                int targetNode = edge.target.val;

                if (distToTargetNode < dist[targetNode]) {
                    dist[targetNode] = distToTargetNode;
                    heap.add(new int[] {targetNode, distToTargetNode});
                    dp[targetNode] = dp[currNode.val]; // count of short paths == to count of short paths of parent node
                } else if (distToTargetNode == dist[targetNode]) {
                    dp[targetNode] += dp[currNode.val];
                    dp[targetNode] %= 1_000_000_007; // to avoid long overflow
                }
            }
        }

        return (int) dp[finalNode] % 1_000_000_007; // Since the answer may be large, return it modulo 10^9 + 7.
    }
}
