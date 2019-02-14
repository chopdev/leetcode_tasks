/**
 70. Climbing Stairs

 You are climbing a stair case. It takes n steps to reach to the top.
 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 Note: Given n will be a positive integer.

 Example 1:

 Input: 2
 Output: 2
 Explanation: There are two ways to climb to the top.
 1. 1 step + 1 step
 2. 2 steps

 Example 2:

 Input: 3
 Output: 3
 Explanation: There are three ways to climb to the top.
 1. 1 step + 1 step + 1 step
 2. 1 step + 2 steps
 3. 2 steps + 1 step

* */
public class Solution {


    // My solution
    // Top down solution
    // O(N) time, O(N) space
    // As we could do 2 or 1 step, for each stair number of ways will equal
    // to number of ways from previous stair + number of way from pre previous stair
    public int climbStairs(int n) {
        return count(n, new int[n+1]);
    }

    private int count(int n, int[] memo) {
        if(n <= 1) return 1;
        if(memo[n] != 0) return memo[n];

        memo[n] = count(n - 1, memo) + count(n - 2, memo);
        return memo[n];
    }

    // My bottom up DP solution
    // O(N) space
    public int climbStairs2222(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    // My bottom up DP solution
    // O(1) space
    public int climbStairs3333(int n) {
        int a = 1, b = 1, c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }
}
