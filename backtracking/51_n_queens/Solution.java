/*
51. N-Queens
https://leetcode.com/problems/n-queens/description/

Return all distinct placements of n queens on an n-by-n board such that no two
queens share a row, column, or diagonal. Represent queens with 'Q' and empty
spaces with '.'.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        backtrack(n, 0, new ArrayList<>(), res);
        return res;
    }

    /**
     * Try each column in the current row, then move to the next row.
     * Reject positions that share a row, column, or diagonal with a taken spot.
     *
     * Time: O(N^2 * N!) is a loose upper bound, including scanning all columns,
     * checking up to N placed queens per candidate, and building result boards.
     * Auxiliary space: O(N) for taken and the recursion stack, excluding output.
     * Output space: O(K * N^2), where K is the number of valid boards.
     *
     * taken contains the coordinates of placed queens.
     */
    void backtrack(int n, int row, List<int[]> taken, List<List<String>> res) {
        if (taken.size() == n) {
            StringBuilder line = new StringBuilder(".".repeat(n));
            List<String> temp = new ArrayList<>();
            for (int[] r : taken) {
                line.setCharAt(r[1], 'Q');
                temp.add(line.toString());
                line.setCharAt(r[1], '.');
            }
            res.add(temp);
            return;
        }

        // Consider all columns on the current row.
        for (int j = 0; j < n; j++) {
            if (intersect(row, j, taken)) continue;

            taken.add(new int[] {row, j});
            backtrack(n, row + 1, taken, res);
            taken.remove(taken.size() - 1);
        }
    }

    // Solution 2: track occupied columns and diagonals for O(1) conflict checks.
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();

        boolean[] cols = new boolean[n];
        boolean[] diag = new boolean[2 * n - 1];      // row - col + n - 1
        boolean[] antiDiag = new boolean[2 * n - 1];  // row + col

        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtrack2(0, n, board, cols, diag, antiDiag, res);

        return res;
    }

    private void backtrack2(
        int row,
        int n,
        char[][] board,
        boolean[] cols,
        boolean[] diag,
        boolean[] antiDiag,
        List<List<String>> res
    ) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (char[] r : board) {
                solution.add(new String(r));
            }
            res.add(solution);
            return;
        }

        // Try placing queen in every column of current row
        for (int col = 0; col < n; col++) {
            int d = row - col + n - 1;
            int ad = row + col;

            if (cols[col] || diag[d] || antiDiag[ad]) {
                continue;
            }

            // choose
            board[row][col] = 'Q';
            cols[col] = true;
            diag[d] = true;
            antiDiag[ad] = true;

            // explore next row
            backtrack2(
                row + 1,
                n,
                board,
                cols,
                diag,
                antiDiag,
                res
            );

            // undo
            board[row][col] = '.';
            cols[col] = false;
            diag[d] = false;
            antiDiag[ad] = false;
        }
    }

    boolean intersect(int row, int col, List<int[]> taken) {
        for (int[] coordinates : taken) {
            int r = coordinates[0];
            int c = coordinates[1];

            if (row == r || col == c) return true;

            int diffRow = Math.abs(row - r);
            int diffCol = Math.abs(col - c);

            if (diffRow == diffCol) return true;
        }

        return false;
    }
}
