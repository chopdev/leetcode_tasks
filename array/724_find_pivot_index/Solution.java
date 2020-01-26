/* 
724. Find Pivot Index
https://leetcode.com/problems/find-pivot-index/

Given an array of integers nums, write a method that returns the "pivot" index of this array.
We define the pivot index as the index where the sum of the numbers to the left of the index
 is equal to the sum of the numbers to the right of the index.
If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

Example 1:

Input: 
nums = [1, 7, 3, 6, 5, 6]
Output: 3
Explanation: 
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.
 

Example 2:

Input: 
nums = [1, 2, 3]
Output: -1
Explanation: 
There is no index that satisfies the conditions in the problem statement.
 

Note:

The length of nums will be in the range [0, 10000].
Each element nums[i] will be an integer in the range [-1000, 1000].

 */
class Solution {

    /* 
    My solution O(1) space O(N) time
     */
    public int pivotIndex(int[] nums) {
        int left = 0, right = 0;
        for (int i=1; i < nums.length; i++) {
            right += nums[i];
        }
        for(int i = 0; i< nums.length; i++) {
            if(i > 0) {
                left += nums[i - 1];
                right -= nums[i];
            }
            
            if(left == right) return i;
        }
        
        return -1;
    }

    /* 
        Similar solution 
        https://leetcode.com/problems/find-pivot-index/discuss/486587/Simple-Java-O(n)
     */
    public int pivotIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int leftSum = 0;
        for (int i=0; i<nums.length; i++) {
            if (leftSum == total - nums[i] - leftSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}