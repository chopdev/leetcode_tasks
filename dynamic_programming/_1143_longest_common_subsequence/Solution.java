import java.util.Arrays;

/**
 * 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 *
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 *
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 * */
public class Solution {

    char[] text1;
    char[]  text2;


    // My initial, simple recursion
    // O(2^(N*M))
    public int longestCommonSubsequence2222(String text1, String text2) {
        this.text1 = text1.toCharArray();
        this.text2 = text2.toCharArray();
        return getLCS(0, 0);
    }

    private int getLCS(int i, int j) {
        if (i >= text1.length || j >= text2.length)
            return 0;

        int k = j;
        for (; k < text2.length; k++) {
            if (text1[i] == text2[k])
                break;
        }

        int longestIfTakeCurr = k < text2.length ? 1 + getLCS(i + 1, k) : Integer.MIN_VALUE;
        int longetstIfSkipCurr = getLCS(i + 1, j);

        return Math.max(longestIfTakeCurr, longetstIfSkipCurr);
    }


    /**
     * My memo solution
     * We either take current char from text1 if there is a matching char at text2. Or we just skip current char.
     *
     * O(N*M) time and space
     * */
    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1.toCharArray();
        this.text2 = text2.toCharArray();
        int[][] memo = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            memo[i] = new int[text2.length()];
            Arrays.fill(memo[i], Integer.MIN_VALUE);
        }
        return getLCS(0, 0, memo);
    }

    private int getLCS(int i, int j, int[][] memo) {
        if (i >= text1.length || j >= text2.length)
            return 0;
        if (memo[i][j] != Integer.MIN_VALUE)
            return memo[i][j];

        int k = j;
        for (; k < text2.length; k++) {
            if (text1[i] == text2[k])
                break;
        }

        int longestIfTakeCurr = k < text2.length ? 1 + getLCS(i + 1, k + 1, memo) : Integer.MIN_VALUE;
        int longetstIfSkipCurr = getLCS(i + 1, j, memo);

        memo[i][j] = Math.max(longestIfTakeCurr, longetstIfSkipCurr);
        return memo[i][j];
    }

    // DP[i][j] = DP[i - 1][j - 1] + 1 , if text1[i] == text2[j] DP[i][j] = max(DP[i - 1][j], DP[i][j - 1]) , otherwise

}
