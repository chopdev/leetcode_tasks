/*
213. House Robber II
https://leetcode.com/problems/house-robber-ii/description/

Find the maximum money obtainable from houses arranged in a circle without
robbing adjacent houses. The first and last houses are also adjacent.
*/

class Solution {
    /*
     [3,4,3,5] -> 4 + 5
     [3,10,3] -> 10
     [1,4,10,3] -> 1 + 10
     [10,1,1,8] -> 10 + 1 (the first and last houses cannot both be robbed)

     dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i])

     Compare excluding the last house with excluding the first house.
     Time: O(N); auxiliary space: O(N).
    */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(robInRange(nums, 0, nums.length - 1), robInRange(nums, 1, nums.length));
    }

    // lo is inclusive; hi is exclusive.
    int robInRange(int[] nums, int lo, int hi) {
        int[] dp = new int[nums.length];

        for (int i = lo; i < hi; i++) {
            int prev2 = i - 2 < 0 ? 0 : dp[i - 2];
            int prev1 = i - 1 < 0 ? 0 : dp[i - 1];
            dp[i] = Math.max(prev1, nums[i] + prev2);
        }

        return dp[hi - 1];
    }
}
