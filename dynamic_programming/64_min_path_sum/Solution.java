/**
 64. Minimum Path Sum
 https://leetcode.com/problems/minimum-path-sum/

 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.

 Example:

 Input:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 Output: 7

 Explanation: Because the path 1→3→1→1→1 minimizes the sum.

* */
public class Solution {
    private int rowCount;
    private int colCount;

    // Mine Up down Memoization solution
    // Time O(N*M), space O(N*M)
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        rowCount = grid.length;
        colCount = grid[0].length;

        int[][] memo = new int[rowCount][colCount];
        int minPath = minPath(grid, rowCount - 1, colCount - 1, memo);
        return minPath == Integer.MAX_VALUE ? grid[0][0] : minPath;
    }

    private int minPath(int[][] grid, int i, int j, int[][] memo) {
        if(i == 0 && j == 0) return grid[0][0];
        if(i < 0 || j < 0) return Integer.MAX_VALUE;
        if(memo[i][j] > 0) return memo[i][j];

        memo[i][j] = grid[i][j] + Math.min(minPath(grid, i - 1, j, memo), minPath(grid, i, j - 1, memo));
        return memo[i][j];
    }

    // Not mine bottom up DP solution
    public int minPathSum2222(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for(int i = 1;i<grid.length;i++)  dp[i][0]=dp[i-1][0]+grid[i][0];
        for(int j = 1;j<grid[0].length; j++) dp[0][j]=dp[0][j-1]+grid[0][j];
        for(int i = 1;i<grid.length;i++){
            for(int j = 1;j<grid[0].length; j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
}
