import java.util.HashMap;
import java.util.Map;

/**
 * 1371. Find the Longest Substring Containing Vowels in Even Counts
 * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 *
 * Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 *
 * Example 1:
 *
 * Input: s = "eleetminicoworoep"
 * Output: 13
 * Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
 *
 *
 * Example 2:
 *
 * Input: s = "leetcodeisgreat"
 * Output: 5
 * Explanation: The longest substring is "leetc" which contains two e's.
 *
 *
 * Example 3:
 *
 * Input: s = "bcbcbc"
 * Output: 6
 * Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 x 10^5
 * s contains only lowercase English letters
 * */
public class Solution {

    Map<Character, Integer> vowels = new HashMap<>();

    {
        vowels.put('a', 1);
        vowels.put('e', 2);
        vowels.put('i', 4);
        vowels.put('o', 8);
        vowels.put('u', 16);
    }

    /**
     * My O(N^2) solution
     * Figured out a trick with bitmap myself
     *
     * 1 xor 1 = 0
     * 0 xor 1 = 1
     * 0 xor 0 = 0
     * */
    public int findTheLongestSubstring2222(String s) {
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            int length = 0;
            int bitmap = 0;
            for (int j = i; j < s.length(); j++) {
                if (vowels.containsKey(s.charAt(j)))
                    bitmap ^= vowels.get(s.charAt(j));

                length++;
                if (bitmap == 0 && length > longest) longest = length;
            }
        }
        return longest;
    }


    /**
     * Not my idea https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/discuss/531840/JavaC%2B%2BPython-One-Pass
     * O(N) time, O(1) space solution
     * */
    public int findTheLongestSubstring(String s) {
        int res = 0 , bitmap = 0, n = s.length();
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; ++i) {
            if (vowels.containsKey(s.charAt(i)))
                bitmap ^= vowels.get(s.charAt(i));
            seen.putIfAbsent(bitmap, i);
            res = Math.max(res, i - seen.get(bitmap));
        }
        return res;
    }
}
