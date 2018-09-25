import java.util.HashMap;
/*
76. Minimum Window Substring

Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
Example:
Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Note:
If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.


SIMILAR TASK but with arrays

Given two lists: S1 and S2, S1 is bigger than S2. Find start and end indexes of sublist in S1 that contains all elements from S2. 
If there several lists with the same minimum length, then return sublist with smaller start index.

* */
public class Solution {

    // IMPORTANT: This template with two pointers can be used to many substring problems
    // Explanation here https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
    // Time complexity O(S), Space O(S)
    public String minWindow(String s, String t) {
        if (s == null || t == null) return null;

        // map for tracking characters in t
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i)))
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            else
                map.put(t.charAt(i), 1);
        }

        // end - head pointer, goes first
        // start - tail pointer
        // head - for remembering beginning of the shortest substring
        // lenght - length of substring
        // count - count of t characters that are in substring of s
        int end = 0, start = 0, head = 0, length = Integer.MAX_VALUE, count = 0;

        while (end < s.length()) {
            Character currChar = s.charAt(end);
            if (map.containsKey(currChar)) {
                map.put(currChar, map.get(currChar) - 1);
                if (map.get(currChar) >= 0)
                    count++; // increase count of common chars in t and s
            }

            while (count == t.length()) { // while we have all t chars is substring of s, try to shorten start of substring
                if (length > end - start) {
                    length = end - start;
                    head = start;
                }
                currChar = s.charAt(start);
                if (map.containsKey(currChar)) {
                    map.put(currChar, map.get(currChar) + 1);
                    if (map.get(currChar) > 0)
                        count--; // invalidate, we removed obligatory t char from the beginning of substring
                }
                start++; // shorten start
            }

            end++;  // increase end of substring
        }

        return length == Integer.MAX_VALUE ? "" : s.substring(head, head + length + 1);
    }

    /*
    * Another approach mine solution is to use recursive solution + dynamic programming
    * The idea is to shorthand string s on each recursive call from 1 side and from other side
    * and check which case has lesser length and valid
    * Without dynamic programming, time complexity O(2^n * n) - 2^n - two recursive branches on each recursive level, n - check if substring has all letters of t
    * WIth dynamic programming: time complexity O(n^2) , n - number of recursive calls and n - checking of t
    * */
}
