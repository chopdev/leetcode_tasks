public class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix == null) return;

        boolean zeroToFirstRow = false, zeroToFirstCol = false;
        int rowCount = matrix.length;
        int colCount = matrix[0].length;

        for (int i = 0; i < colCount; i++)
            if(matrix[0][i] == 0) {
                zeroToFirstRow = true;
                break;
            }

        for (int i = 0; i < rowCount; i++)
            if(matrix[i][0] == 0) {
                zeroToFirstCol = true;
                break;
            }

        for (int i = 1; i < rowCount; i++)
            for (int j = 1; j < colCount; j++) {
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    continue;
                }
            }

        for (int i = 1; i < colCount; i++)
            if(matrix[0][i] == 0)
                setZeroToColumn(matrix, i);

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
}
