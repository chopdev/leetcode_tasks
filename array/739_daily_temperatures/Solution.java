import java.util.Stack;

public class Solution {

    /**
     * My solution, not the cleanest one
    *
     * O(N) time, O(N) space
    * */
    public int[] dailyTemperatures(int[] T) {
        if (T == null)
            return null;

        int[] res = new int[T.length];
        Stack<Integer> indexes = new Stack<>();
        for (int i = 0; i<T.length - 1; i++) {
            if(T[i] >= T[i + 1]) {
                indexes.push(i);
            } else {
                indexes.push(i);
                while(indexes.size() > 0) {
                    int candidateIndex = indexes.peek();
                    if (T[candidateIndex] < T[i + 1]) {
                        indexes.pop();
                        res[candidateIndex] = i + 1 - candidateIndex;
                    } else {
                        break;
                    }
                }
            }
        }

        return res;
    }

    /**
     * Not mine, clean solution
     * https://leetcode.com/problems/daily-temperatures/discuss/109832/Java-Easy-AC-Solution-with-Stack
     *
     * Time: O(N)
     * Space: O(N)
     * */
    public int[] dailyTemperatures2222(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }
}

