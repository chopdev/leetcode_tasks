/**
 73. Set Matrix Zeroes
 https://leetcode.com/problems/set-matrix-zeroes/
 Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */

public class Solution {

    // Mine solutiuon, Time O(N^2), Space O(1)
    // The idea is simple: we remember which columns and rows need to be set to zero in the first column and the first row
    // Shorter solutins are here:
    // https://leetcode.com/problems/set-matrix-zeroes/discuss/26008/My-AC-java-O(1)-solution-(easy-to-read)
    // https://leetcode.com/problems/set-matrix-zeroes/discuss/26014/Any-shorter-O(1)-space-solution
    public void setZeroes(int[][] matrix) {
        if(matrix == null) return;

        boolean zeroToFirstRow = false, zeroToFirstCol = false;
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        // check if first row should be set to 0
        for (int i = 0; i < colCount; i++)
            if(matrix[0][i] == 0) {
                zeroToFirstRow = true;
                break;
            }

        // check if first column should be set to 0
        for (int i = 0; i < rowCount; i++)
            if(matrix[i][0] == 0) {
                zeroToFirstCol = true;
                break;
            }

        // set zero to first col and row if the element is 0
        for (int i = 1; i < rowCount; i++)
            for (int j = 1; j < colCount; j++) {
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    continue;
                }
            }

        // if we meet 0 in first row setZeroToColumn
        for (int i = 1; i < colCount; i++)
            if(matrix[0][i] == 0)
                setZeroToColumn(matrix, i);

        // if we meet 0 in column row setZeroToRow
        for (int i = 1; i < rowCount; i++)
            if(matrix[i][0] == 0)
                setZeroToRow(matrix, i);

        if(zeroToFirstCol)
            setZeroToColumn(matrix, 0);

        if(zeroToFirstRow)
            setZeroToRow(matrix, 0);
    }

    private void setZeroToColumn(int[][] matrix, int i){
        for (int j = 0; j < matrix.length; j++)
            matrix[j][i] = 0;
    }

    private void setZeroToRow(int[][] matrix, int i){
        for (int j = 0; j < matrix[0].length; j++)
            matrix[i][j] = 0;
    }




    // Not mine. Shorter, but idea is the same
    public void setZeroes2222(int[][] matrix) {
        boolean fr = false, fc = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) fr = true;
                    if (j == 0) fc = true;
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (fr) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (fc) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
