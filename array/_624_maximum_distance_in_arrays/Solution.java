import java.util.List;

/**
 * https://leetcode.com/problems/maximum-distance-in-arrays/?envType=study-plan-v2&id=premium-algo-100
 * https://leetcode.com/problems/maximum-distance-in-arrays/
 *
 * 624. Maximum Distance in Arrays
 *
 * You are given m arrays, where each array is sorted in ascending order.
 * You can pick up two integers from two different arrays (each array picks one) and calculate the distance.
 * We define the distance between two integers a and b to be their absolute difference |a - b|.
 * Return the maximum distance.
 *
 * Example 1:
 *
 * Input: arrays = [[1,2,3],[4,5],[1,2,3]]
 * Output: 4
 * Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 *
 *
 * Example 2:
 *
 * Input: arrays = [[1],[1]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == arrays.length
 * 2 <= m <= 10^5
 * 1 <= arrays[i].length <= 500
 * -10^4 <= arrays[i][j] <= 10^4
 * arrays[i] is sorted in ascending order.
 * There will be at most 105 integers in all the arrays.
 *
 * */
public class Solution {

    /**
     * Some more examples
     * [[-3,-2,-1],[0,4,5],[1,2,3]]    5 - -3 = 8
     * [[-3,-2,-1],[-4,-2,2]]        0 - -4 =
     *
     * [[1,4],[0,5]]   5 - 1 = 4    4 - 0 = 4
     **/

    /**
     * My solution, O(N) time
     * */
    public int maxDistance(List<List<Integer>> arrays) {
        int minInd = -1, maxInd = -1;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < arrays.size(); i++) {
            int firstEmement = arrays.get(i).get(0);
            min = Math.min(min, firstEmement);
            if (min == firstEmement) minInd = i;

            int lastElement = arrays.get(i).get(arrays.get(i).size() - 1);
            max = Math.max(max, lastElement);
            if (max == lastElement) maxInd = i;
        }

        if (maxInd != minInd) return Math.abs(max - min);

        int anotherMax = Integer.MIN_VALUE;
        for (int i = 0; i < arrays.size(); i++) {
            if (i == maxInd) continue;

            int lastElement = arrays.get(i).get(arrays.get(i).size() - 1);
            anotherMax = Math.max(anotherMax, lastElement);
        }

        int anotherMin = Integer.MAX_VALUE;
        for (int i = 0; i < arrays.size(); i++) {
            if (i == maxInd) continue;

            int firstEmement = arrays.get(i).get(0);
            anotherMin = Math.min(anotherMin, firstEmement);
        }

        return Math.max(Math.abs(anotherMax - min), Math.abs(max - anotherMin));
    }


/**
 * Not my solution, O(N)
 * https://leetcode.com/problems/maximum-distance-in-arrays/solutions/104613/java-solution-min-and-max/?orderBy=most_votes
 * */
    public int maxDistance2222(int[][] arrays) {
        int result = Integer.MIN_VALUE;
        int max = arrays[0][arrays[0].length - 1];
        int min = arrays[0][0];

        for (int i = 1; i < arrays.length; i++) {
            result = Math.max(result, Math.abs(arrays[i][0] - max));
            result = Math.max(result, Math.abs(arrays[i][arrays[i].length - 1] - min));
            max = Math.max(max, arrays[i][arrays[i].length - 1]);
            min = Math.min(min, arrays[i][0]);
        }

        return result;
    }
}
