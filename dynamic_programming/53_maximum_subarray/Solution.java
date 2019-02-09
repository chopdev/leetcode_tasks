/**
 53. Maximum Subarray
 https://leetcode.com/problems/maximum-subarray/

 Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class Solution {

    /*
    I wanted somehow to use two pointers here, but it is impossible to find out the criteria of when to move starting pointer
    Bottom solution uses previous calculation and
    * */

    // Not mine solution
    // O(N) time, O(N) space
    // https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
		
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : nums[i];
            max = max > dp[i] ? max : dp[i];
        }

        return max;
    }

    // My recursive interpretation of upper solution
    public int maxSubArray2222(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            dp[i] = Integer.MIN_VALUE;

        getSum(nums, nums.length - 1, dp);

        int max = Integer.MIN_VALUE;
        for (int temp : dp) {
            max = max > temp ? max : temp;
        }
        return max;
    }

    public int getSum(int[] nums, int i, int[] dp) {
        if(dp[i] != Integer.MIN_VALUE) return dp[i];
        if(i == 0) {
            dp[i] = nums[i];
            return nums[i];
        }

        int prev = getSum(nums, i - 1, dp);
        dp[i] = prev > 0 ? prev + nums[i] : nums[i];
        return dp[i];
    }

    // Not mine
    // Similar to first solution, but with O(1) memory
    // https://leetcode.com/problems/maximum-subarray/discuss/20211/Accepted-O(n)-solution-in-java
    public int maxSubArray3333(int[] A) {
        int maxSoFar=A[0], maxEndingHere=A[0];
        for (int i=1;i<A.length;++i){
            maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
            maxSoFar=Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    // cool python with modification of array
    // https://leetcode.com/problems/maximum-subarray/discuss/20396/Easy-Python-Way
}
