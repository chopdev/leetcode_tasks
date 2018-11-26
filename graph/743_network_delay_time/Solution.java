import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 https://leetcode.com/problems/network-delay-time/description/
 743. Network Delay Time

 There are N network nodes, labelled 1 to N.

 Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

 Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

 Note:
 N will be in the range [1, 100].
 K will be in the range [1, N].
 The length of times will be in the range [1, 6000].
 All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.
 */
public class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        if(times == null || times.length == 0) return -1;
        HashMap<Integer, Node> graph = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int sourceNode = times[i][0];
            int targetNode = times[i][1];
            int weight = times[i][2];

            Node source = graph.getOrDefault(sourceNode, new Node(sourceNode));
            Node target = graph.getOrDefault(targetNode, new Node(targetNode));

            if(!graph.containsKey(sourceNode)) graph.put(sourceNode, source);
            if(!graph.containsKey(targetNode)) graph.put(targetNode, source);

            source.adjacent.add(new Edge(weight, target));
        }

        return bfs(graph, K);
    }

    private int bfs(HashMap<Integer, Node> graph, int K) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(graph.get(K));

        int time = 0;
        Node curr = null;
        while (!queue.isEmpty()) {
            curr = queue.remove();
            graph.remove(curr.val);
            int timeFromCurrent = 0;
            for(Edge edge : curr.adjacent) {
                if(!graph.containsKey(edge.target.val)) continue;

                queue.add(edge.target);
                timeFromCurrent = Math.max(timeFromCurrent, edge.weight);
            }
            time += timeFromCurrent;
        }

        return time;
    }

    public int dfs(HashMap<Integer, Node> graph, int K) {
        if(!graph.containsKey(K)) return 0;

        int time = 0;
        Node curr = graph.remove(K);
        for(Edge edge : curr.adjacent) {
            int weight = edge.weight;
            time =  Math.max(weight + dfs(graph, edge.target.val), time);
        }

        return time;
    }
}
