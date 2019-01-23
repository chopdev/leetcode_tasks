public class Solution {

    // My bad partially working solution
    // idea is taken from Sedgewick book (Fundamentals chapter -> Union Find)
    // Firstly, each '1' is a separate island
    // Then we combine all adjacent ones into one island
    public int numIslands(char[][] grid) {
        Point[][] field = new Point[grid.length][grid[0].length];
        int islandsCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1') {
                    field[i][j] = new Point(i, j);
                    islandsCount ++;
                }
            }
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if(i + 1 < field.length && field[i][j] != null && field[i+1][j] != null)
                    islandsCount = union(field, islandsCount, i, j, i +1, j);
                if(j + 1 < field[i].length && field[i][j] != null && field[i][j+1] != null)
                    islandsCount = union(field, islandsCount, i, j, i, j + 1);
            }
        }

        return islandsCount;
    }

    // returns component (island) to which this point is related
    private Point find(Point[][] field, int i, int j) {
        while (field[i][j].i != i && field[i][j].j != j) {
            Point curr = field[i][j];
            i = curr.i;
            j = curr.j;
        }

        return field[i][j];
    }

    // union two points in one component
    // returns current count of islands
    private int union(Point[][] field, int size, int i, int j, int k, int m) {
        Point firstParent = find(field, i, j);
        Point secondParent = find(field, k, m);

        if(firstParent == secondParent) return size;

        field[i][j] = secondParent;
        return --size;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////


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
}
