/**
 https://leetcode.com/problems/count-square-submatrices-with-all-ones/
 1277. Count Square Submatrices with All Ones

 Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.

 Example 1:

 Input: matrix =
 [
 [0,1,1,1],
 [1,1,1,1],
 [0,1,1,1]
 ]
 Output: 15
 Explanation:
 There are 10 squares of side 1.
 There are 4 squares of side 2.
 There is  1 square of side 3.
 Total number of squares = 10 + 4 + 1 = 15.


 Example 2:

 Input: matrix =
 [
 [1,0,1],
 [1,1,0],
 [1,1,0]
 ]
 Output: 7
 Explanation:
 There are 6 squares of side 1.
 There is 1 square of side 2.
 Total number of squares = 6 + 1 = 7.
 * */
public class Solution {

    // My good memoization solution, O(N*M) space and time
    // Idea: we consider each position as left top corner of square
    // position has at least one square if it is 1, so we consider only such elements
    // if all adjacent elements also > 0, than we have 2 squares from this position
    // so the logic is that count of squares equal to: 1 + min count of squares from each adjacent position
    public int countSquares(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int count = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                count += countRecursive(matrix, i, j, memo);
            }
        }
        return count;
    }

    private int countRecursive(int[][] matrix, int row, int col, int[][] memo) {
        if(row >= matrix.length || col >= matrix[0].length || matrix[row][col] == 0)
            return 0;

        if(memo[row][col] > 0) return memo[row][col];

        int right = countRecursive(matrix, row + 1, col, memo);
        int botom = countRecursive(matrix, row, col + 1, memo);
        int opposite = countRecursive(matrix, row + 1, col + 1, memo);

        return memo[row][col] = 1 + Math.min(Math.min(right, botom), opposite);
    }



    // Not mine DP solution O(N*M) time O(1) space
    // https://leetcode.com/problems/count-square-submatrices-with-all-ones/discuss/441306/Python-DP-solution
    // idea is similar, but dp[i][j] means the biggest square with A[i][j] as bottom-right corner.
    // If A[i][j] == 0, no square.
    //If A[i][j] == 1,
    //we compare the size of square dp[i-1][j-1], dp[i-1][j] and dp[i][j-1].
    //min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1 is the maximum size of square that we can find.
    public int countSquares2222(int[][] A) {
        int res = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (A[i][j] > 0 && i > 0 && j > 0) {
                    A[i][j] = Math.min(A[i - 1][j - 1], Math.min(A[i - 1][j], A[i][j - 1])) + 1;
                }
                res += A[i][j];
            }
        }
        return res;
    }
}
