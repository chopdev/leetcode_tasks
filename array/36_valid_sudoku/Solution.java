import java.util.HashSet;
import java.util.Set;

/**
 https://leetcode.com/problems/valid-sudoku/description/
 36. Valid Sudoku

 Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

 Each row must contain the digits 1-9 without repetition.
 Each column must contain the digits 1-9 without repetition.
 Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

 A partially filled sudoku which is valid.

 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

 Example 1:

 Input:
 [
 ["5","3",".",".","7",".",".",".","."],
 ["6",".",".","1","9","5",".",".","."],
 [".","9","8",".",".",".",".","6","."],
 ["8",".",".",".","6",".",".",".","3"],
 ["4",".",".","8",".","3",".",".","1"],
 ["7",".",".",".","2",".",".",".","6"],
 [".","6",".",".",".",".","2","8","."],
 [".",".",".","4","1","9",".",".","5"],
 [".",".",".",".","8",".",".","7","9"]
 ]
 Output: true
 Example 2:

 Input:
 [
 ["8","3",".",".","7",".",".",".","."],
 ["6",".",".","1","9","5",".",".","."],
 [".","9","8",".",".",".",".","6","."],
 ["8",".",".",".","6",".",".",".","3"],
 ["4",".",".","8",".","3",".",".","1"],
 ["7",".",".",".","2",".",".",".","6"],
 [".","6",".",".",".",".","2","8","."],
 [".",".",".","4","1","9",".",".","5"],
 [".",".",".",".","8",".",".","7","9"]
 ]
 Output: false
 Explanation: Same as Example 1, except with the 5 in the top left corner being
 modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 Note:

 A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 Only the filled cells need to be validated according to the mentioned rules.
 The given board contain only digits 1-9 and the character '.'.
 The given board size is always 9x9.

* */
public class Solution {

    // Mine solution
    // O(N^2)
    public boolean isValidSudoku(char[][] board) {
        if(board == null) return false;

        int len = board.length;
        int[] check = new int[len + 1];

        // check rows
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                char curr = board[i][j];
                if(curr == '.') continue;
                check[curr-'0']++;
                if(check[curr-'0'] > 1) return false;
            }
            check = new int[len + 1];
        }

        // check columns
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                char curr = board[j][i];
                if(curr == '.') continue;
                check[curr-'0']++;
                if(check[curr-'0'] > 1) return false;
            }
            check = new int[len + 1];
        }

        // check blocks
        int step = 3;
        for (int row = 0; row < len; row+=step) {
            for (int col = 0; col < len; col+=step) {
                for (int i = 0; i < step; i++) {
                    for (int j = 0; j < step; j++) {
                        char curr = board[i+row][j+col];
                        if(curr == '.') continue;
                        check[curr-'0']++;
                        if(check[curr-'0'] > 1) return false;
                    }
                }
                check = new int[len + 1];
            }
        }

        return true;
    }

    // Not mine
    // O(N^2)  - time the same
    //https://leetcode.com/problems/valid-sudoku/discuss/15634/Sharing-my-easy-understand-java-solution-using-set
    public boolean isValidSudoku3333(char[][] board) {
        for (int i=0; i<9; i++) {
            if (!isParticallyValid(board,i,0,i,8)) return false;
            if (!isParticallyValid(board,0,i,8,i)) return false;
        }
        for (int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if (!isParticallyValid(board,i*3,j*3,i*3+2,j*3+2)) return false;
            }
        }
        return true;
    }
    private boolean isParticallyValid(char[][] board, int x1, int y1,int x2,int y2){
        Set singleSet = new HashSet();
        for (int i= x1; i<=x2; i++){
            for (int j=y1;j<=y2; j++){
                if (board[i][j]!='.') if(!singleSet.add(board[i][j])) return false;
            }
        }
        return true;
    }

    // Not mine, using binary operations
    // O(N^2)  - time the same
    // https://leetcode.com/problems/valid-sudoku/discuss/15560/Yet-another-java-2ms-solution
    public boolean isValidSudoku2222(char[][] board) {
        int [] vset = new int [9];  // 9 columns, each cell is a column
        int [] hset = new int [9]; // 9 rows, each cell is a row
        int [] bckt = new int [9]; // 9 buckets, each cell is a bucket
        int idx = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    idx = 1 << (board[i][j] - '0') ;
                    if ((hset[i] & idx) > 0 ||
                            (vset[j] & idx) > 0 ||
                            (bckt[(i / 3) * 3 + j / 3] & idx) > 0) return false;   // i/3 * 3  - 3 big columns (3 cell each)
                    hset[i] |= idx;                                                 // j/3   - 3 rows
                    vset[j] |= idx;
                    bckt[(i / 3) * 3 + j / 3] |= idx;
                }
            }
        }
        return true;
    }
}
