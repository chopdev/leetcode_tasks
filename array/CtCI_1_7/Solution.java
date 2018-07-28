public class Solution {
    public int[][] rotateClockwise(int[][] arr) {
        if(arr == null) return null;

        int rowCount = arr.length;
        int colCount = arr[0].length;
        int[][] res = new int[colCount][rowCount];

        for (int i = rowCount - 1; i >= 0 ; i--)
            for (int j = 0; j < colCount; j++)
                res[j][rowCount - 1 - i] = arr[i][j];

        return res;
    }
}
