import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 1696. Jump Game VI
* https://leetcode.com/problems/jump-game-vi/
 *
 * You are given a 0-indexed integer array nums and an integer k.
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 * Return the maximum score you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
 *
 *
 * Example 2:
 *
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
 *
 *
 * Example 3:
 *
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length, k <= 105
 * -104 <= nums[i] <= 104
* */
public class Solution {

    /**
    * My solution
     * Not working for the last test case...
     * The idea is to save max possible value in dp[i] step by checking k previous steps
     *
     * Complexity: O(N*K) time, O(N) space
    * */
    public int maxResult1111(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = nums[i] + dp[i - 1];
            for (int j = i - 2; j >= i - k && i - k >= 0; j--) {
                dp[i] = Math.max(dp[i], nums[i] + dp[j]);
            }
        }

        return dp[dp.length - 1];
    }

    /**
     * Similar approach as above, but more clean.
     * https://leetcode.com/problems/jump-game-vi/discuss/1260843/C%2B%2BJavaPython-DP-and-Decreasing-Deque-Clean-and-Concise-Time-O(N)-Space-O(K)
     *
     * Let dp[i] is the maximum score we can get when ending at index i.
     * Base case: dp[0] = nums[0], we start at index 0
     * State transfer equation:
     * If we have already computed dp[0], dp[1], ..., dp[i-1], how can we compute dp[i]?
     * Since we can jump at most k steps, to arrive index i, we must jump from one of indices [i-k, i-k+1, ..., i-1].
     * So dp[i] = max(dp[i-k], dp[i-k+1], ..., dp[i-1]) + nums[i].
     * Finally, dp[n-1] is the maximum score when reaching the last index of the array, index n-1.
     * */
    public int maxResult2222(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        for (int i = 1; i < n; ++i)
            for (int j = Math.max(0, i - k); j < i; ++j)
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
        return dp[n-1];
    }

    /**
     * Same as above, but re-use nums array as dp
     * https://leetcode.com/problems/jump-game-vi/discuss/1260843/C%2B%2BJavaPython-DP-and-Decreasing-Deque-Clean-and-Concise-Time-O(N)-Space-O(K)
     * */
    public int maxResult3333(int[] nums, int k) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            int best = Integer.MIN_VALUE;
            for (int j = Math.max(0, i - k); j < i; ++j)
                best = Math.max(best, nums[j] + nums[i]);
            nums[i] = best;
        }
        return nums[n-1];
    }


    /**
     * Dynamic Programming + Decreasing Deque (Accepted)
     *
     * We need a way to get a maximum value in range [dp[i-k], dp[i-k+1], ..., dp[i-1]] better than O(K) to avoid Time Limit Exceeded.
     * This is the same with problem 239. Maximum in Sliding Window Size K problem.
     * There are total 3 ways:
     * By using MaxHeap, it costs O(logN)
     * By using TreeMap, it costs O(logK)
     * By using Decreasing Deque, it costs O(1)
     * To make this post short, I choose to use Decreasing Deque which is O(1), for other ways please reference to this 239. Maximum in Sliding Window Size K post.
     * In Decreasing Deque approach:
     * We used a deque to store indices of nums elements, elements is in decreasing order, the front is the maximum element.
     * When adding a new number nums[i], we eliminate elements which is less or equal to nums[i] in deque, which will never be chosen in the future.
     * Push index of current nums[i] to back of the deque.
     * If the last element in deque is out of range K then remove it.
     *
     * Complexity:
     *
     * Time: O(N)
     * Space: O(K)
     * */
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> dq = new ArrayDeque<>(); // store index of `nums` elements, elements is in decreasing order, the front is the maximum element.
        dq.offer(0);
        for (int i = 1; i < n; ++i) {
            // nums[i] = max(nums[i-k], nums[i-k+1], .., nums[i-1]) + nums[i] = nums[dq.front()] + nums[i]
            nums[i] = nums[dq.peekFirst()] + nums[i];

            // Remove if the last element is out of window size k
            if (i - dq.peekFirst() >= k) dq.pollFirst();

            // Add nums[i] to our deque
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast(); // Eliminate elements less or equal to nums[i], which will never be chosen in the future
            dq.offerLast(i);
        }
        return nums[n - 1];
    }
}
