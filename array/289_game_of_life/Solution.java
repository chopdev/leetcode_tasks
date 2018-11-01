import java.util.ArrayList;

/**
 289. Game of Life
 https://leetcode.com/problems/game-of-life/description/

 According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 https://ru.wikipedia.org/wiki/%D0%98%D0%B3%D1%80%D0%B0_%C2%AB%D0%96%D0%B8%D0%B7%D0%BD%D1%8C%C2%BB

 Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

 Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 Any live cell with two or three live neighbors lives on to the next generation.
 Any live cell with more than three live neighbors dies, as if by over-population..
 Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

 Example:
 Input:
 [
 [0,1,0],
 [0,0,1],
 [1,1,1],
 [0,0,0]
 ]
 Output:
 [
 [0,0,0],
 [1,0,1],
 [0,1,1],
 [0,1,0]
 ]

 Follow up:

 1) Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 2) In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

 */
public class Solution {

    // Mine solution
    // O(M*N) - time, O(1) space
    // We go through each cell and check if it going to be alive on the next step, by checking neighbor cells
    // If cell was dead, and will become live, set it to -1
    // If cell is alive and will continue to be alive, set it to 2
    // On the second go, just switch -1 and 2 to 1, all other to 0.
    public void gameOfLife(int[][] board) {
        if(board == null) return;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(willAlive(board, i, j)){
                    boolean isAliveNow = board[i][j] > 0;
                    if(isAliveNow) board[i][j] = 2;
                    else board[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if(board[i][j] == -1 || board[i][j] == 2)
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
    }

    private boolean willAlive(int[][] board, int i, int j) {
        boolean isAliveNow = board[i][j] > 0;
        int aliveNeighbors = 0;

        if(board.length > i + 1 && board[i+1][j] > 0) aliveNeighbors ++;
        if(i - 1 >= 0 && board[i-1][j] > 0) aliveNeighbors++;
        if(j - 1 >= 0 && board[i][j-1] > 0) aliveNeighbors++;
        if(j + 1 < board[0].length && board[i][j+1] > 0) aliveNeighbors++;

        if(board.length > i + 1 && j - 1 >= 0 && board[i+1][j-1] > 0) aliveNeighbors++;
        if(board.length > i + 1 && j + 1 < board[0].length && board[i+1][j+1] > 0) aliveNeighbors++;
        if(i - 1 >= 0 && j - 1 >= 0 && board[i-1][j-1] > 0) aliveNeighbors++;
        if(i - 1 >= 0 && j + 1 < board[0].length && board[i-1][j+1] > 0) aliveNeighbors++;

        if(isAliveNow && aliveNeighbors == 2 || aliveNeighbors == 3) return true;
        if (!isAliveNow && aliveNeighbors == 3) return true;
        return false;
    }



    // Not mine clever solution
    // https://leetcode.com/problems/game-of-life/discuss/73223/Easiest-JAVA-solution-with-explanation
    // They do not check each neighbour cell manually, but use loop with Math.max, min
    //+ switch to 3 and 2 that makes checks pretty
    public void gameOfLife2222(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when will the 2nd bit become 1.
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1; // will give one if number is 3 (11) or 1 (01)
            }
        }
        lives -= board[i][j] & 1; // remove cell it self, if it was 1
        return lives;
    }
}
