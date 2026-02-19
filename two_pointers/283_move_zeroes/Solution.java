/*
283. Move Zeroes
https://leetcode.com/problems/move-zeroes/
*/

/**
Keep two indexes, w - to write to, i regular one that checks current value

[1, 2, 3]

[0, 0, 0]

[0, 1, 0]

[0, 0, 1]
 */

class Solution {
    public void moveZeroes(int[] nums) {
        int w = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[w];
                nums[w] = nums[i];
                nums[i] = temp;
                w++;
            }
        }
    }

    // overwrite then fill zeros
    public void moveZeroes2222(int[] nums) {
        int w = 0;
        for (int x : nums) if (x != 0) nums[w++] = x;
        while (w < nums.length) nums[w++] = 0;
    }
}
