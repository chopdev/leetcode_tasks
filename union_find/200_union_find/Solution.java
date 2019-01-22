public class Solution {

    public int numIslands(char[][] grid) {
        Point[][] field = new Point[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') field[i][j] = new Point(i, j);
            }
        }


    }

    // returns component (island) to which this point is related
    private Point find(Point[][] field, int i, int j) {
        return null;
    }

    // union two points in one component
    // returns current count of islands
    private int union(Point[][] field, int size, int i, int j, int k, int m) {
        return 0;
    }
}
