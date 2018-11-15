/**
 https://leetcode.com/problems/implement-strstr/description/
 28. Implement strStr()

 Implement strStr().
 Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Example 1:

 Input: haystack = "hello", needle = "ll"
 Output: 2


 Example 2:

 Input: haystack = "aaaaa", needle = "bba"
 Output: -1

 Clarification:

 What should we return when needle is an empty string? This is a great question to ask during an interview.
 For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 * */
public class Solution {

    // My solution O(N) time, O(1) space
    public int strStr(String haystack, String needle) {
        if(haystack == null || needle == null || needle.isEmpty()) return 0;

        int first = 0, second = 0, nPointer = 0;
        while (first < haystack.length()) {
            second = first; // set pointers for comparing
            nPointer = 0;
            while (nPointer < needle.length() &&
                    second < haystack.length() &&
                    haystack.charAt(second) == needle.charAt(nPointer)) { // while chars are equal we keep going
                if(nPointer == needle.length() - 1) return first; // if char is the last, we found substring
                second++;
                nPointer++;
            }
            first++;
        }
        return -1;
    }

    // Similar solution from leetcode
    // https://leetcode.com/problems/implement-strstr/discuss/
    public int strStr2222(String haystack, String needle) {
        if (haystack == null || needle == null) return Integer.MIN_VALUE;

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j;
            for (j = 0; j < needle.length(); j++)
                if (haystack.charAt(i + j) != needle.charAt(j)) break;

            if (j == needle.length()) return i;
        }
        return -1;
    }
}
