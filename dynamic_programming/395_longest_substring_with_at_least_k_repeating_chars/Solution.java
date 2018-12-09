import java.util.Arrays;
import java.util.HashMap;

/**
 https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
 395. Longest Substring with At Least K Repeating Characters

 Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

 Example 1:

 Input:
 s = "aaabb", k = 3
 Output:
 3
 The longest substring is "aaa", as 'a' is repeated 3 times.


 Example 2:

 Input:
 s = "ababbc", k = 2
 Output:
 5
 The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.


 My example

 "kababcbaaabaalo"

 The longest substring is "baaabaa"
* */
public class Solution {

    // My recursive solution without DP
    // Time O(2^N*N), space O(N) deepness of recursion
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() < k) return 0;
        return longestSubstring(s, 0, s.length() - 1, k);
    }

    private int longestSubstring(String s, int lo, int hi, int k) {
        if(hi - lo + 1 < k) return 0;

        HashMap<Character, Integer> counts = new HashMap<>();
        for (int i = lo; i <=hi ; i++) {
            char curr = s.charAt(i);
            if(!counts.containsKey(curr)) counts.put(curr, 1);
            else counts.put(curr, counts.get(curr) + 1);
        }

        boolean valid = true;
        for(int number : counts.values()) {
            if(number < k) {
                valid = false;
                break;
            }
        }

        if(valid) return hi - lo + 1;
        return Math.max(longestSubstring(s, lo + 1, hi, k), longestSubstring(s, lo, hi - 1, k));
    }


    // My dynamic programming solution
    // Time O(N^3), N*N - calls and each call makes N operations
    // Space O(N^2)
    public int longestSubstring2222(String s, int k) {
        if(s == null || s.length() < k) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], -1);

        return longestSubstring(s, 0, s.length() - 1, k, dp);
    }

    private int longestSubstring(String s, int lo, int hi, int k, int[][] dp) {
        if(hi - lo + 1 < k) return 0;
        if(dp[lo][hi] > -1) return dp[lo][hi];

        HashMap<Character, Integer> counts = new HashMap<>();
        for (int i = lo; i <=hi ; i++) {
            char curr = s.charAt(i);
            if(!counts.containsKey(curr)) counts.put(curr, 1);
            else counts.put(curr, counts.get(curr) + 1);
        }

        boolean valid = true;
        for(int number : counts.values()) {
            if(number < k) {
                valid = false;
                break;
            }
        }

        if(valid) dp[lo][hi] = hi - lo + 1;
        else
            dp[lo][hi] = Math.max(longestSubstring(s, lo + 1, hi, k, dp), longestSubstring(s, lo, hi - 1, k, dp));
        return dp[lo][hi];
    }
}
