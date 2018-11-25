import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 https://leetcode.com/problems/is-graph-bipartite/description/
 785. Is Graph Bipartite?

 Given an undirected graph, return true if and only if it is bipartite.
 Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that
 every edge in the graph has one node in A and another node in B.
 The graph is given in the following form: graph[i] is a list of indexes j
 for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.
 There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

 Example 1:
 Input: [[1,3], [0,2], [1,3], [0,2]]
 Output: true
 Explanation:
 The graph looks like this:
 0----1
 |    |
 |    |
 3----2
 We can divide the vertices into two groups: {0, 2} and {1, 3}.

 Example 2:
 Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 Output: false
 Explanation:
 The graph looks like this:
 0----1
 | \  |
 |  \ |
 3----2
 We cannot find a way to divide the set of nodes into two independent subsets.

 Note:

 graph will have length in range [1, 100].
 graph[i] will contain integers in range [0, graph.length - 1].
 graph[i] will not contain i or duplicate values.
 The graph is undirected: if any element j is in graph[i], then i will be in graph[j]

 My Example

 1 ------ 3
 | -------4

 0 ------- 2

 Input: [[2], [3, 4], [0], [1], [1]]
 Output: true

 LINKS:
 https://ru.wikipedia.org/wiki/%D0%94%D0%B2%D1%83%D0%B4%D0%BE%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%B3%D1%80%D0%B0%D1%84

 * */
public class Solution {

    // My wrong solution
    // works only for lucky cases
    public boolean isBipartite(int[][] graph) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int i = 0; i < graph.length; i++) {
            for (int k = 0; k < graph[i].length; k++) {
                int child = graph[i][k];
                if(!set1.contains(i) && !set2.contains(i)) {
                    if(set1.contains(child))
                        set2.add(i);
                    else if(set2.contains(child))
                        set1.add(i);
                    else {
                        set1.add(i);  // on this part we are failing
                        set2.add(child);  // because we can't say exactly in which sets to put i and child
                    }
                }
                else if(set1.contains(i)) {
                    if(set1.contains(child)) return false;
                    else set2.add(child);
                }
                else if(set2.contains(i)) {
                    if(set2.contains(child)) return false;
                    else set1.add(child);
                }
            }
        }

        return true;
    }

    // my not working solution
    // idea: do graph coloring and then detect how much colors do we have
    // https://www.geeksforgeeks.org/graph-coloring-set-2-greedy-algorithm/
    // This algorithm doesn’t always use minimum number of colors. Also, the number of
    // colors used sometime depend on the order in which vertices are processed.
    public boolean isBipartite2222(int[][] graph) {
        if(graph == null || graph.length == 0) return false;

        int[] resColors = new int[graph.length];
        Arrays.fill(resColors, -1);
        resColors[0] = 0;
        boolean[] forbiddenColors = new boolean[graph.length];

        for (int i = 1; i < graph.length; i++) {

            for (int j = 0; j < graph[i].length; j++) {
                int child = graph[i][j];
                if(resColors[child] != -1)
                    forbiddenColors[resColors[child]] = true;
            }

            for (int j = 0; j < forbiddenColors.length; j++) {
                if(!forbiddenColors[j]) {
                    resColors[i] = j;
                    break;
                }
            }

            Arrays.fill(forbiddenColors, false);
        }

        HashSet<Integer> uniqueColors = new HashSet<>();
        for (int i = 0; i < resColors.length; i++) {
            uniqueColors.add(resColors[i]);
        }
        return uniqueColors.size() <= 2;
    }

    // Not mine BFS solution
    // https://leetcode.com/problems/is-graph-bipartite/discuss/120218/Java-Short-Iterative-Solution
    public boolean isBipartite3333(int[][] graph) {
        if(graph == null || graph.length == 0) return false;
        int[] colors = new int[graph.length];  // 0 - unset, 1 - red, -1 - black
        for (int i = 0; i < graph.length; i++) { // visit all nodes
            if(colors[i] != 0)  // if node is colored, skip it
                continue;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);  // set to first node red color
            colors[i] = 1;  // do BFS
            while (!queue.isEmpty()) {
                Integer curr = queue.poll(); // for each child of current node
                for(Integer child : graph[curr]) {
                    if(colors[child] == 0) {
                        colors[child] = -colors[curr];  // set color to opposite
                        queue.add(child);
                    }
                    // color is already exist, and it the same as in adjacent node
                    else if(colors[child] == colors[curr]) return false;
                }
            }
        }

        return true;
    }


    // Not mine solution
    // https://leetcode.com/problems/is-graph-bipartite/discuss/115487/Java-Clean-DFS-solution-with-Explanation
    // Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
    // Initialize a color[] array for each node. Here are three states for colors[] array:
    //0: Haven't been colored yet.
    //1: Blue.
    //-1: Red.
    // DFS solution
    // set to adjacent nodes opposite color
    public boolean isBipartite4444(int[][] graph) {
        if (graph == null || graph.length == 0) return false;

        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if(colors[i] != 0)  // if node is colored, skip it
                continue;

            if(!validColor(graph, colors, i, 1)) return false;
        }

        return true;
    }

    private boolean validColor(int[][] graph, int[] colors, int node, int color) {
        if(colors[node] != 0) {
            return colors[node] == color;  // return false if other color is set
        }

        colors[node] = color;
        for (Integer child : graph[node]) {
            if(!validColor(graph, colors, child, -color))  return false;
        }

        return true;
    }
}
