import java.util.HashSet;

/**
 * 79. Word Search
 https://leetcode.com/problems/word-search/

 Given a 2D board and a word, find if the word exists in the grid.

 The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

 Example:

 board =
 [
 ['A','B','C','E'],
 ['S','F','C','S'],
 ['A','D','E','E']
 ]

 Given word = "ABCCED", return true.
 Given word = "SEE", return true.
 Given word = "ABCB", return false.
 */
public class Solution {

    // My solution
    // time O(B*W) - we go through each board val and do dfs with length of the word
    // space O(B+W) - visited count + recursion depth
    public boolean exist(char[][] board, String word) {
        if(board == null || board.length == 0 || board[0].length == 0) return false;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0, new HashSet<>()))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, int colInd, int rowInd, String word, int i, HashSet<Integer> visited) {
        if(i >= word.length()) return true;

        if(colInd < 0 || colInd >= board.length || rowInd < 0 || rowInd >= board[0].length) return false;
        if(word.charAt(i) != board[colInd][rowInd]) return false;

        int id = colInd* board[0].length + rowInd; // this trick is used to put elements of 2-dimensional array into 1-dimensional
        if(visited.contains(id)) return false;

        visited.add(id);
        if(dfs(board, colInd + 1, rowInd, word, i + 1, visited)) return true;
        if(dfs(board, colInd - 1, rowInd, word, i + 1, visited)) return true;
        if(dfs(board, colInd, rowInd + 1, word, i + 1, visited)) return true;
        if(dfs(board, colInd, rowInd - 1, word, i + 1, visited)) return true;

        visited.remove(id);  // important to remove it when we go back. Missed this
        return false;
    }


    // Not mine solution
    // https://leetcode.com/problems/word-search/discuss/27834/Simple-solution
    // idea is the same, but they modify existing board with '#' symbol, so we have to ask what symbols could we have in board
    public boolean exist2222(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(exist(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int x, int y, String word, int start) {
        if(start >= word.length()) return true;
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length) return false;
        if (board[x][y] == word.charAt(start++)) {
            char c = board[x][y];
            board[x][y] = '#';
            boolean res = exist(board, x + 1, y, word, start) || exist(board, x - 1, y, word, start) ||
                    exist(board, x, y + 1, word, start) || exist(board, x, y - 1, word, start);
            board[x][y] = c;
            return res;
        }
        return false;
    }
}
