/*
348. Design Tic-Tac-Toe
https://leetcode.com/problems/design-tic-tac-toe/

Design an n-by-n Tic-Tac-Toe game for two players. After each valid move,
return the winning player's number if a row, column, or diagonal is complete;
otherwise return 0.
*/

import java.util.HashMap;
import java.util.Map;

class TicTacToe {
    int n;
    Map<Integer, int[]> rowToCount;
    Map<Integer, int[]> colToCount;
    int[] diagonal;
    int[] antidiagonal;

    public TicTacToe(int n) {
        this.n = n;
        rowToCount = new HashMap<>();
        colToCount = new HashMap<>();
        diagonal = new int[3];
        antidiagonal = new int[3];

        for (int i = 0; i < n; i++) {
            rowToCount.put(i, new int[3]);
            colToCount.put(i, new int[3]);
        }
    }

    public int move(int row, int col, int player) {
        if (row == col) {
            diagonal[player]++;
        }

        if (row == n - 1 - col) {
            antidiagonal[player]++;
        }

        rowToCount.get(row)[player]++;
        colToCount.get(col)[player]++;

        if (rowToCount.get(row)[player] == n ||
            colToCount.get(col)[player] == n ||
            diagonal[player] == n ||
            antidiagonal[player] == n) return player;

        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
