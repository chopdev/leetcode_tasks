import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    // Mine solution, time O(2N)=O(N), space O(N)
    // IMPORTANT, look task #79 , it is popular "two pointer" approach or "sliding window" approach
    // https://leetcode.com/articles/longest-substring-without-repeating-characters/
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


    // shorter solution, but idea is the same, Time O(2n)=O(n), Space O(min(M, N))
    public int lengthOfLongestSubstring2222(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    // This solution is optimized, O(N) time
    // when we meet a duplicate, we skip all the chars till the duplicate
    public int lengthOfLongestSubstring3333(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}
