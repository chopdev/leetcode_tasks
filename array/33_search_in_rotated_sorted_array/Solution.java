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

    // My O(lonN) solution
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        if(nums.length == 1) return nums[0] == target ? 0 : -1;

        int lo = 0, hi = nums.length - 1, mid = 0;
        while (lo < hi) {
            mid = (lo+hi) /2;
            if(nums[mid] > nums[hi])
                lo = mid + 1;
            else hi = mid;
        }

        int res = -1;
        if(target <= nums[nums.length - 1])
            res = Arrays.binarySearch(nums, lo, nums.length, target);
        else if (lo > 0)
            res = Arrays.binarySearch(nums, 0, lo, target);

        return res < 0 ? -1 : res;
    }
}
