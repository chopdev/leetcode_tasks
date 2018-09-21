import java.util.HashMap;

/*
* Given a string, find the length of the longest substring without repeating characters.

Example 1:
Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

* */
public class Solution {

    // Mine solution, tume O(N), space O(N)
    // IMPORTANT, look task #79 , it is popular two pointer approach
    // Explanation
    // move end of substring until we find repeating char or the end of the string
    // if we meet repeating char, move start of the substring until number of repeating chars is 0
    public int lengthOfLongestSubstring(String s) {
        if(s == null) return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, countInvalid = 0, length = 0;

        while (end < s.length()) {
            char curr = s.charAt(end);
            if(map.containsKey(curr)) {
                map.put(curr, map.get(curr) + 1);

                if(map.get(curr) > 1)
                    countInvalid++;
            } else
                map.put(curr, 1);

            if(countInvalid == 0)
                length = length < end - start + 1 ? end - start + 1 : length;

            while (start <= end && countInvalid > 0) {
                curr = s.charAt(start);
                if(map.get(curr) > 1)
                    countInvalid--;

                map.put(curr, map.get(curr) - 1);
                start++;
            }

            end++;
        }

        return length;
    }
}
