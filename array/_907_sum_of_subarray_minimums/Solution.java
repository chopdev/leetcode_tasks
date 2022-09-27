import java.util.Stack;

/**
 * 907. Sum of Subarray Minimums
 *
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 *
 *
 * Example 2:
 *
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 *
 *Constraints:
 *
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 * */
public class Solution {
    // My O(N^2) solution
    public int sumSubarrayMins2222(int[] arr) {
        long sum = 0;
        for (int i = 0; i < arr.length; i ++) {
            int min = arr[i];
            for (int j = i; j < arr.length; j ++) {
                min = Math.min(min, arr[j]);
                sum += min;
                sum %= 1000_000_007;
            }
        }
        return (int)sum % 1000_000_007;
    }

    /**
     * Implemented by me, idea not mine (inspired by 2104 task)
     *
     *
     *
     * [5]
     * [5, 4]
     * [4, 5]
     *
     * [5,4,3,1,2,4]
     * [1,2,3,1,5,2,4]
     * [1 5 3 5 6 7 5]
     * */
    public int sumSubarrayMins(int[] arr) {
        long sum = 0;
        Stack<Integer> increasingStack = new Stack<>(); //monotonous increasing stack,

        for (int i = 0; i <= arr.length; i ++) {
            while (!increasingStack.isEmpty() && (i == arr.length || arr[increasingStack.peek()] > arr[i])) { // it means we found i that will be smaller than current index, so we want to calculate distance to i
                int current = increasingStack.pop();
                int k = increasingStack.isEmpty() ? -1 : increasingStack.peek(); // smaller item than current from the left side
                // (i - current) * (current - k) - distance to the smaller element (or -1 index) on the left side times distance to the smaller element on the right side (or end of array)
                // so we count amount of times some element appeared as a smallest on specific range
                sum += (arr[current] * (i - current) * (current - k)) % 1000_000_007;
                sum %= 1000_000_007;
            }
            increasingStack.push(i);
        }

        return (int)sum;
    }

    // Not mine with dp https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170769/Java-O(n)-monotone-stack-with-DP
    public int sumSubarrayMins3333(int[] A) {
        Stack<Integer> stack = new Stack<>();
        int[] dp = new int[A.length + 1];
        stack.push(-1);
        int result = 0, M = (int)1e9 + 7;
        for (int i = 0; i < A.length; i++) {
            while (stack.peek() != -1 && A[i] <= A[stack.peek()]) {
                stack.pop();
            }
            dp[i + 1] = (dp[stack.peek() + 1] + (i - stack.peek()) * A[i]) % M;
            stack.push(i);
            result += dp[i + 1];
            result %= M;
        }
        return result;
    }
}
