/**
 * 629. K Inverse Pairs Array
 *
 * For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
 *
 * Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there
 * are exactly k inverse pairs. Since the answer can be huge, return it modulo 10^9 + 7.
 *
 * Example 1:
 *
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
 *
 *
 * Example 2:
 *
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * 0 <= k <= 1000
 * */
public class Solution {
    /**
     * https://leetcode.com/problems/k-inverse-pairs-array/solutions/127746/k-inverse-pairs-array/
     *
     * consider the case for [2,4,1,3]. This array can be obtained from [1,2,3,4] by shifting 2 by one position
     * towards the left first and then shifting 4 by 2 positions towards the left. Thus, the total number of
     * displacements is 3, which is equal to the number of inverse pairs in the new array.
     *
     * This rule of displacements holds true because, whenever a number is shifted y times towards the left starting
     * from the array [1,2,3,4], after the shift, y numbers smaller than it lie towards its right, giving a total of y inverse pairs.
     *
     * NOTE: Basically count of shifts from right == count of inversions K.
     *
     *
     * Now, if we want to add a new number 5 to this array to consider an array with n=5 [1, 2, 3, 4, 5].
     * - 5 can be placed in the end, and it won't change count of inversions that we already had. Basically K is still 3 in this case: [2,4,1,3,5]  k = 3
     * - 5 can be placed in any middle point of the array, let's say "p" and it will add  p additional inversions to existing k: [2,4,5,1,3]  k = 3 + 2
     *
     * Let's say count(i,j) represents the number of arrangements with i elements and exactly j inverse pairs.
     * - if n = 0, no inverse pairs exist, count(0, k) = 0
     * - if k = 0, only one arrangement is possible, when array is sorted e.g. [1,2,3,4],  count(n, 0) = 1
     * - count(n, k) = SUM(count(n-1, k) + count(n-1, k-1) + count(n-1, k - 2) ... + count(n-1, 0))  .This is logic comes from the idea that we can place Nth element in any position
     *
     *
     * Time: O(N*K*min(N,K)) - N*K the function is called until memo is filled, each function call takes O(min(N, K))
     * Space: O(N*K) for memo array, N is a depth of recursion
     * */
    public int kInversePairs(int n, int k) {
        Integer[][] memo = new Integer[n + 1][k + 1]; // good trick with using Integer not int, to compare with null
        return kInversePairsRecursive(n, k, memo);
    }

    public int kInversePairsRecursive(int n, int k, Integer[][] memo) {
        if (n == 0) return 0;
        if (k == 0) return 1;
        if (memo[n][k] != null) return memo[n][k];
        int countOfInversions = 0;
        for (int i = 0; i <= n - 1 && i <= k; i ++) {
            countOfInversions = (countOfInversions + kInversePairsRecursive(n - 1, k - i, memo)) % 1000000007;
        }
        memo[n][k] = countOfInversions;
        return countOfInversions;
    }


    /**
     * Not my DP solution
     * https://leetcode.com/problems/k-inverse-pairs-array/solutions/127746/k-inverse-pairs-array/
     * */
    public int kInversePairs2222(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0)
                    dp[i][j] = 1;
                else {
                    for (int p = 0; p <= Math.min(j, i - 1); p++)
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - p]) % 1000000007;
                }
            }
        }
        return dp[n][k];
    }

}
