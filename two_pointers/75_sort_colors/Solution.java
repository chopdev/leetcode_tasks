/*
75. Sort Colors
https://leetcode.com/problems/sort-colors/
*/

/*
 the idea it to have two pinters l and r. 
 - l is for writing 0 values and moving right.
 - r is for writing 2 values and moving left

 left region [0..l-1] = all 0s (done)
 right region [r+1..n-1] = all 2s (done)
 middle [l..i-1] = processed (all 1s in the classic version)
 [i..r] = unknown

 * */

class Solution {
    public void sortColors(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        // including r in a case on r index is 0 that we need to move 
        for (int i = 0; i <= r; i++) {
            if (nums[i] == 0) {
                swap(nums, i, l);
                l++;
            } else if (nums[i] == 2) {
                swap(nums, i, r);
                r--;
                i--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
