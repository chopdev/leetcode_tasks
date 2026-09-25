/*
51. N-Queens
https://leetcode.com/problems/n-queens/description/

Return all distinct placements of n queens on an n-by-n board such that no two
queens share a row, column, or diagonal. Represent queens with 'Q' and empty
spaces with '.'.
*/

import java.util.ArrayList;
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
