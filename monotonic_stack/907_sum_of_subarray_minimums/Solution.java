/*
907. Sum of Subarray Minimums
https://leetcode.com/problems/sum-of-subarray-minimums/
*/

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {

    /*
    For each index i:

    L = number of choices for the left boundary such that arr[i] is the minimum

    R = number of choices for the right boundary such that arr[i] is the minimum
    Then contribution = arr[i] * L * R.

    To compute L and R, use monotonic increasing stacks to find:

    PLE(i) = previous strictly less element index (to the left)

    NLE(i) = next less or equal element index (to the right)

    That strict/non-strict pairing is important to avoid double-counting when there are equal values.
    */
    public int sumSubarrayMins(int[] arr) {
        final int MOD = 1_000_000_007;
        int n = arr.length;

        int[] ple = new int[n]; // previous less (strict)
        int[] nle = new int[n]; // next less or equal

        Deque<Integer> st = new ArrayDeque<>();

        // PLE: previous strictly less
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) st.pop();
            ple[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        st.clear();
        // NLE: next less or equal
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
            nle[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            long L = i - ple[i];
            long R = nle[i] - i;
            res = (res + (arr[i] * L % MOD) * R) % MOD;
        }

        return (int) res;
    }


    // My solution, O(N^2)
    public int sumSubarrayMins2222(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = arr.length;
        int[] smaller = new int[n];
        long res = 0;

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                stack.pop();
            }
            smaller[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        for (int i = 0; i < n; i++) { 
            int currMin = i;
            for (int j = i; j < n; j ++) {
                // if smaller[currMin] == -1  it means there is no smaller item to the right
                int nextMin = smaller[currMin] == -1 ? currMin : smaller[currMin];
                if (nextMin <= j) currMin = nextMin;
                res += arr[currMin];
            } 
        }

        return (int) (res % (Math.pow(10, 9) + 7));
     }
}
