import javafx.util.Pair;

import java.util.HashSet;

public class Solution {

    class Coordinate {
        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (obj.getClass() != this.getClass()) {
                return false;
            }

            final Coordinate other = (Coordinate)obj;
            return other.row == this.row && other.col == this.col;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 53 * hash + this.row;
            hash = 53 * hash + this.col;
            return hash;
        }
    }

    class Context {
        int resCount;
        int walkableCount;
    }


    // My solution, O(3^S) time where S=N*M, O(S) space
    public int uniquePathsIII(int[][] grid) {
        Context context = new Context();
        Coordinate starting = scanGrid(grid, context);
        dfs(grid, starting.row, starting.col, new HashSet<>(), context);
        return context.resCount;
    }

    private Coordinate scanGrid(int[][] grid, Context context) {
        Coordinate startingNode = null;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1)
                    startingNode = new Coordinate(i, j);

                if (grid[i][j] != -1) context.walkableCount++;
            }
        }
        return startingNode;
    }

    private void dfs(int[][] grid, int i, int j, HashSet<Coordinate> visited, Context context) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == -1) return;

        Coordinate curr = new Coordinate(i, j);
        if (visited.contains(curr)) return;

        visited.add(curr);
        if (grid[i][j] == 2) {
            if (visited.size() == context.walkableCount)
                context.resCount ++;
            visited.remove(curr);
            return;
        }

        dfs(grid, i+1, j, visited, context);
        dfs(grid, i, j+1, visited, context);
        dfs(grid, i-1, j, visited, context);
        dfs(grid, i, j-1, visited, context);

        visited.remove(curr);
    }




    // Not my solution, idea is the similar though, but initial grid is modified to mark visited nodes
    // https://leetcode.com/problems/unique-paths-iii/discuss/221946/JavaPython-Brute-Force-Backtracking
    // visualization: https://leetcode.com/problems/unique-paths-iii/discuss/1554601/Java-Simple-and-Readable-Solution-or-DFS-or-Backtracking
    int res = 0, empty = 1, sx, sy, ex, ey;
    public int uniquePathsIII_2222(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        dfs(grid, sx, sy);
        return res;
    }

    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0)
            return;
        if (grid[x][y] == 2) {
            if (empty == 0) res++;
            return;
        }
        grid[x][y] = -2;
        empty--;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        grid[x][y] = 0;
        empty++;
    }
}
