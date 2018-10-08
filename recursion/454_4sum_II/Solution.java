import java.util.HashMap;
import java.util.Map;

/**
 https://leetcode.com/problems/4sum-ii/description/

 Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

 To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

 Example:

 Input:
 A = [ 1, 2]
 B = [-2,-1]
 C = [-1, 2]
 D = [ 0, 2]

 Output:
 2

 Explanation:
 The two tuples are:
 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

 */
public class Solution {

    // Mine solution  O(N^4), probably dynamic programming could be used to memoize sum that was calculated before
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if(A == null || B == null || C == null || D == null)
            return 0;

        int[][] numbs = new int[4][A.length];
        numbs[0] = A;
        numbs[1] = B;
        numbs[2] = C;
        numbs[3] = D;
        return getFourSumCount(numbs, 0, 0);
    }

    private int getFourSumCount(int[][] numbs, int index, int sum) {
        if(index >= numbs[0].length)
            return sum == 0 ? 1 : 0;

        int res = 0;
        for (int i = 0; i < numbs[index].length; i++) {
            res += getFourSumCount(numbs, index + 1, sum + numbs[index][i]);
        }
        return res;
    }


    // Not mine smart solution, O(N^2), space O(N)
    public int fourSumCount2222(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int temp = C[i] + D[j];
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int temp = A[i] + B[j];
                res += map.getOrDefault((-1) * temp, 0);
            }
        }

        return res;
    }
}
