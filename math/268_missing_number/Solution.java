/*
268. Missing Number
https://leetcode.com/problems/missing-number/

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

* */
public class Solution {
    // My good solution using sum of arithmetic progression
    // time O(N), space O(1)
    public int missingNumber(int[] nums) {
        int biggest = nums.length;
        int count = biggest + 1;
        int sum = biggest*count /2;
        int currSum = 0;
        for(int numb : nums) {
            currSum += numb;
        }
        return sum - currSum;
    }
}
