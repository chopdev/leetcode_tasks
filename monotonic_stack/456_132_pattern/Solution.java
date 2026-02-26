/*
456. 132 Pattern
https://leetcode.com/problems/132-pattern/
*/

/**

the idea is to find closest larger to the right but smaller even futher to the right. Decreasing monotonic stack can help to find that
 */

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;

        int last = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>(); // decreasing stack
        
        for (int i = n - 1; i >= 0; i --) {
            if (nums[i] < last) return true;

            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                last = stack.pop(); // possible '2'
            }

            // middle element - possible '3'
            stack.push(nums[i]);
        }

        return false;
    }
}
