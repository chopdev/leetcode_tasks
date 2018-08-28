import java.util.ArrayList;
import java.util.HashSet;

/*
* Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
The robot can only move in two directions, right and down, but certain cells are "off limits" such that
the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
the bottom right.

* */
public class Solution {
    // Mine solution, Complexity O(2^N) N=number of rows + number of columns
    // bottom up solution
    public ArrayList<Point> getPath(boolean[][] grid) {
        if(grid == null) return null;
        ArrayList<Point> res = new ArrayList<>();
        if(!gPath(grid, 0, 0, res)) return null;
        return res;
    }

    private boolean gPath(boolean[][] grid, int x, int y, ArrayList<Point> res) {
        int rows = grid.length;
        int columns = grid[0].length;

        if(x >= columns || y >=rows || !grid[y][x]) return false;
        if(x == columns - 1 && y == rows - 1) return true;

        boolean right = gPath(grid, x + 1, y, res);
        boolean left = false;
        if(!right) left = gPath(grid, x, y+1, res);

        if(!right && !left) return false;
        res.add(new Point(y, x));
        return true;
    }



    // Not mine solution, Uses simple dynamic programming, top down solution
    // Complexity O(col*row) - because in the worst case we can visit every cell only ones
    // From last cell (r, c) we can get from (r-1, c) or from (r, c-1)
    // to (r-1, c) from (r-1, c-1) (r-2, c)
    // to (r, c-1) from (r-1, c-1) (r, c-2)
    // So by caching not successful cells we remove redundant branches of recursive call
    public ArrayList<Point> getPathDP(boolean[][] grid) {
        if(grid == null) return null;
        ArrayList<Point> res = new ArrayList<>();
        HashSet<Point> failedPoints = new HashSet<>();
        getPathDP(grid, grid.length - 1, grid[0].length - 1, res, failedPoints);
        return res;
    }

    public boolean getPathDP(boolean[][] maze,
                             int row,
                             int col,
                             ArrayList<Point> path,
                             HashSet<Point> failedPoints) {
        if(row >= maze.length || col >= maze[0].length || !maze[row][col]) return false;
        Point p = new Point(row, col);
        if(failedPoints.contains(p)) return false;

        boolean isStart = row == 0 && col == 0;

        if(isStart || getPathDP(maze, row - 1, col, path, failedPoints) ||
                getPathDP(maze, row, col - 1, path, failedPoints)){
            path.add(p);
            return true;
        }

        failedPoints.add(p); // Cache result
        return false;
    }
}
