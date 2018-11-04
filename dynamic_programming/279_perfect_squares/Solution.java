/**
 https://leetcode.com/problems/perfect-squares/description/
 279. Perfect Squares

 Given a positive integer n, find the least number of perfect square numbers
 (for example, 1, 4, 9, 16, ...) which sum to n.

 Example 1:
 Input: n = 12
 Output: 3
 Explanation: 12 = 4 + 4 + 4.

 Example 2:
 Input: n = 13
 Output: 2
 Explanation: 13 = 4 + 9.
 * */
public class Solution {
    // Mine solution
    // Beats 97% of solutions
    public int numSquares(int n) {
        return calculateCount(n, new int[n + 1]);
    }

    // the idea is that we trying to find the shortest solution by
    // removing perfect square from the number
    // and recursively calculating count of numbers that sum up to the rest of removing
    private int calculateCount(int numb, int[] dp) {
        if(numb == 0) return 0;
        if(dp[numb] != 0) return dp[numb];

        int min = Integer.MAX_VALUE;
        int maxSqrt = (int)Math.sqrt(numb);

        // we could go till i > 0, but this just speed ups the solution,
        // cause we will not get the shortest result using numbers less than average
        for (int i = maxSqrt; i > maxSqrt / 2 ; i--) {
            int res = 1 + calculateCount(numb - ((int) Math.pow(i, 2) ), dp);
            min = res < min ? res : min;
        }

        dp[numb] = min;
        return min;
    }


    // Not mine
    // https://leetcode.com/problems/perfect-squares/discuss/71632/Beautiful-8-Lines-Java-Solution
    // https://leetcode.com/problems/perfect-squares/discuss/71488/Summary-of-4-different-solutions-(BFS-DP-static-DP-and-mathematics)
    // DP bottom up solution
    // The idea is that we are calculating count of perfect square numbers that sum up for each number till n
    // count = Count(N-K^2) we are comparing what is shorter N of ones, or count of previous diff + 1
    public int numSquares2222(int n) {
        int[] record = new int[n+1];
        for(int i=0;i<=n;i++){
            record[i] = i;
            for(int j=1;j*j<=i;j++){
                record[i] = Math.min(record[i-j*j]+1,record[i]);
            }
        }
        return record[n];
    }
}
