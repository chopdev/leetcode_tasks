/*
https://leetcode.com/problems/maximum-average-subarray-i/description/?envType=study-plan-v2&envId=leetcode-75

You are given an integer array nums consisting of n elements, and an integer k.

Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.

 

Example 1:

Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:

Input: nums = [5], k = 1
Output: 5.00000
 

Constraints:

n == nums.length
1 <= k <= n <= 105
-104 <= nums[i] <= 104
*/
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int maxSum = 0;
        int sum = 0;
        for (int i=0; i< k; i++) 
            sum+= nums[i];
        maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum -= nums[i-k];
            sum += nums[i];
            

            if (sum > maxSum) 
                maxSum = sum;       
        }

        System.out.println(maxSum);

        return Math.round(maxSum / (double)k * 100000.0) / 100000.0;
    }
}