/**
 152. Maximum Product Subarray
 https://leetcode.com/problems/maximum-product-subarray/

 Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

 Example 1:

 Input: [2,3,-2,4]
 Output: 6
 Explanation: [2,3] has the largest product 6.

 Example 2:

 Input: [-2,0,-1]
 Output: 0
 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

 QUESTIONS:
 1) important to ask about negative and zero numbers
* */
public class Solution {
    // Mine incorrect solution
    // I did't take into account that product of negative numbers could change the result
    // But idea was to use DP to remember max product that we could have for specific index
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];

        for(int i = 1; i<nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i-1]*nums[i]);
            max = Math.max(dp[i], max);
        }

        return max;
    }


    // Not mine O(N) time O(1) space solution
    // https://leetcode.com/problems/maximum-product-subarray/discuss/48252/Sharing-my-solution%3A-O(1)-space-O(n)-running-time
    // Basically we do not whole DP array here
    // we use previous calculations to get max and min products in each index and if this product is bigger
    // than our result product, we just update it
    public int maxProduct2222(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int maxpre = nums[0], minpre = nums[0];
        int maxhere = maxpre, minhere = minpre;
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxhere = Math.max(Math.max(maxpre * nums[i], minpre * nums[i]), nums[i]);
            minhere = Math.min(Math.min(maxpre * nums[i], minpre * nums[i]), nums[i]);

            if(maxhere > res) res = maxhere;
            maxpre = maxhere;
            minpre = minhere;
        }

        return res;
    }
}
