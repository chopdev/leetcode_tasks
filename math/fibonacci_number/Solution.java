/**
 Interview question:

 What a fibonacci function which return Nth position number both in recursive and loop, also give
 the explanation on both implementation on their time efficiency.

 */
public class Solution {

    // My solution
    // O(N) time, O(1) space
    public int getFibonacci(int N) {
        if(N <= 0) return 0;

        int a = 0, b = 1, c = 1;
        for(int i = 2; i <= N; i++) {
            c = a + b;
            a = b;
            b = c;
        }

        return c;
    }


    // My solution
    // O(N) time, O(N) space
    public int getFibonacci2222(int N) {
        if(N <= 0) return 0;

        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[N];
    }

    // My solution
    // Time O(2^N), O(N) space complexity because of the length of recursion
    public int getFibonacciRecurs(int N) {
        if(N <= 0) return 0;
        if(N == 1) return 1;

        return getFibonacciRecurs(N-1) + getFibonacciRecurs(N-2);
    }
}
