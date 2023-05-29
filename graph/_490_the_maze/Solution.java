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
     * My solution, not passing all test cases
     * */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
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

}
