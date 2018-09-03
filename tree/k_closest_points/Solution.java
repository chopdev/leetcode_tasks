import java.util.ArrayList;
import java.util.PriorityQueue;

/*
* There is a service that returns closest hotels to your location.
* Given an array with coordinates, return K-th closest hotels
*
* [[1, 2] [1, 1] [1, 0]] get 2 closest
*   [[1, 1], [1, 0]]
* */
public class Solution {

    // Mine solution
    // Complexity O(N+2K+N-K)*logK) = O(N+(N-K)*logK) , because N>=K
    public int[][] getClosestHotels(int[][] points, int numberOfClosest) {
        if(points == null) return null;
        if(numberOfClosest >= points.length) return points;

        // O(N)
        double[] dest = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            dest[i] = Math.sqrt(Math.pow(points[i][0], 2) + Math.pow(points[i][1], 2));
        }

        // O(K)
        ArrayList<Node> temp = new ArrayList<>();
        for (int i = 0; i < numberOfClosest; i++) {
            temp.add(new Node(dest[i], i));
        }
        PriorityQueue<Node> closest = new PriorityQueue<>(temp);

        // O((N-K)*logK)
        for (int i = numberOfClosest; i < dest.length; i++) {
            Node curr = closest.peek();
            if(curr.val > dest[i]) {
                closest.poll();
                closest.add(new Node(dest[i], i));
            }
        }

        // O(K)
        int[][] res = new int[numberOfClosest][2];
        int j = 0;
        for (Node n : closest) {
            int index = n.index;
            res[j][0] = points[index][0];
            res[j][1] = points[index][1];
            j++;
        }

        return res;
    }
}
