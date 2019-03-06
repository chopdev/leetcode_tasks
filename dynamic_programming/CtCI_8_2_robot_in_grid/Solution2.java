import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 ROBOT CAN MOVE 4 WAYS

* Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
The robot can only move in 4 directions, right and down,top, left but certain cells are obstacles such that
the robot cannot step on them. Design an algorithm to find the length of shortest path for the robot from the top left to
the destination. Return -1 if path can't be found

9- destination
1 - flat cell, robot can move
0 - obstacle, robot can't move

1 1 0 1
1 0 1 0
1 0 9 0
1 1 1 1

answer 7

* */
public class Solution2 {

    // Mine solution. Complexity O(N*M), N - number of rows, M - number of columns
    // Space complexity O(N*M) - memoization + O(N+M) - deepness of recursion O(M*N + N + M) - maybe mistake here
    public int shortestPath(int[][] arr) {
        int rowsCount = arr.length;
        int colCount = arr[0].length;
        int[][] mem = new int[rowsCount][colCount];
        int res = shortestPath(arr, rowsCount, colCount, 0, 0, mem);
        if(res == Integer.MAX_VALUE) return -1;
        return res;
    }

    public int shortestPath(int[][] arr,
                            int rowsCount,
                            int colCount,
                            int row,
                            int col,
                            int[][] mem)
    {
        // out of bounds
        if(row < 0 ||  row > rowsCount - 1 || col < 0 || col > colCount - 1 || arr[row][col] == 0)
            return Integer.MAX_VALUE;

        if(arr[row][col] == 9) return 1; // found destination point
        if(mem[row][col] > 0) return mem[row][col]; // we already have the length of path from this point

        if(mem[row][col] == -1) return Integer.MAX_VALUE; // if point was visited, return (to avoid cycles)
        mem[row][col] = -1; // mark as visited

        int bottom = shortestPath(arr, rowsCount, colCount, row + 1, col, mem);
        int right = shortestPath(arr, rowsCount, colCount, row, col + 1, mem);
        int left = shortestPath(arr, rowsCount, colCount, row, col - 1, mem);
        int top = shortestPath(arr, rowsCount, colCount, row - 1, col, mem);

        // detect shortest path from this point and momoize it
        int val = Integer.min(Integer.min(bottom, right), Integer.min(left, top));
        if(val != Integer.MAX_VALUE) val ++;
        mem[row][col] = val;
        return val;
    }


    private int[] rowSide = new int[] {0, 1, -1, 0};
    private int[] colSide = new int[] {1, 0, 0, -1};

    // My BFS solution
    // Use Pair (Tuple) class in order to preserve rowInd and colInd
    public int shortestPath2222(int[][] arr) {
        int rowsCount = arr.length;
        int colCount = arr[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair(0, 0));
        HashMap<Pair<Integer, Integer>, Integer> dist = new HashMap<>();
        dist.put(new Pair(0, 0), 1);

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> curr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = curr.getKey() + rowSide[i];
                int nextCol = curr.getValue() + colSide[i];
                Pair<Integer, Integer> next = new Pair(nextRow, nextCol);
                if(isValid(rowsCount, colCount, nextRow, nextCol) && !dist.containsKey(next)) {
                    if(arr[nextRow][nextCol] == 0) continue; // obstacle
                    if(arr[nextRow][nextCol] == 9) return dist.get(curr) + 1;
                    queue.offer(next);
                    dist.put(next, dist.get(curr) + 1);
                }
            }
        }

        return -1;
    }

    private boolean isValid(int rowCount, int colCount, int row, int col) {
        return row >= 0 && col >= 0 && row < rowCount && col < colCount;
    }
}
