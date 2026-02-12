/**

1004. Max Consecutive Ones III
https://leetcode.com/problems/max-consecutive-ones-iii/

nums = [1,1,1,0,0,0,1,1,1,1,0]
k = 3
res =[1,1,1,1,1,1,1,1,1,1,0]



nums = [1, 1, 0,1, 1, 0, 0, 1, 1, 1, 1, 1, 1]
k = 1


k = 0 -> return longest subarray of 1s

if k > nums.length, return nums.length


extend the window while count <= k, where count is count of 0. When count > k, shrink the window from the beginning.

"Maintain a window with at most k zeros. Expand right; if zeros exceed k, shrink from left until valid again. Track max window length."

 */


class Solution {
    // Time O(n), space O(1)
    public int longestOnes(int[] nums, int k) {
        if (k > nums.length) return nums.length;

        int count = 0;
        int begin = 0;
        int res = k;

        for (int end = 0; end < nums.length; end ++) {
            if (nums[end] == 0) count++;

            while (count > k) {
                if (nums[begin] == 0) count --;
                begin ++;
            }

            res = Math.max(res, end - begin + 1);
        }

        return res;
    }
}