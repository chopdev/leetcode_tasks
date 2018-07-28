//*
// Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
//bytes, write a method to rotate the image by 90 degrees. (an you do this in place?
// /
public class Solution {
    // Mine solution, O(N^2) - space and time
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
