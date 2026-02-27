/*
85. Maximal Rectangle
https://leetcode.com/problems/maximal-rectangle/description/
*/

import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int res = 0;

        for (int row = 0; row < matrix.length; row ++) {
            for (int col = 0; col < matrix[0].length; col ++) {
                if (matrix[row][col] == '1') {
                    heights[col] ++;
                } else {
                    heights[col] = 0; 
                }   
            }

            res = Math.max(res, getMaxArea(heights));
        }

        return res;
    }

    public int getMaxArea(int[] heights) {
        Deque<Integer> incStack = new ArrayDeque<>();
        int area = 0;
        int n = heights.length;

        // move right border
        for (int i = 0; i <= n; i ++) {
            int curr = i == n ? 0 : heights[i]; // to process the last rectangle

            while (!incStack.isEmpty() && curr < heights[incStack.peek()]) {
                // i is right smaller border
                // mid represents the smallest item between (next incStack.peek() e.g. leftBorder, and i e.g. rightBorder)
                int mid = incStack.pop(); 
                int height = heights[mid];
                int leftBorder = incStack.isEmpty() ? -1 : incStack.peek();
                int width = i - leftBorder - 1; // excluding left and right borders
                area = Math.max(area, height * width);
            }
            incStack.push(i);
        }

        return area;
    }
}
