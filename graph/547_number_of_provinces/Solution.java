/*
547. Number of Provinces
https://leetcode.com/problems/number-of-provinces/

Given a matrix describing direct connections between cities, count the provinces.
A province is a group of directly or indirectly connected cities with no
connections to cities outside the group.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) return 0;

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int r = 0; r < isConnected.length; r++) {
            for (int c = 0; c < isConnected[0].length; c++) {
                if (isConnected[r][c] == 1) {
                    if (!graph.containsKey(r)) graph.put(r, new ArrayList<>());
                    if (!graph.containsKey(c)) graph.put(c, new ArrayList<>());

                    graph.get(r).add(c);
                    graph.get(c).add(r);
                }
            }
        }

        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (Integer node : graph.keySet()) {
            if (visited.contains(node)) continue;
            count++;
            dfs(graph, visited, node);
        }

        return count;
    }

    public void dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int node) {
        if (visited.contains(node)) return;

        visited.add(node);
        for (Integer next : graph.get(node)) {
            dfs(graph, visited, next);
        }
    }
}
