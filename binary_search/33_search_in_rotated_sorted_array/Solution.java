/*
33. Search in Rotated Sorted Array
https://leetcode.com/problems/search-in-rotated-sorted-array/
*/

/*At each step, at least one side is sorted. I check which side is sorted, then decide if the target lies inside that sorted range; if yes, shrink to it, otherwise search the other half.*/

class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 1) return nums[0] == target ? 0 : -1;

        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2; 
            if (nums[mid] == target) return mid;

            if (nums[mid] >= nums[l]) { // left side is sorted
                if (target >= nums[l] && target < nums[mid]) 
                    r = mid - 1;
                else 
                    l = mid + 1;
            } else { // right side is sorted
                if (target > nums[mid] && target <= nums[r]) 
                    l = mid + 1;
                else
                    r = mid - 1;
            }
        }

        return -1;
    } 
}
