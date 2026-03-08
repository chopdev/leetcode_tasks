/*
862. Shortest Subarray with Sum at Least K
https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
*/

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1]; // IMPORTANT: use long for sum as it increases significantly
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        Deque<Integer> dq = new ArrayDeque<>(); // increasing stack
        for (int i = 0; i <= n; i++) {
            while (!dq.isEmpty() && prefixSum[i] - prefixSum[dq.peekFirst()] >= k) {
                // shrink from the left to get the shortest ending at i
                minLen = Math.min(minLen, i - dq.removeFirst());
            }

            // maintain increasing prefix sums
            while (!dq.isEmpty() && prefixSum[i] < prefixSum[dq.peekLast()]) {
                dq.removeLast();
            }

            dq.addLast(i);
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}
