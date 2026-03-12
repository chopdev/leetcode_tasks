/*
153. Find Minimum in Rotated Sorted Array
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
*/

class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (min > nums[mid]) min = nums[mid];
            if (min > nums[l]) min = nums[l];
            if (min > nums[r]) min = nums[r];

            if (nums[mid] > nums[l]) { // left side is ordered
                if (nums[l] <= nums[r] && nums[l] <= nums[mid])
                    r = mid - 1;
                else 
                    l = mid + 1;
            } else { // right side is ordered, smaller item is always on rotated half
                r = mid - 1;
            }
        }

        return min;
    }


    /// not my solution
    public int findMin2222(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            /// when nums[mid] > nums[r], minimum must be on the right of mid
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid; // do not +1 as mid can be the smallest
            }
        }

        return nums[l];
    }

}
