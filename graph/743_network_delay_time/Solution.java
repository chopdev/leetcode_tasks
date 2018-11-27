import java.util.*;

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


 SOLUTIONS
 https://leetcode.com/problems/network-delay-time/solution/
 */
public class Solution {

    // NOT mine - Almost working solution
    // https://leetcode.com/problems/network-delay-time/solution/
    // approach 1 from here
    // intetesting how they implement a graph
    // Map<Integer, List<int[]>> graph = new HashMap();
    // key is source node, list - edges, int[] - int[0] weight, int[1] target node

    // Time complexity O(N^N), for each node we create N dfs branches
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
            if(!graph.containsKey(targetNode)) graph.put(targetNode, target);

            source.adjacent.add(new Edge(weight, target));
        }

        if(N > graph.size()) return -1;
        HashMap<Integer, Integer> nodeToTime = new HashMap<>();
        for (Integer key : graph.keySet())
            nodeToTime.put(key, Integer.MAX_VALUE);

        dfs(graph, K, 0, nodeToTime);

        int maxTime = Integer.MIN_VALUE;
        for (int time : nodeToTime.values()) {
            maxTime = Integer.max(time, maxTime);
        }

        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }

    public void dfs(HashMap<Integer, Node> graph, int K, int time, HashMap<Integer, Integer> nodeToTime) {
        if(time > nodeToTime.get(K)) return;

        Node curr = graph.get(K);
        nodeToTime.put(K, time);

        for(Edge edge : curr.adjacent) {
            int weight = edge.weight;
            dfs(graph, edge.target.val, time + weight, nodeToTime);
        }
    }


    public int networkDelayTime2222(int[][] times, int N, int K) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();

        for(int[] arr : times) {
            if(!graph.containsKey(arr[0])) graph.put(arr[0], new ArrayList<>());
            if(!graph.containsKey(arr[1])) graph.put(arr[1], new ArrayList<>());
            List<int[]> edges = graph.get(arr[0]);
            edges.add(new int[] {arr[1], arr[2]}); // target node; dist from source to target
        }

        HashMap<Integer, Integer> nodeToDist = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        heap.add(new int[] {K, 0});

        while (!heap.isEmpty()) {
            int[] nodeWeight = heap.poll();
            int currNode = nodeWeight[0];
            int distToCurr = nodeWeight[1];
            if(nodeToDist.containsKey(currNode)) continue;
            nodeToDist.put(currNode, distToCurr);

            for(int[] targetAndWeight : graph.get(currNode)) {
                int target = targetAndWeight[0];
                int dist = targetAndWeight[1];

                if(!nodeToDist.containsKey(target))
                    heap.add(new int[] {target, dist + distToCurr});
            }
        }

        if(nodeToDist.size() != N) return -1;

        int maxTime = Integer.MIN_VALUE;
        for (int time : nodeToDist.values()) {
            maxTime = Integer.max(time, maxTime);
        }
        return maxTime;
    }
}
