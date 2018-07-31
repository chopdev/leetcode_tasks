//*
// 1)From book:
// Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
//bytes, write a method to rotate the image by 90 degrees. (can you do this in place?)
//
// 2) From leetcode:
// You are given an n x n 2D matrix representing an image.
//  Rotate the image by 90 degrees (clockwise).
// Note:You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
// DO NOT allocate another 2D matrix and do the rotation.
// /
public class Solution {

    // Mine solution, took more than 1 hour, figured it out after looking on 100 hint in cracking coding...
    // The idea is following: look on the matrix layer by layer. Firstly rotate outer sides of it,
    // than inner (1 layer less, 2 layers less...)
    public void rotate(int[][] a) {
        if(a == null) return;
        int n = a.length - 1; // last index of matrix

        for (int lay= 0; lay <= n/2 ; lay++) // run from 0 layer till n/2 layer,  (3/2=1)
            for (int i = lay; i < n-lay; i++) { // run in borders of layer
                swap(a, lay, i, i, n - lay); // swap top element with right most
                swap(a, lay, i, n-lay, n-i); // swap top element with bottom element
                swap(a, lay, i, n-i, lay); // swap top element with left one
            }

    }

    private void swap(int[][] a, int x, int y, int i, int j) {
        int temp = a[x][y];
        a[x][y] = a[i][j];
        a[i][j] = temp;
    }


    // Mine solution, O(N^2) - space and time
    // With creation of new array
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
