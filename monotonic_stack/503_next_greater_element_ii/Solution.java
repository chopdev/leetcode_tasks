/*
503. Next Greater Element II
https://leetcode.com/problems/next-greater-element-ii/
*/

/**

 [4, 5, 2, 3, 1]
 [5, -1, 3, 4, 4]

 [1, 2, 3, 4]
 [2, 3, 4, -1]

 [4, 3, 2, 1]
  [-1, 4, 4, 4]

 start from end index and move to 0. Add number to stack , only if it's smaller than stack.peek(). Otherwise pop()

 it's a decreasing stack, smaller number on top
 */
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Arrays;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i --) {
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            stack.push(nums[i]);
        }

        for (int i = nums.length - 1; i >= 0; i --) { 
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }

        return res;
    }


    public int[] nextGreaterElements22222(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);

        Deque<Integer> st = new ArrayDeque<>(); // values (or indices)

        for (int i = 2 * n - 1; i >= 0; i--) {
            int x = nums[i % n];
            while (!st.isEmpty() && x >= st.peek()) st.pop();
            if (i < n) res[i] = st.isEmpty() ? -1 : st.peek();
            st.push(x);
        }
        return res;
    }
}
