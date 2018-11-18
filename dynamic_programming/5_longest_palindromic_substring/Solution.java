/**
 https://leetcode.com/problems/longest-palindromic-substring/description/
 5. Longest Palindromic Substring

 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

 Example 1:

 Input: "babad"
 Output: "bab"
 Note: "aba" is also a valid answer.


 Example 2:

 Input: "cbbd"
 Output: "bb"


 SOLUTION
 https://leetcode.com/problems/longest-palindromic-substring/solution/
 * */
public class Solution {
    // My solution without dp
    // O(2^N) time, O(N) space
    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) return s;
        return longestPalRecursive(s, 0, s.length() - 1);
    }

    public boolean isPalindrome(String str, int i, int j) {
        while (i <= j) {
            if(str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    private String longestPalRecursive(String str, int i, int j) {
        if(i == j) return str.substring(i, i + 1);
        if(isPalindrome(str, i, j)) return str.substring(i, j + 1);

        String res1 = longestPalRecursive(str, i+1, j);
        String res2 = longestPalRecursive(str, i, j-1);
        if(res1.length() > res2.length()) return res1;
        return res2;
    }


    // My solution with memoization
    // Time ?,  Space O(N^2)
    public String longestPalindrome2222(String s) {
        if(s == null || s.isEmpty()) return s;
        String[][] dp = new String[s.length()][s.length()];
        return longestPalRecursive2222(s, 0, s.length() - 1, dp);
    }

    private String longestPalRecursive2222(String str, int i, int j, String[][] dp) {
        if(i == j) return str.substring(i, i + 1);
        if(isPalindrome(str, i, j)) return str.substring(i, j + 1);
        if(dp[i][j] != null) return dp[i][j];

        String res1 = longestPalRecursive2222(str, i+1, j, dp);
        String res2 = longestPalRecursive2222(str, i, j-1, dp);
        if(res1.length() > res2.length()) dp[i][j] = res1;
        else dp[i][j] = res2;
        return dp[i][j];
    }


    // My non-recursive solution
    // Time O(N^3), space O(1)
    public String longestPalindrome3333(String s) {
        if(s == null || s.isEmpty()) return s;
        String max = Character.toString(s.charAt(0));
        for (int i = 0; i < s.length(); i++) {
            int j = s.length() - 1;
            while (i < j) {
                if (isPalindrome(s, i, j))
                    max = max.length() <= j - i ? s.substring(i, j + 1) : max;

                j--;
            }
        }

        return max;
    }
}
