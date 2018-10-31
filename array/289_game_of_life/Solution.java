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

    public void gameOfLife(int[][] board) {
        if(board == null) return;

        ArrayList<Point> alive = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(isAlive(board, i, j)) alive.add(new Point(i, j));
            }
        }

        
    }

    private boolean isAlive(int[][] board, int i, int j) {
        boolean isAliveNow = board[i][j] == 1;
        int aliveNeighbors = 0;

        if(board.length > i + 1 && board[i+1][j] == 1) aliveNeighbors ++;
        if(i - 1 >= 0 && board[i-1][j] == 1) aliveNeighbors++;
        if(j - 1 >= 0 && board[i][j-1] == 1) aliveNeighbors++;
        if(j + 1 < board[0].length && board[i][j+1] == 1) aliveNeighbors++;

        if(board.length > i + 1 && j - 1 >= 0 && board[i+1][j-1] == 1) aliveNeighbors++;
        if(board.length > i + 1 && j + 1 < board[0].length && board[i+1][j+1] == 1) aliveNeighbors++;
        if(i - 1 >= 0 && j - 1 >= 0 && board[i-1][j-1] == 1) aliveNeighbors++;
        if(i - 1 >= 0 && j + 1 < board[0].length && board[i-1][j+1] == 1) aliveNeighbors++;

        if(isAliveNow && aliveNeighbors == 2 || aliveNeighbors == 3) return true;
        if (!isAliveNow && aliveNeighbors == 3) return true;
        return false;
    }
}
