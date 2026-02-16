/*
16 3sum closest
https://leetcode.com/problems/3sum-closest/description/
*/

/**

7
[-1,2,1,1,1,2,-2,-3, -4, 5]
[-4, -3, -2, -1, 1, 1, 1, 2, 2, 5] -> 6


    1
    [-4, -1, 1, 2]
 */

import java.util.Arrays;

class Solution {


    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i ++) {
            int begin = i + 1;
            int end = nums.length - 1;

            while (begin < end) {
                int sum = nums[i] + nums[begin] + nums[end];
                if (sum == target) return target;

                res = Math.abs(target - res) > Math.abs(target - sum) ? sum : res;

                if (target > sum) {
                    begin ++; 
                } else {
                    end --;
                }
            }
        }

        return res;
    }
}
