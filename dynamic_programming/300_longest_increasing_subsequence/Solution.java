import java.util.Arrays;

/*
300. Longest Increasing Subsequence
https://leetcode.com/problems/longest-increasing-subsequence/
Given an unsorted array of integers, find the length of longest increasing subsequence.
For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
Note that there may be more than one LIS combination, it is only necessary for
you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/

// Great explanation of this problem
// https://habrahabr.ru/post/113108/
class Solution {

    // NOT mine solution - repeated from memory in 2 days
    // Space complexity O(n), runtime O(N^2)
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length]; // dynamic programming
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++)
            for (int j = 0; j < i; j++)
                if(nums[i] > nums[j] && dp[i] <= dp[j])
                    dp[i] = dp[j] + 1;

        int length = 0;
        for (int i = 0; i < dp.length; i++)
            if(dp[i] > length)
                length = dp[i];

        return  length;
    }


    // NOT mine runtime - O(n log n)
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
    }
}