/*
1696. Jump Game VI
https://leetcode.com/problems/jump-game-vi/
*/

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int maxResult(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        Deque<Integer> incStack = new ArrayDeque<>();
        incStack.addFirst(0);

        for (int i = 1; i < nums.length; i ++) {
            while(incStack.peekFirst() < i - k) {
                incStack.removeFirst(); // remove items out of the [i-k; i) window
            }
            dp[i] = nums[i] + dp[incStack.peekFirst()];
            while (!incStack.isEmpty() && dp[i] >= dp[incStack.peekLast()]) {
                incStack.removeLast();
            }
            incStack.addLast(i);
        }

        return dp[dp.length - 1];
    }
}
