/*
994. Rotting Oranges
https://leetcode.com/problems/rotting-oranges/description/

A grid contains empty cells (0), fresh oranges (1), and rotten oranges (2).
Each minute, rot spreads to horizontally and vertically adjacent fresh oranges.
Return the minutes needed to rot every orange, or -1 if some remain unreachable.
*/

import java.util.ArrayDeque;
import java.util.Queue;

class Pair {
    int row;
    int col;

    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    int[][] directions = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        Queue<Pair> q = new ArrayDeque<>();

        // Add all rotten fruits initially to the queue.
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 2)
                    q.offer(new Pair(r, c));
            }
        }

        int minutes = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair pair = q.poll();
                int r = pair.row;
                int c = pair.col;
                for (int[] step : directions) {
                    int nextRow = r + step[0];
                    int nextCol = c + step[1];
                    if (nextRow >= 0 && nextRow < grid.length &&
                        nextCol >= 0 && nextCol < grid[0].length &&
                        grid[nextRow][nextCol] == 1) {

                        grid[nextRow][nextCol] = 2;
                        q.offer(new Pair(nextRow, nextCol));
                    }
                }
            }

            minutes++;
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1)
                    return -1;
            }
        }

        return minutes == 0 ? 0 : minutes - 1;
    }

    // Solution 2: track fresh oranges and stop when none remain.
    public int orangesRotting222(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        int fresh = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }

        int minutes = 0;

        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for (int[] dir : directions) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];

                    if (nr < 0 || nr >= rows ||
                        nc < 0 || nc >= cols ||
                        grid[nr][nc] != 1) {
                        continue;
                    }

                    grid[nr][nc] = 2;
                    fresh--;
                    queue.offer(new int[]{nr, nc});
                }
            }

            minutes++;
        }

        return fresh == 0 ? minutes : -1;
    }
}
