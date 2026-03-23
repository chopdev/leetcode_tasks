/*
1838. Frequency of the Most Frequent Element
https://leetcode.com/problems/frequency-of-the-most-frequent-element/
*/

import java.util.Arrays;

class Solution {
    public int maxFrequency(int[] nums, int k) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        Arrays.sort(nums);

        int begin = 0;
        int res = 0;
        long sum = 0;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            while (begin < nums.length && (long) nums[end] * (end - begin + 1) > sum + k) {
                // shrink window until we can make all elements equal to nums[end]
                sum -= nums[begin];
                begin ++;
            }

            res = Math.max(res, end - begin + 1);
        }

        return res;  
    }

}
