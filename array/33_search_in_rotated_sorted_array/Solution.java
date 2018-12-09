import java.util.Arrays;

/**
 https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 33. Search in Rotated Sorted Array

 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 You are given a target value to search. If found in the array return its index, otherwise return -1.
 You may assume no duplicate exists in the array.

 Your algorithm's runtime complexity must be in the order of O(log n).

 Example 1:

 Input: nums = [4,5,6,7,0,1,2], target = 0
 Output: 4

 Example 2:
 Input: nums = [4,5,6,7,0,1,2], target = 3
 Output: -1


 IMPORTANT HERE
 to ask if we could have duplicates. In task it is mentioned that we not
* */
public class Solution {

    // My good O(lonN) solution
    // examples
    // 7, 0, 1, 2, 4, 5, 6
    // 2, 4, 5, 6, 7, 0, 1
    // My idea is to find the lowest element of rotated array
    // Where is it? if mid value < last value of array, than in the left side of array
    // if mid > last value, than lowest element is on the right side
    // After finding of the lowest element we could understand in which part of array to seek
    // after lowest element or before
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1) return nums[0] == target ? 0 : -1;

        int lo = 0, hi = nums.length - 1, mid = 0;

        // Finding lowest element
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi:
        // suppose that mid==hi, then from this formula mid = (lo+hi) /2; -> lo==hi and loop would have been terminated.
        while (lo < hi) {
            mid = (lo+hi) /2;
            if(nums[mid] > nums[hi]) lo = mid + 1;
            else hi = mid;
        }

        int res = -1;
        if(target <= nums[nums.length - 1]) // target is less than maximum in the right side
            res = Arrays.binarySearch(nums, lo, nums.length, target);
        else if (lo > 0) // lo is bigger than 0, so there is left side
            res = Arrays.binarySearch(nums, 0, lo, target);

        return res < 0 ? -1 : res;
    }


}
