import java.lang.reflect.Array;
import java.util.Arrays;

/**
 https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 240. Search a 2D Matrix II

 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.

 Example:

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.

* */
public class Solution {

    // My O(N*logM)
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int row = 0;
        for (row = 0; row < matrix.length; row++) {
            if (matrix[row][0] == target) return true;
            if (matrix[row][0] > target) break;
        }

        while (row > 0) {
            row--;
            int col = Arrays.binarySearch(matrix[row], target);
            if (col >= 0) return true;
        }

        return false;
    }

    // My not working solution. try to implement binary search
    // it will not work here, because we have to decrease column or row
    // when matrix[mid.row][mid.col] != target (in order to avoid infinite loop),
    // but if we would decrease/increase - we will miss part of row/column
    public boolean searchMatrix222(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        Point lo = new Point(0, 0);
        Point hi = new Point(matrix.length - 1, matrix[0].length - 1);
        Point mid = null;

        while (lo.col <= hi.col && lo.row <= hi.row) {
            mid = new Point((lo.row + hi.row) / 2,(lo.col + hi.col) / 2);
            if(matrix[mid.row][mid.col] == target) return true;

            if(matrix[mid.row][mid.col] > target) lo = mid;
            else hi = mid;
        }

        return false;
    }

    // Not mine O(m+n) time
    public boolean searchMatrix3333(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int col = matrix[0].length - 1;
        int row = 0;

        while (col >= 0 && row < matrix.length) {
            if(matrix[row][col] == target) return true;
            else if(matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }
}
