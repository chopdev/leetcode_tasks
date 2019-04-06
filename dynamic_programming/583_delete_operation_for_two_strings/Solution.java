/*
583. Delete Operation for Two Strings
https://leetcode.com/problems/delete-operation-for-two-strings/

Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:

Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
* */
public class Solution {
    // Mine solution
    // O(N*M) time, space O(N*M+ N+M) =O(N*M) - memo+length of recursion
    public int minDistance(String word1, String word2) {
        if(word1.isEmpty()) return word2.length();
        if(word2.isEmpty()) return word1.length();

        int longest = lcs(word1, word2, 0, 0, new int[word1.length()][word2.length()]);
        int res = word1.length() - longest;
        res += word2.length() - longest;
        return res;
    }

    // Longest Common Subsequence
    private int lcs(String str1, String str2, int i, int j, int[][] memo) {
        if(i >= str1.length() || j >= str2.length()) return 0;

        if(memo[i][j] > 0) return memo[i][j];
        if(str1.charAt(i) == str2.charAt(j))
            memo[i][j] = 1 + lcs(str1, str2, i + 1, j + 1, memo);
        else
            memo[i][j] = Math.max(lcs(str1, str2, i + 1, j, memo),
                    lcs(str1, str2, i, j + 1, memo));

        return memo[i][j];
    }


    // Not mine solution
    // https://leetcode.com/problems/delete-operation-for-two-strings/discuss/103214/Java-DP-Solution-(Longest-Common-Subsequence)
    public int minDistance2222(String word1, String word2) {
        int dp[][] = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word1.length(); i++) {
            for(int j = 0; j <= word2.length(); j++) {
                if(i == 0 || j == 0) dp[i][j] = 0;
                else dp[i][j] = (word1.charAt(i-1) == word2.charAt(j-1)) ? dp[i-1][j-1] + 1
                        : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        int val =  dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;
    }
}
