import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 684. Redundant Connection
 https://leetcode.com/problems/redundant-connection/

 In this problem, a tree is an undirected graph that is connected and has no cycles.
 The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one
 additional edge added.
 The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
 that represents an undirected edge connecting nodes u and v.

 Return an edge that can be removed so that the resulting graph is a tree of N nodes.
 If there are multiple answers, return the answer that occurs last in the given 2D-array.
 The answer edge [u, v] should be in the same format, with u < v.

 Example 1:
 Input: [[1,2], [1,3], [2,3]]
 Output: [2,3]
 Explanation: The given undirected graph will be like this:
 1
 / \
 2 - 3

 Example 2:
 Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 Output: [1,4]
 Explanation: The given undirected graph will be like this:
 5 - 1 - 2
 |   |
 4 - 3

 Note:
 The size of the input 2D-array will be between 3 and 1000.
 Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.


 * */
public class Solution {
    // My solution, using DFS for detecting of loop
    // O(V+E) time, O(V) space
    // IMPORTANT: for undirected graph we also pass parent, because vertices are connected both ways
    public int[] findRedundantConnection(int[][] edges) {
        if(edges == null || edges.length == 0) return null;
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
        for (int[] pair : edges) {
            if(!graph.containsKey(pair[0])) graph.put(pair[0], new ArrayList<>());
            if(!graph.containsKey(pair[1])) graph.put(pair[1], new ArrayList<>());

            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        HashSet<Integer> result = new HashSet<>();
        int[] lastPair = null;
        dfs(graph, edges[0][0],edges[0][0], new HashSet<>(), new HashSet<>(), result);

        for(int[] edge : edges) {
            if(result.contains(edge[0]) && result.contains(edge[1])) {
                lastPair = edge;
            }
        }

        return lastPair;
    }

    private Integer dfs(HashMap<Integer, ArrayList<Integer>> graph,
                        int curr,
                        int parent,
                        HashSet<Integer> visiting,
                        HashSet<Integer> visited,
                        HashSet<Integer> result) {
        if(visited.contains(curr)) return null;
        if(visiting.contains(curr)) {
            result.add(curr);
            return curr;
        }

        visiting.add(curr);

        for (int child : graph.get(curr)) {
            if(child == parent) continue;

            Integer val =  dfs(graph, child, curr, visiting, visited, result);
            if(val != null) {
                visited.add(curr);
                visiting.remove(curr);
                if(val == curr) return null;
                else {
                    result.add(curr);
                    return val;
                }
            }
        }

        visited.add(curr);
        visiting.remove(curr);
        return null;
    }
}
