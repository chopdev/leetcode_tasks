/**
 387. First Unique Character in a String
 https://leetcode.com/problems/first-unique-character-in-a-string/

 Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 Note: You may assume the string contain only lowercase letters.


 QUESTION:
 1) what symbols could be in a string?
 2) is it fit in memory?
 */
public class Solution {

    // My solution
    // O(N) time, O(1) space
    public int firstUniqChar(String s) {
        int[] letters = new int[26];
        for(int i = 0; i < s.length(); i++) {
            letters[s.charAt(i) - 'a'] += 1;
        }

        for(int i = 0; i < s.length(); i++) {
            if(letters[s.charAt(i) - 'a'] == 1) return i;
        }

        return -1;
    }
}
