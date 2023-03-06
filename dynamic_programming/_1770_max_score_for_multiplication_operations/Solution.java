/**
 * 1770. Maximum Score from Performing Multiplication Operations
 * https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/
 *
 * You are given two 0-indexed integer arrays nums and multipliers of size n and m respectively, where n >= m.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (0-indexed) you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Note that multipliers[0] corresponds to the first operation, multipliers[1] to the second operation, and so on.
 * Remove x from nums.
 * Return the maximum score after performing m operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 *
 *
 * Example 2:
 *
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 300
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 * */
public class Solution {

    /**
     * My solution, recursion without DP
     * O(2^M) time, O(M) space
     *
     * The idea is either to take left or right item and return Max possible value at specific state
     * */
    public int maximumScore2222(int[] nums, int[] multipliers) {
        return maximumScore(multipliers, 0, nums, 0);
    }

    public int maximumScore( int[] multipliers, int i, int[] nums, int left) {
        int right = nums.length - 1 - (i - left);
        if (i == multipliers.length - 1) return Math.max(multipliers[i] * nums[left], multipliers[i] * nums[right]);

        int leftScore = maximumScore(multipliers, i + 1, nums, left + 1);
        int rightScore = maximumScore(multipliers, i + 1, nums, left);

        return Math.max(leftScore + nums[left] * multipliers[i], rightScore + nums[right] * multipliers[i]);
    }


    /**
     * My solution, recursion + memo
     * O(M^2) time because M is less than N
     * O(M^2) space
     * */
    public int maximumScore(int[] nums, int[] multipliers) {
        int[][] memo = new int[multipliers.length][nums.length];
        for (int i = 0; i < multipliers.length; i++) {
            memo[i] = new int[nums.length];
        }
        return getMaxScore(multipliers, 0, nums, 0, memo);
    }

    private int getMaxScore( int[] multipliers, int i, int[] nums, int left, int[][] memo) {
        int right = nums.length - 1 - (i - left);
        if (memo[i][left] != 0) return memo[i][left];
        if (i == multipliers.length - 1) return Math.max(multipliers[i] * nums[left], multipliers[i] * nums[right]);

        int leftScore = getMaxScore(multipliers, i + 1, nums, left + 1, memo);
        int rightScore = getMaxScore(multipliers, i + 1, nums, left, memo);

        memo[i][left] = Math.max(leftScore + nums[left] * multipliers[i], rightScore + nums[right] * multipliers[i]);
        return memo[i][left];
    }



    /**
     * Not my
     *https://leetcode.com/explore/featured/card/dynamic-programming/631/strategy-for-solving-dp-problems/4100/
     *
     * we need to iterate backwards starting from m because the base case happens when i equals m).
     * We also need to initialize dp with one extra row so that we don't go out of bounds in the first iteration of the outer loop.
     * */
    public int maximumScore3333(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int[][] dp = new int[m + 1][m + 1]; // M is less than N. Add +1 for first iteration

        for (int i = m - 1; i >= 0; i--) {
            for (int left = i; left >= 0; left--) {
                int mult = multipliers[i];
                int right = n - 1 - (i - left);
                dp[i][left] = Math.max(mult * nums[left] + dp[i + 1][left + 1],
                        mult * nums[right] + dp[i + 1][left]);
            }
        }

        return dp[0][0];
    }
}
