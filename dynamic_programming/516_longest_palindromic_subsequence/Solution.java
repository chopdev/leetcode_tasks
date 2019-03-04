/**
 516. Longest Palindromic Subsequence
 https://leetcode.com/problems/longest-palindromic-subsequence/

 Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length
 of s is 1000.

 Example 1:

 Input:
 "bbbab"
 Output:
 4
 One possible longest palindromic subsequence is "bbbb".

 Example 2:

 Input:
 "cbbd"
 Output:
 2

 One possible longest palindromic subsequence is "bb".
 **/
public class Solution {
    // Not mine solution
    // https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
    public int longestPalindromeSubseq(String s) {
        int[][] memo = new int[s.length()][s.length()];
        return getLongestPalindrome(s, 0, s.length() - 1, memo);
    }

    private int getLongestPalindrome(String str, int i, int j, int[][] memo) {
        if(i > j) return 0;
        if(i == j) return 1;
        if(memo[i][j] != 0) return memo[i][j];

        if(str.charAt(i) == str.charAt(j))
            memo[i][j] = 2 + getLongestPalindrome(str, i + 1, j - 1, memo);
        else
            memo[i][j] = Math.max(getLongestPalindrome(str, i + 1, j, memo),
                    getLongestPalindrome(str, i, j - 1, memo));

        return memo[i][j];
     }

    // Not mine solution
    public int longestPalindromeSubseq2222(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
