import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


// Not mine solution
public class Solution2 {
    class Node {
        int label;
        Map<Node, Integer> outEdges;

        int pathCost;

        Node(int label) {
            this.label = label;
            outEdges = new HashMap<>();
            pathCost = Integer.MAX_VALUE;
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Node> labelToNode = new HashMap<>();
        for (int[] edge : times) {
            int sourceLabel = edge[0];
            int targetLabel = edge[1];
            int time = edge[2];

            Node sourceNode = labelToNode.computeIfAbsent(sourceLabel, label -> new Node(label));
            Node targetNode = labelToNode.computeIfAbsent(targetLabel, label -> new Node(label));
            sourceNode.outEdges.put(targetNode, time);
        }

        Comparator<Node> comparator = (n1, n2) -> Integer.compare(n1.pathCost, n2.pathCost);
        PriorityQueue<Node> minCostNodes = new PriorityQueue(comparator);
        Node kNode = labelToNode.computeIfAbsent(K, label -> new Node(label));
        kNode.pathCost = 0;
        minCostNodes.add(kNode);
        int maxPathCost = 0;
        for (int i = 0; i < N; ++i) {
            if (minCostNodes.isEmpty()) {
                return -1;
            }

            Node minNode = minCostNodes.poll();
            maxPathCost = minNode.pathCost;
            for (Map.Entry<Node, Integer> outEdge : minNode.outEdges.entrySet()) {
                Node targetNode = outEdge.getKey();
                int cost = outEdge.getValue();
                if (maxPathCost + cost < targetNode.pathCost) {
                    minCostNodes.remove(targetNode);
                    targetNode.pathCost = maxPathCost + cost;
                    minCostNodes.add(targetNode);
                }
            }
        }

        return maxPathCost;
    }
}
