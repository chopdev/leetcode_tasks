/*
84. Largest Rectangle in Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/description/?source=submission-noac
*/

/**

[2,1,5,6,2,3]


[1, 2, 3, 4, 4, 3, 2, 1] 

[3, 2, 1, 1, 1, 2, 3, 5]

[1, 2, 3, 4, 4, 3, 2, 1] 

[1, 2, 3, 4, 5]


height  = h
width   = rightSmaller - leftSmaller - 1 // minus one is needed to not include both beginning and end

keep increasing elements stack to track leftSmaller number of the range   - maxStack 
rightSmaller is i+1 item for which height[i+1] < height[i]
 */

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {

    public int largestRectangleArea2222(int[] heights) {
        int res = 0;
        Deque<Integer> maxStack = new ArrayDeque<>();
        int[] leftSmaller = new int[heights.length];
        int[] rightSmaller = new int[heights.length];

        for (int i = 0; i < heights.length; i ++) {
            while (!maxStack.isEmpty() && heights[maxStack.peek()] >= heights[i]) {
                maxStack.pop();
            }
            leftSmaller[i] = maxStack.isEmpty() ? -1 : maxStack.peek();
            maxStack.push(i);
        }

        maxStack.clear();
        for (int i = heights.length - 1; i >= 0; i --) {
            while (!maxStack.isEmpty() && heights[maxStack.peek()] >= heights[i]) {
                maxStack.pop();
            }
            rightSmaller[i] = maxStack.isEmpty() ? heights.length : maxStack.peek();
            maxStack.push(i);
        }

        for (int i = 0; i < heights.length; i ++) {
            int width = rightSmaller[i] - leftSmaller[i] - 1; // count of indexes between r and l excluding
            res = Math.max(width * heights[i], res);
        }

        return res;
    }



    public int largestRectangleArea(int[] heights) { 
        int res = 0;
        Deque<Integer> maxStack = new ArrayDeque<>();

        for (int i = 0; i <= heights.length; i ++) {
            // append a “0 height” sentinel to flush the stack at the end.
            int cur = (i == heights.length) ? 0 : heights[i];

            // we found right boundary, calculate area for all previous left boundaries
            while (!maxStack.isEmpty() && cur < heights[maxStack.peek()]) {
                int mid = maxStack.pop();
                int leftSmaller = maxStack.isEmpty() ? - 1 : maxStack.peek();
                int width =  i - leftSmaller - 1;
                int height = heights[mid];
                res = Math.max(res, width * height);
            }
            maxStack.push(i);
        }

        return res;
    }
}
