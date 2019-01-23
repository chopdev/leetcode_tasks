import java.lang.reflect.Field;

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
}
