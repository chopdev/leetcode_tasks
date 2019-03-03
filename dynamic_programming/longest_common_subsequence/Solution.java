import java.util.Arrays;

/*
*LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them.
 *  A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 *  For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
* So a string of length n has 2^n different possible subsequences.
*
*It is a classic computer science problem, the basis of diff
* (a file comparison program that outputs the differences between two files),
 * and has applications in bioinformatics.
*
* Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
*
*
* EXPLANATION: https://www.geeksforgeeks.org/longest-common-subsequence/
* */
public class Solution {

    // Without caching, it is overlapping problem
    // runtime O(2^max(n, m)))
    public int LCS2222(String s1, String s2)
    {
        if(s1 == null || s2 == null)
            return 0;

        int n = s1.length() - 1;
        int m = s2.length() - 1;

        return LCS2222(s1, s2, n, m);
    }

    public int LCS2222(String s1, String s2, int n, int m)
    {
        if(n < 0 || m < 0)
            return 0;

        if(s1.charAt(n) == s2.charAt(m))
            return  1 + LCS2222(s1, s2, n - 1, m - 1);

        return Math.max(LCS2222(s1, s2, n-1, m), LCS2222(s1, s2, n, m-1));
    }

    // Top down solution with memoization
    // O(N*M) complexity
    public int LCS3333(String s1, String s2)
    {
        if(s1 == null || s2 == null)
            return 0;

        int n = s1.length() - 1;
        int m = s2.length() - 1;
        int[][] memo = new int[s1.length()][s2.length()];
        return LCS3333(s1, s2, n, m, memo);
    }

    public int LCS3333(String s1, String s2, int n, int m, int[][] memo)
    {
        if(n < 0 || m < 0)
            return 0;

        if(s1.charAt(n) == s2.charAt(m))
            memo[n][m] = 1 + LCS3333(s1, s2, n - 1, m - 1, memo);
        else
            memo[n][m] = Math.max(LCS3333(s1, s2, n-1, m, memo), LCS3333(s1, s2, n, m-1, memo));

        return memo[n][m];
    }

    // With dynamic programming
    public int LCS(String s1, String s2)
    {
        if(s1 == null || s2 == null)
            return 0;

        int n = s1.length() + 1;
        int m = s2.length() + 1;
        int[][] cache = new int[n][m];

        for (int i = 1; i < n; i++)
            for (int j = 1; j < m; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    cache[i][j] = 1 + cache[i-1][j-1];
                else
                    cache[i][j] = Math.max(cache[i-1][j], cache[i][j-1]);
            }

        return cache[n-1][m-1];
    }
}
