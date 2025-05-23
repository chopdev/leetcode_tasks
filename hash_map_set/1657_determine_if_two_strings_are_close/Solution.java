/* 
https://leetcode.com/problems/determine-if-two-strings-are-close/?envType=study-plan-v2&envId=leetcode-75

1657. Determine if Two Strings Are Close


Two strings are considered close if you can attain one from the other using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

 

Example 1:

Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"
Example 2:

Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
Example 3:

Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"
 

Constraints:

1 <= word1.length, word2.length <= 105
word1 and word2 contain only lowercase English letters.

 */

class Solution {
    public boolean closeStrings(String word1, String word2) {
       if (word1.length() != word2.length()) return false;

        Set<Character> word1Letters = new HashSet<>();
        List<Integer> word1Counts = new ArrayList<>();

        Set<Character> word2Letters = new HashSet<>();
        List<Integer> word2Counts = new ArrayList<>();

        int[] word1Map = new int[26];
        int[] word2Map = new int[26];

        for (int i = 0; i< word1.length(); i ++) {
            word1Letters.add(word1.charAt(i));
            word2Letters.add(word2.charAt(i));

            word1Map[word1.charAt(i) - 'a'] ++;
            word2Map[word2.charAt(i) - 'a'] ++;
        }


        for (int i =0; i<26; i++) {
            if (word1Map[i] != 0) word1Counts.add(word1Map[i]);
            if (word2Map[i] != 0) word2Counts.add(word2Map[i]);
        }

        Collections.sort(word1Counts);
        Collections.sort(word2Counts); 

        System.out.println(word2Counts);
        System.out.println(word1Counts);

        return word1Letters.equals(word2Letters) && word1Counts.size() == word2Counts.size() && word1Counts.equals(word2Counts);
    }
}


// same complexity (even for space), but more clean
 public boolean closeStrings2222(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        // Count frequencies of characters in both words
        for (char c : word1.toCharArray()) {
            freq1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            freq2[c - 'a']++;
        }
        
        // Check if both strings have the same set of characters
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0)) {
                return false;
            }
        }
        
        // Sort frequencies
        Arrays.sort(freq1);
        Arrays.sort(freq2);
        
        // Compare frequency arrays
        return Arrays.equals(freq1, freq2);
    }