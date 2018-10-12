import java.util.HashSet;
import java.util.Random;

/**
 https://en.wikipedia.org/wiki/Maze_generation_algorithm

 */
public class MazeMine {

    private int n;
    private Cell[][] maze;
    private HashSet<Cell> unvisited;

    public MazeMine(int n) {
        this.n = n;
        init();
    }

    private void init() {
        maze = new Cell[n][n];
        unvisited = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Cell cell = new Cell(i, j);
                maze[i][j] = cell;
                unvisited.add(cell);
            }
        }
    }

    private void generate(int i, int j) {

        Cell curr = maze[i][j];
        while (unvisited.size() > 0) {
            wh
        }
    }

    private boolean visited(int i, int j) {
        if(i >= n || j > n || i < 0 || j < 0)
            return true;


    }

}
