import java.util.Arrays;

/**
 931. Minimum Falling Path Sum
 https://leetcode.com/problems/minimum-falling-path-sum/

 Given a square array of integers A, we want the minimum sum of a falling path through A.
 A falling path starts at any element in the first row, and chooses one element from each row.
 The next row's choice must be in a column that is different from the previous row's column by at most one.


 Example 1:

 Input: [[1,2,3],[4,5,6],[7,8,9]]
 Output: 12
 Explanation:
 The possible falling paths are:
 [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 The falling path with the smallest sum is [1,4,7], so the answer is 12.


 Note:

 1 <= A.length == A[0].length <= 100
 -100 <= A[i][j] <= 100
 * */
public class Solution {

    // My memoization solution
    // O(N*M) time and space
    // The idea is to go down for each column and remember best sum for each cell
    // if the row is the last - best sum for cell is itself
    // if it is pre last row, we consider to take one of 3 previous + current
    // memo[row][col] = arr[row][col] + Min(memo[row + 1][col - 1], memo[row+1][col], memo[row+1][col+1])
    public int minFallingPathSum(int[][] A) {
        if(A == null || A.length == 0) return 0;
        int[][] memo = new int[A.length][];
        for(int i = 0; i < A.length; i++)
            memo[i] = new int[A[i].length];

        int minSum = Integer.MAX_VALUE;
        for (int col = 0; col < A[0].length; col++) {
            minSum = Math.min(minSum, minSumPath(A, 0, col, memo));
        }
        return minSum;
    }

    private int minSumPath(int[][] arr, int row, int col, int[][] memo) {
        if(row < 0 || row >= arr.length || col < 0 || col >= arr[0].length)
            return Integer.MAX_VALUE;

        if(row == arr.length - 1)
            memo[row][col] = arr[row][col];

        if(memo[row][col] != 0)
            return memo[row][col];

        int left = minSumPath(arr, row + 1, col - 1, memo);
        int bottom = minSumPath(arr, row + 1, col, memo);
        int right = minSumPath(arr, row + 1, col + 1, memo);
        memo[row][col] = arr[row][col] + Math.min(left, Math.min(bottom, right));
        return memo[row][col];
    }


    // My dp solution
    // O(N*M) time and space
    // idea is the same as for memoization, but we begin to grow sum from the beginning
    public int minFallingPathSum2222(int[][] A) {
        if(A == null || A.length == 0) return 0;
        int[][] dp = new int[A.length][];
        dp[0] = A[0];
        for(int i = 1; i < A.length; i++)
            dp[i] = new int[A[i].length];

        for (int row = 1; row < A.length; row++) {
            for (int col = 0; col < A[0].length; col++) {
                int left = dp[row - 1][Math.max(0, col - 1)];
                int top = dp[row - 1][col];
                int right = dp[row - 1][Math.min(A.length - 1, col + 1)];
                dp[row][col] = A[row][col] + Math.min(left, Math.min(top, right));
            }
        }

        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < A[0].length; i++) {
            minSum = Math.min(minSum, dp[A.length - 1][i]);
        }
        return minSum;
    }


    // Not mine, neat solution
    // https://leetcode.com/problems/minimum-falling-path-sum/discuss/186666/C%2B%2BJava-4-lines-DP
    // Idea is the same, but they modify initial array + use of Java8
    public int minFallingPathSum3333(int[][] A) {
        for (int i = 1; i < A.length; ++i)
            for (int j = 0; j < A.length; ++j)
                A[i][j] += Math.min(A[i - 1][j], Math.min(A[i - 1][Math.max(0, j - 1)], A[i - 1][Math.min(A.length - 1, j + 1)]));
        return Arrays.stream(A[A.length - 1]).min().getAsInt();
    }
}
