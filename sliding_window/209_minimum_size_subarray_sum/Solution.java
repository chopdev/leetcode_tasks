/**
209. Minimum Size Subarray Sum
https://leetcode.com/problems/minimum-size-subarray-sum/

target = 7, nums = [2,3,1,2,4,3]
[4,3]


target = 7, nums = [1,2,1,2,4,3]
[1,2,4] -> [4, 3] res = 2

target = 3, nums = [1,1,1,1,2,1]
[1,2]

target = 1, nums = [2,3,3,3]
[3]

extend the window while sum < sum of the window. then shrink from the beginning to get min length

 */


class Solution {
    // O(N) time, O(1) space
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE, sum = 0;
        int begin = 0;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            while (sum >= target) {
                res = Math.min(res, end - begin + 1);
                sum -= nums[begin];
                begin ++;
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}