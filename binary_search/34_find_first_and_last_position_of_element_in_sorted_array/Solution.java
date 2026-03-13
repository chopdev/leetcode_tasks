/*
34. Find First and Last Position of Element in Sorted Array
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
*/

/*

2,2,2,4,4,4,4,5,7,7,8,8,8,8,8,10


1,1,1,1,1,1,1,2,2,2,2
*/

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[] {-1, -1};

        int lowerBound = getLowerBound(nums, target);

        if (lowerBound == nums.length || nums[lowerBound] != target) 
            return new int[] {-1, -1};

        int upperBound = getLowerBound(nums, target + 1) - 1;

        return new int[] {lowerBound, upperBound};
    }

    int getLowerBound(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
