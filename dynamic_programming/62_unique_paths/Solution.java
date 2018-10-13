public class Solution {
    // Mine recursive solution
    // space O(M*N), time O(M*N)
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        return uniquePathsRecursive(m, n, 0, 0, dp);
    }

    public int uniquePathsRecursive(int m, int n, int i, int j, int[][] dp ) {
        if(i >= m || j >= n) return 0;
        if(i == m - 1 && j == n - 1) return 1;
        if(dp[i][j] != 0) return dp[i][j];
        return uniquePathsRecursive(m, n, i+1, j, dp) + uniquePathsRecursive(m, n, i, j+1, dp);
    }

    // Mine iterative solution
    // from start to end
    // space O(M*N), time O(M*N), but uses less memory because of recursion
    public int uniquePaths2222(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1; // initialize the most top and left row and column
        for (int i = 0; i < n; i++) dp[0][i] = 1; // we can get to these cells only from 1 side

        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                dp[i][j] = dp[i][j-1] + dp[i-1][j];

        return dp[m-1][n-1];
    }

    // Not mine
    // https://leetcode.com/problems/unique-paths/discuss/22981/My-AC-solution-using-formula
    public int uniquePaths3333(int m, int n) {
        int N = n + m - 2;// how much steps we need to do
        int k = m - 1; // number of steps that need to go down
        double res = 1;
        // here we calculate the total possible path number
        // Combination(N, k) = n! / (k!(n - k)!)
        // reduce the numerator and denominator and get
        // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
        for (int i = 1; i <= k; i++)
            res = res * (N - k + i) / i;
        return (int)res;
    }
}
