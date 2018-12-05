import java.util.Arrays;
import java.util.HashMap;

/**

 242. Valid Anagram
 https://leetcode.com/problems/valid-anagram/description/

 Given two strings s and t , write a function to determine if t is an anagram of s.

 Example 1:
 Input: s = "anagram", t = "nagaram"
 Output: true

 Example 2:
 Input: s = "rat", t = "car"
 Output: false
 Note:
 You may assume the string contains only lowercase alphabets.

 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?

 GOOD EXPLANATION HERE
 https://leetcode.com/problems/valid-anagram/discuss/66533/Java-solution-HashMap-Unicode-Follow-up

 * */
public class Solution {

    // My good solution
    // O(N) time, O(N) space
    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length()) return  false;

        HashMap<Character, Integer> map = new HashMap();
        for(int i = 0; i<s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(int i = 0; i<t.length(); i++) {
            Character curr = t.charAt(i);
            if(!map.containsKey(curr)) return false;

            int count = map.get(curr);
            if(count == 1)
                map.remove(curr);
            else
                map.put(curr, map.get(curr) - 1);
        }

        if(map.size() == 0) return true;
        return false;
    }

    // Not mine solution
    // O(N)
    public boolean isAnagram2222(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }

    // Not mine solution
    // O(N*logN)
    public boolean isAnagram3333(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);

        for (int i=0; i< s1.length; i++) {
            if (s1[i] != s2[i]) {
                return false;
            }
        }

        return true;
    }
}
