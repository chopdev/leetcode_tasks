/*
200. Number of Islands
https://leetcode.com/problems/number-of-islands/

Count the islands in a grid where '1' represents land and '0' represents water.
An island consists of land cells connected horizontally or vertically.
*/

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    count++;
                }
            }
        }

        return count;
    }

    void dfs(char[][] grid, int r, int c) {
        if (r < 0 ||
            r >= grid.length ||
            c >= grid[0].length ||
            c < 0 ||
            grid[r][c] != '1') return;

        grid[r][c] = '_'; // mark as visited

        dfs(grid, r + 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r - 1, c);
        dfs(grid, r, c - 1);

        // System.out.println(Arrays.deepToString(grid));
    }
}
