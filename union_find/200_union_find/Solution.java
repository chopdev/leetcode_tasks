/*
200 Number of islands

https://leetcode.com/problems/number-of-islands/

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1


Example 2:

Input:
11000
11000
00100
00011

Output: 3

* */
public class Solution {

    // Not mine solution https://leetcode.com/problems/number-of-islands/discuss/56359/Very-concise-Java-AC-solution
    // TIme complexity O(N*M) - N  number of rows, M - number of columns
    // Why? In the worst case we would have grid with ones. We would go through each cell using recursion on the
    // first step of the loop. Then we will do constant operation for other steps of the loop, so O(N*M + N*M)
    // Space O(N*M) - deepness of recursion
    public int numIslands2222(char[][] grid) {
        int size = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    size ++;
                    dfs(grid, i, j);
                }
            }
        }
        return size;
    }

    private void dfs(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return;

        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Not mine solution
    // https://leetcode.com/problems/number-of-islands/discuss/56354/1D-Union-Find-Java-solution-easily-generalized-to-other-problems
    // idea is taken from Sedgewick book (Fundamentals chapter -> Union Find)
    // Firstly, each '1' is a separate island
    // Then we combine all adjacent ones into one island
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        UnionFind uf = new UnionFind(grid);
        int rowLenght = grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    int id = i*rowLenght + j;
                    if(i + 1 < grid.length && grid[i+1][j] == '1')
                        uf.union(id, (i+1) * rowLenght + j);
                    if(j + 1 < grid[i].length && grid[i][j+1] == '1')
                        uf.union(id, i * rowLenght + j + 1);
                }
            }
        }
        return uf.size;
    }
}
