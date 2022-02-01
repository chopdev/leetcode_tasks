import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * 1738. Find Kth Largest XOR Coordinate Value
 https://leetcode.com/problems/find-kth-largest-xor-coordinate-value/

 You are given a 2D matrix of size m x n, consisting of non-negative integers. You are also given an integer k.
 The value of coordinate (a, b) of the matrix is the XOR of all matrix[i][j] where 0 <= i <= a < m and 0 <= j <= b < n (0-indexed).
 Find the kth largest value (1-indexed) of all the coordinates of matrix.



 Example 1:
 Input: matrix = [[5,2],[1,6]], k = 1
 Output: 7
 Explanation: The value of coordinate (0,1) is 5 XOR 2 = 7, which is the largest value.

 Example 2:
 Input: matrix = [[5,2],[1,6]], k = 2
 Output: 5
 Explanation: The value of coordinate (0,0) is 5 = 5, which is the 2nd largest value.

 Example 3:
 Input: matrix = [[5,2],[1,6]], k = 3
 Output: 4
 Explanation: The value of coordinate (1,0) is 5 XOR 1 = 4, which is the 3rd largest value.
 * */
public class Solution {
    private void printMatrix(int[][] matrix) {
        System.out.println(Arrays.deepToString(matrix));
    }

    // matrix     // values
    // 5  2      //   5  7
    // 1  6      //   4  0


    // My initial not working solution
    // Had issues with calculation of values matrix. Basically I used dp[i][j] = dp[i-1][j] ^ dp[i][j-1] ^ matrix[i][j] which is not correct
    // Pretty bad description of what is value[i][j] !!
    public int kthLargestValue22222(int[][] matrix, int k) {
        int[][] values = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) values[i] = new int[matrix[i].length];
        values[0][0] = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (i == 0 && j == 0) continue;
                int left = j > 0 ? matrix[i][j - 1] ^  matrix[i][j]: matrix[i][j];
                int top = i > 0 ? matrix[i - 1][j] ^ matrix[i][j] : matrix[i][j];
                values[i][j] = left ^ top;
            }
        }

        printMatrix(values);

        PriorityQueue<Integer> heap = new PriorityQueue<>(); // collect k max in mean heap
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                int val = values[i][j];
                if (heap.size() < k) {
                    heap.offer(val);
                    continue;
                }
                if (heap.peek() < val) {
                    heap.poll();
                    heap.offer(val);
                }
            }
        }

        return heap.peek();
    }



    // Idea taken from here https://leetcode.com/problems/find-kth-largest-xor-coordinate-value/discuss/1032010/Detailed-Explanation-or-C%2B%2B-Solution-or-Easy-Implementation
    // Another clean solution https://leetcode.com/problems/find-kth-largest-xor-coordinate-value/discuss/1032143/Java-Detailed-Explanation-DP-with-Graph-Demo
    public int kthLargestValue(int[][] matrix, int k) {
        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                matrix[i][j] = matrix[i][j - 1] ^ matrix[i][j];
            }
        }
        for (int j = 0; j < m; j ++) {
            for (int i = 1; i < n; i++) {
                matrix[i][j] = matrix[i-1][j] ^ matrix[i][j];
            }
        }

        printMatrix(matrix);

        PriorityQueue<Integer> heap = new PriorityQueue<>(); // collect k max in mean heap
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = matrix[i][j];
                if (heap.size() < k) {
                    heap.offer(val);
                    continue;
                }
                if (heap.peek() < val) {
                    heap.poll();
                    heap.offer(val);
                }
            }
        }

        return heap.peek();
    }


}
