/**
 329. Longest Increasing Path in a Matrix
 https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

 Given an integer matrix, find the length of the longest increasing path.

 From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

 Example 1:

 Input: nums =
 [
 [9,9,4],
 [6,6,8],
 [2,1,1]
 ]
 Output: 4
 Explanation: The longest increasing path is [1, 2, 6, 9].


 Example 2:

 Input: nums =
 [
 [3,4,5],
 [3,2,6],
 [2,2,1]
 ]
 Output: 4

 Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * */
public class Solution {


	// Not mine O(N*M) solution
	// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78308/15ms-Concise-Java-Solution
	// Key point here is that this graph in matrix has no loops, because we connect only nodes when 1 is bigger than other
	// That's why we can use DFS here + memoization to remember max length at each point
    int[][] sides = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0) return 0;

        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        int[][] memo = new int[rowCount][colCount];

        int max = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                max = Math.max(max, dfs(matrix, i, j, rowCount, colCount, memo));
            }
        }

        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int rowCount, int colCount, int[][] memo) {
        if(memo[i][j] > 0) return memo[i][j];

        for(int[] side : sides) {
            int row = i + side[0];
            int col = j + side[1];
            if(row < 0 || row >= rowCount || col < 0 || col >= colCount || matrix[row][col] <= matrix[i][j])
                continue;

            int len = dfs(matrix, row, col, rowCount, colCount, memo);
            memo[i][j] = Math.max(memo[i][j], 1 + len);
        }

        memo[i][j] = memo[i][j] > 0 ? memo[i][j] : 1;
        return memo[i][j];
    }
}
