/*
79. Word Search
https://leetcode.com/problems/word-search/

Determine whether a word can be formed by following horizontally or vertically
adjacent cells in a character grid, without reusing any cell in the same path.
*/

class Solution {
    /*
     Time: O(R * C * 4^L)
       - We can start the search from each of R*C cells.
       - At each character of the word, we can explore up to 4 directions.
       - L = word.length().
       - A tighter bound is O(R * C * 3^L), because after the first move
         we cannot immediately go back to the previously visited cell.

     Space: O(R * C + L)
       - O(R*C) for the visited/used matrix.
       - O(L) bounds the recursion stack; more precisely O(min(L, R*C)).
       - This simplifies to O(R*C), since a path cannot reuse cells,
         even when the word is longer than the number of cells.
    */

    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public boolean exist(char[][] board, String word) {
        boolean[][] used =
            new boolean[board.length][board[0].length];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (backtrack(board, row, col, used, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean backtrack(
        char[][] board,
        int row,
        int col,
        boolean[][] used,
        String word,
        int index
    ) {
        if (row < 0 || row >= board.length) return false;
        if (col < 0 || col >= board[0].length) return false;
        if (used[row][col]) return false;
        if (board[row][col] != word.charAt(index)) return false;

        if (index == word.length() - 1) return true;

        used[row][col] = true;

        for (int[] direction : directions) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (backtrack(
                board,
                nextRow,
                nextCol,
                used,
                word,
                index + 1
            )) {
                used[row][col] = false;
                return true;
            }
        }

        used[row][col] = false;
        return false;
    }
}
