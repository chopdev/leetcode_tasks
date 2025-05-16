/* 
https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/?envType=study-plan-v2&envId=leetcode-75

1456. Maximum Number of Vowels in a Substring of Given Length

Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

 

Example 1:

Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:

Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:

Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.
 

Constraints:

1 <= s.length <= 105
s consists of lowercase English letters.
1 <= k <= s.length
 */

class Solution {
    static final Set<Character> vovels = Set.of('a', 'e', 'i', 'o', 'u');

    public int maxVowels(String s, int k) {
       if (k>s.length()) return -1; // can be skipped 1 <= k <= s.length
       
       int maxCount, count = 0;
       for (int i=0; i< k; i++) 
            if (vovels.contains(s.charAt(i)))
                count ++;

        maxCount = count;
        for (int i = k; i < s.length(); i++) {
            if (vovels.contains(s.charAt(i - k))) count --;
            if (vovels.contains(s.charAt(i))) count ++;

            if (count > maxCount) maxCount = count;
        }

        return maxCount;
    }
}