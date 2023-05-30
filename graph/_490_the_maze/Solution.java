import java.util.LinkedList;
import java.util.Queue;

/**
 * 490. The Maze
 * https://leetcode.com/problems/the-maze/?envType=study-plan-v2&id=premium-algo-100
 *
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through
 * the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol],
 * return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 * Example 1:
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 *
 * Example 2:
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
 *
 *
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * Output: false
 * */
public class Solution {

    private class Position {
        private final int[] start;
        private final int horizontalStep;
        private final int verticalStep;

        public Position(int[] start, int horizontalStep, int verticalStep) {
            this.start = start;
            this.horizontalStep = horizontalStep;
            this.verticalStep = verticalStep;
        }
    }

    private static final int VISITED = 3;
    private static final int WALL = 1;


    /**
     * My solution. NOTE: not passing all test cases
     * */
    public boolean hasPath2222(int[][] maze, int[] start, int[] destination) {
        Queue<Position> queue = new LinkedList<>();
        addNextMoves(queue, start);
        maze[start[0]][start[1]] = VISITED;

        int horLen = maze[0].length;
        int vertLen = maze.length;

        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int i = position.start[0];
            int j = position.start[1];

            if (i == destination[0] && j == destination[1]) {
                return true;
            }

            int nextI = i + position.horizontalStep;
            int nextJ = j + position.verticalStep;
            if (nextI == vertLen || nextI == -1 || nextJ == horLen || nextJ == -1 || maze[nextI][nextJ] == WALL
                    || maze[nextI][nextJ] == VISITED) {
                continue; // no way forward, skip this direction move
            }

            while (nextI != vertLen && nextI != -1 && nextJ != horLen && nextJ != -1 && maze[nextI][nextJ] != WALL) {
                maze[nextI][nextJ] = VISITED;
                nextI = nextI + position.horizontalStep;
                nextJ = nextJ + position.verticalStep;
            }

            addNextMoves(queue, new int[] {nextI - position.horizontalStep, nextJ - position.verticalStep});
        }

        return false;
    }

    private void addNextMoves(Queue<Position> queue, int[] start) {
        queue.add(new Position(start, 1, 0));
        queue.add(new Position(start, -1, 0));
        queue.add(new Position(start, 0, 1));
        queue.add(new Position(start, 0, -1));
    }



    /***
     * My implementation, not my idea
     *
     * BFS approach
     * https://leetcode.com/problems/the-maze/editorial/
     *
     * In this case, we try to explore the search space on a level by level basis. i.e. We try to move in all the directions at every step.
     * When all the directions have been explored and we still don't reach the destination, then only we proceed to the new set of traversals from the new positions obtained.
     *
     * In order to implement this, we make use of a queuequeuequeue. We start with the ball at the startstartstart position.
     * For every current position, we add all the new positions possible by traversing in all the four directions(till reaching the wall or boundary) into the queuequeuequeue to act
     * as the new start positions and mark these positions as True in the visitedvisitedvisited array. When all the directions have been covered up, we remove a position value, sss,
     * from the front of the queuequeuequeue and again continue the same process with sss acting as the new startstartstart position.
     * Further, in order to choose the direction of travel, we make use of a dirdirdir array, which contains 4 entries. Each entry represents a
     * one-dimensional direction of travel. To travel in a particular direction, we keep on adding the particular entry of the dirsdirsdirs array till we hit a wall or a boundary.
     * For a particular start position, we do this process of dirdirdir addition for all all the four directions possible.
     *
     * */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        maze[start[0]][start[1]] = VISITED;

        int horLen = maze[0].length;
        int vertLen = maze.length;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int i = position[0];
            int j = position[1];

            if (i == destination[0] && j == destination[1]) {
                return true;
            }

            for (int[] dir : directions) {
                int nextI = i + dir[0];
                int nextJ = j + dir[1];
                while (nextI != vertLen && nextI != -1 && nextJ != horLen && nextJ != -1 && maze[nextI][nextJ] != WALL) {
                    nextI += dir[0];
                    nextJ += dir[1];
                }
                int stopI = nextI - dir[0];
                int stopJ = nextJ - dir[1];
                if (maze[stopI][stopJ] != VISITED) {
                    maze[stopI][stopJ] = VISITED;
                    queue.add(new int[] {stopI, stopJ});
                }
            }
        }

        return false;
    }




/**
 * DFS implementation, not my
 * https://leetcode.com/problems/the-maze/editorial/
 * */
    private int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public boolean hasPath33333(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, visited, start, destination);
    }

    private boolean dfs(int[][] maze, boolean[][] visited, int[] c, int[] des) {
        if (visited[c[0]][c[1]]) return false;
        if (c[0] == des[0] && c[1] == des[1]) return true;

        visited[c[0]][c[1]] = true;
        boolean result = false;
        for (int[] d : dir) {
            int x = c[0] + d[0];
            int y = c[1] + d[1];
            while ( 0 <= x && x < maze.length && 0 <= y && y < maze[0].length && maze[x][y] == 0) {
                x += d[0];
                y += d[1];
            }
            result = result || dfs(maze, visited, new int[]{ x - d[0], y - d[1]}, des);
        }
        return result;
    }

}
