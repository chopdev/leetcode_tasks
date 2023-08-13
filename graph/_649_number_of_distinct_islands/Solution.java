import java.util.HashSet;

/**
 * 694. Number of Distinct Islands
 * https://leetcode.com/problems/number-of-distinct-islands
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 * Return the number of distinct islands.
 *
 * Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 * Output: 1
 *
 * Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 * */
public class Solution {

    /**
     * My implemenation, idea taken from https://leetcode.com/problems/number-of-distinct-islands/solutions/108475/java-very-elegant-and-concise-dfs-solution-beats-100/?envType=study-plan-v2&envId=premium-algo-100
     * */
    public int numDistinctIslands(int[][] grid) {
        HashSet<String> islands = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    getIslandForm(grid, i, j, 0, 0, path);
                    islands.add(path.toString());
                }
            }
        }
        return islands.size();
    }

    int[][] directions = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
    // i, j - absolute coordinates
    // x, y - relative coordinates to the start of the "1" tree
    private void getIslandForm(int[][] grid, int i, int j, int x, int y, StringBuilder path) {
        if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0 || grid[i][j] != 1) return;
        path.append(x); // remember relative coordinates of specific node
        path.append(y);
        for (int[] dir : directions) {
            grid[i][j] = 2; // mark as visited
            getIslandForm(grid, i + dir[0], j + dir[1], x + dir[0], y + dir[1], path);
        }
    }
}
