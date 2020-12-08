/*
59. Spiral Matrix II
https://leetcode.com/problems/spiral-matrix-ii/

Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:

Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]
Example 2:

Input: n = 1
Output: [[1]]

Constraints:
1 <= n <= 20


1  2  3  4  5  6 
20 21 22 23 24 7
19 32 33 34 25 8
18 31 36 35 26 9
17 30 29 28 27 10
16 15 14 13 12 11

1  2  3  4  5
16 17 18 19 6
15 25 26 20 7
14 23 22 21 8
13 12 11 10 9
*/

class Solution {

    /* 
        My solution, O(N^2) time, O(N^2) space
    */
    public int[][] generateMatrix(int n) {
        int row = 0;
        int col = 0;
        int val = 1;
        int[][] matrix = new int[n][n];
        for (int len = n; len >= 1; len -= 2) {
            val = fillCircle(row, col, val, len, matrix);
            row++;
            col++;
        }
        return matrix;
    }

    private int fillCircle(int startRow, int startCol, int val, int len, int[][] matrix) {
        for (int col = startCol, row = startRow ; col < startCol + len; col ++) {
            matrix[row][col] = val;
            val ++;
        }

        for (int row = startRow + 1, col = startCol + len -1; row < startRow + len; row ++) {
            matrix[row][col] = val;
            val++;
        }

        for (int row = startRow + len - 1, col = startCol + len - 2; col >= startCol; col --) {
            matrix[row][col] = val;
            val ++;
        }

        for(int row = startRow + len - 2, col = startCol; row > startRow; row --) {
            matrix[row][col] = val;
            val++;
        }

        return val;
    }
}