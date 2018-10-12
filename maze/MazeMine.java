import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

/**
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm
 */
public class MazeMine {

    private int n;
    private int unvisitedCount;
    private Cell[][] maze;

    public MazeMine(int n) {
        this.n = n;
        init();
        generate(0, 0);
    }

    private void init() {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n);

        maze = new Cell[n][n];
        unvisitedCount = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = new Cell(i, j);
            }
        }
    }

    private void generate(int y, int x) {
        Cell curr = maze[y][x];
        Stack<Cell> stack = new Stack<>();
        stack.add(curr);

        while (unvisitedCount > 0) {

            if (visited(curr.i + 1, curr.j) &&
                    visited(curr.i - 1, curr.j) &&
                    visited(curr.i, curr.j + 1) &&
                    visited(curr.i, curr.j - 1)) {

                if(stack.size() == 0) break;
                curr = stack.pop();
                continue;
            }

            curr.visited = true;
            unvisitedCount--;
            stack.push(curr);

            Random r = new Random();
            while (true) {
                int random = StdRandom.uniform(4);
                        //r.nextInt(4);
                if (random == 0 && !visited(curr.i + 1, curr.j)) {
                    curr.bottom = true;
                    maze[curr.i + 1][curr.j].top = true;
                    curr = maze[curr.i + 1][curr.j];
                    break;
                } else if (random == 1 && !visited(curr.i - 1, curr.j)) {
                    curr.top = true;
                    maze[curr.i - 1][curr.j].bottom = true;
                    curr = maze[curr.i - 1][curr.j];
                    break;
                } else if (random == 2 && !visited(curr.i, curr.j + 1)) {
                    curr.right = true;
                    maze[curr.i][curr.j + 1].left = true;
                    curr = maze[curr.i][curr.j + 1];
                    break;
                } else if (random == 3 && !visited(curr.i, curr.j - 1)) {
                    curr.left = true;
                    maze[curr.i][curr.j - 1].right = true;
                    curr = maze[curr.i][curr.j - 1];
                    break;
                }
            }
        }
    }

    private boolean visited(int i, int j) {
        if (i >= n || j > n || i < 0 || j < 0)
            return true;

        return maze[i][j].visited;
    }


    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(n/2.0 + 0.5, n/2.0 + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);

    /*    StdDraw.line(0, 0, 1, 0);
        StdDraw.line(0, 1, 1, 1);*/
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!maze[i][j].bottom) StdDraw.line(j, n-i-1 , j+1, n-i-1); // x0 y0  x1 y1
                if(!maze[i][j].top) StdDraw.line(j, n-i , j + 1, n-i);
                if(!maze[i][j].right) StdDraw.line(j + 1, n-i, j+1, n-i-1);
                if(!maze[i][j].left) StdDraw.line(j - 1, n-i, j-1, n-i-1);
            }
        }
        StdDraw.show();
    }

}
