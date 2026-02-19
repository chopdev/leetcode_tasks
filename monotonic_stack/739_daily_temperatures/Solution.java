/*
739. Daily Temperatures
https://leetcode.com/problems/daily-temperatures/
*/

/**

begin from the end of array.
keep min stack of indexes
pop items from stack until we see larger value than current
if stack is emtpy, then 0 - no warmer day after current one

 */

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] answer = new int[temperatures.length];

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            answer[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return answer;
    }
}
