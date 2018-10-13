import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

/**
 * https://en.wikipedia.org/wiki/Maze_generation_algorithm
 *
 *
 * Recursive backtracker
 *
 * Recursive backtracker on a hexagonal grid
 * The depth-first search algorithm of maze generation is frequently implemented using backtracking:
 *
 * 1. Make the initial cell the current cell and mark it as visited
 * 2. While there are unvisited cells
 *      1.If the current cell has any neighbours which have not been visited
 *          1.Choose randomly one of the unvisited neighbours
 *          2.Push the current cell to the stack
 *          3.Remove the wall between the current cell and the chosen cell
 *          4. Make the chosen cell the current cell and mark it as visited
 *      2.Else if stack is not empty
 *          1.Pop a cell from the stack
 *          2.Make it the current cell
 */
// Easy to understand
public class MazeMine {

    private int n;
    private Cell[][] maze;

    public MazeMine(int n) {
        this.n = n;
        init();
        generateInCommonCoordinateSystem(0, 0);
    }

    private void init() {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n);

        maze = new Cell[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = new Cell(i, j);
            }
        }
    }

    // Mine iterative implementation  of Recursive backtracker algorithm
    // Uses from top-left coordinate system (usual to 2 dimensional arrays)
    // there is some bug here
    private void generate(int y, int x) {
        int unvisitedCount = n;
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
                int random = r.nextInt(4);
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

    // Similar to Sedgwik recursive implementation
    // Easy to understand
    // All cells should be visited in the end. We select random neighbor cell that is unvisited and break the wall.
    // Moving to next cell. Each cell would have at least one broken wall at the end, because the wall is broken when we visit cell
    private void generateInCommonCoordinateSystem(int x, int y) {
        Cell curr = maze[x][y];
        curr.visited = true;

        while (!visited(curr.i + 1, curr.j) ||
                !visited(curr.i - 1, curr.j) ||
                !visited(curr.i, curr.j + 1) ||
                !visited(curr.i, curr.j - 1)) {

            Random r = new Random();
            while (true) {
                int random = r.nextInt(4);
                if (random == 0 && !visited(curr.i, curr.j - 1)) {
                    curr.bottom = true;
                    maze[curr.i][curr.j - 1].top = true;
                    generateInCommonCoordinateSystem(curr.i, curr.j-1);
                    break;
                } else if (random == 1 && !visited(curr.i, curr.j + 1)) {
                    curr.top = true;
                    maze[curr.i][curr.j + 1].bottom = true;
                    generateInCommonCoordinateSystem(curr.i, curr.j+1);
                    break;
                } else if (random == 2 && !visited(curr.i + 1, curr.j)) {
                    curr.right = true;
                    maze[curr.i + 1][curr.j].left = true;
                    generateInCommonCoordinateSystem(curr.i + 1, curr.j);
                    break;
                } else if (random == 3 && !visited(curr.i - 1, curr.j)) {
                    curr.left = true;
                    maze[curr.i - 1][curr.j].right = true;
                    generateInCommonCoordinateSystem(curr.i - 1, curr.j);
                    break;
                }
            }
        }
    }

    private boolean visited(int i, int j) {
        if (i >= n || j >= n || i < 0 || j < 0)
            return true;

        return maze[i][j].visited;
    }


    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(n - 0.5, n - 0.5, 0.375);
        StdDraw.filledCircle(0.5, 0, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if(!maze[x][y].bottom)  StdDraw.line(x, y, x+1, y);
                if(!maze[x][y].top) StdDraw.line(x, y+1, x+1, y+1);
                if(!maze[x][y].left)  StdDraw.line(x, y, x, y+1);
                if(!maze[x][y].right) StdDraw.line(x+1, y, x+1, y+1);
            }
        }
        StdDraw.show();
    }

}
