/*
424. Longest Repeating Character Replacement

https://leetcode.com/problems/longest-repeating-character-replacement/description/

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

 

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
 

Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
*/



/*
k=2
[ABCABC] -> [AAAABC]

k=2
[AABCDKAAA]

k=2
[ABC DABCD]

[A BCC DABCD]

maxFrequentChar
maxFreq 
map[] // track count of every char
res

if (windowSize - maxFreq > k)


k = 0;
s = "BAAD";
*/

class Solution {

    // O(N) time, O(1) space
    public int characterReplacement(String s, int k) {
        if (s.length() <= k + 1) return s.length();

        int[] map = new int[128]; // track count of every char

        char maxFrequentChar = '0';
        int maxFreq = 0, res = 0;
        int begin = 0, end = 0;

        for (; end < s.length(); end ++) {
            map[s.charAt(end)] ++;

            if (map[s.charAt(end)] > maxFreq) {
                maxFreq = map[s.charAt(end)];
                maxFrequentChar = s.charAt(end);
            }
        
            // end - begin + 1 = windowSize
            while (end - begin + 1 - maxFreq > k) { // we need to shrink the window to be able to substitute
                map[s.charAt(begin)] --;
                begin ++;
            }

            res = Math.max(res, end - begin + 1); // longest window is the needed result
        }


        return res;
    }
}

/*
Keeping maxFreq historical is safe because it can only delay shrinking, and the max window length you record still corresponds to a window size that is achievable with ≤ k replacements somewhere during the scan; it won’t make you output a length that’s impossible.

If you try to keep it exact, you’d need to recompute the max count in the window when you decrement counts (that’s O(26) per shrink for uppercase letters, or worse for general alphabets). Historical maxFreq keeps the whole thing cleanly O(n).
*/

