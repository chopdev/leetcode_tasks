import java.util.HashMap;
import java.util.Map;

/**
 * Private task on leetcode:  https://leetcode.com/problems/word-pattern-ii/
 *
 *
 * Given a pattern. Find if string s fits this pattern.
 *
 * Examples:
 *
 * 1)
 * pattern = "aab"
 * s = "ererek"
 * result = true
 *
 * 2)
 * pattern = "aaa"
 * s = "bbb"
 * result = true
 *
 * 3)
 * pattern = "abca"
 * s = aabbccac
 * result = false
 *
 * */

public class Solution {

    // In the worst case we are going to search for word of each pattern char
    // not sure, but time complexity seems to be O(P*S!)

    // Depth of recursion is equal to P, size of patternCharToWord == P
    // space complexity = O(P)
    public boolean isPattern(String pattern, String s) {
        if (pattern == null || pattern.isEmpty() || s == null || s.isEmpty() || pattern.length() > s.length()) {
            return false;
        }

        for (int i=1; i<s.length(); i++) {
            Map<Character, String> patternCharToWord = new HashMap<>();
            patternCharToWord.put(pattern.charAt(0), s.substring(0, i));

            if (backtrack(patternCharToWord, pattern, s, 1, i)) {
                printWords(patternCharToWord, pattern);
                return true;
            }
        }
        return false;
    }

    private boolean backtrack(Map<Character, String> patternCharToWord, String pattern, String s, int patternIndex, int sIndex) {
        if (patternIndex == pattern.length() && sIndex == s.length()) {
            return true;
        } else if (patternIndex >= pattern.length() || sIndex >= s.length()) {
            return false; // s or pattern has ended
        }

        boolean patternDefinedForChar = patternCharToWord.containsKey(pattern.charAt(patternIndex));
        if (patternDefinedForChar) {
            String word = patternCharToWord.get(pattern.charAt(patternIndex));
            boolean fitsPattern = fitsPattern(s, sIndex, word);
            if (!fitsPattern) return false;
            return backtrack(patternCharToWord, pattern, s, patternIndex + 1, sIndex + word.length());
        }


        // pattern is not defined in the map, try to figure out pattern word for char pattern.charAt(patternIndex)
        Character patternChar = pattern.charAt(patternIndex);
        for (int i = sIndex + 1; i < 1 + s.length(); i++) {
            String word = s.substring(sIndex, i);
            patternCharToWord.put(patternChar, word);
            boolean fitsPattern = backtrack(patternCharToWord, pattern, s, patternIndex + 1, sIndex + word.length());
            if (fitsPattern) return true; // in another case try to increase pattern word on one char
        }

        return false;
    }

    public boolean fitsPattern(String s, int sIndex, String word) {
        if (sIndex + word.length() > s.length()) return false;
        return s.substring(sIndex, sIndex + word.length()).equals(word);
    }

    private void printWords(Map<Character, String> patternCharToWord, String pattern) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : pattern.toCharArray()) {
            sb.append(patternCharToWord.get(ch));
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }


}
