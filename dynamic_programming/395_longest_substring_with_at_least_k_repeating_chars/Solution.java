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
    // Space O(N^2), works fast enough, but get Memory Limit Exception
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

        int[] counts = new int[26];
        for (int i = lo; i <= hi ; i++)
            counts[s.charAt(i) - 'a'] += 1;

        int ind;
        for (ind = lo; ind <= hi && counts[s.charAt(ind) - 'a'] >= k; ind++) { }

        if(ind == hi + 1) dp[lo][hi] = hi - lo + 1;
        else
            dp[lo][hi] = Math.max(longestSubstring(s, lo + 1, hi, k, dp), longestSubstring(s, lo, hi - 1, k, dp));
        return dp[lo][hi];
    }

    // Not mine, recursive solution
    // not the quickest one, looking in leetcode
    // https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87736/C++-recursive-solution
    public int longestSubstring3333(String s, int k) {
        if(s == null || s.length() < k) return 0;

        //  record counts of every character in a hashmap
        int[] counts = new int[26];
        for (int i = 0; i < s.length() ; i++)
            counts[s.charAt(i) - 'a'] += 1;

        // locate the first character that appear less than k times in the string. this character is
        // not included in the result, and that separates the string into two parts
        int ind;
        for (ind = 0; ind < s.length() && counts[s.charAt(ind) - 'a'] >= k; ind++) { }

        if(ind == s.length()) return s.length();

        int left = longestSubstring3333(s.substring(0, ind), k);
        int right = longestSubstring3333(s.substring(ind + 1, s.length()), k);

        return Math.max(left, right);
    }


    // Not mine solution
    // one of the quickest, looking in leetcode
    // idea is similar to previous one, but they do not do substring
    // and throw away ranges of chars that appear < k times
    public int longestSubstring4444(String s, int k) {
        return helper(s.toCharArray(), 0, s.length(), k);
    }

    private int helper(char c[], int left, int right, int k){

        if(right - left < k) return 0;

        char count[] = new char[26];
        for(int i = left; i < right; i++)
            count[c[i] - 'a']++;

        for(int i = left; i < right; i++){

            if(count[c[i] - 'a'] < k){

                int j = i + 1;

                while(j < right && count[c[j] - 'a'] < k) j++;

                return Math.max(helper(c, left, i, k), helper(c, j, right, k));

            }

        }
        return right - left;
    }
}
