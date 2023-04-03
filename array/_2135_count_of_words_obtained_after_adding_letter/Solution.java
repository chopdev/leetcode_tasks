import java.util.HashSet;
import java.util.Set;

/**
 * 2135. Count Words Obtained After Adding a Letter
 * https://leetcode.com/problems/count-words-obtained-after-adding-a-letter/
 *
 * You are given two 0-indexed arrays of strings startWords and targetWords. Each string consists of lowercase English letters only.
 * For each string in targetWords, check if it is possible to choose a string from startWords and perform a conversion operation on it to be equal to that from targetWords.
 * The conversion operation is described in the following two steps:
 *
 * Append any lowercase letter that is not present in the string to its end.
 * For example, if the string is "abc", the letters 'd', 'e', or 'y' can be added to it, but not 'a'. If 'd' is added, the resulting string will be "abcd".
 * Rearrange the letters of the new string in any arbitrary order.
 * For example, "abcd" can be rearranged to "acbd", "bacd", "cbda", and so on. Note that it can also be rearranged to "abcd" itself.
 * Return the number of strings in targetWords that can be obtained by performing the operations on any string of startWords.
 *
 * Note that you will only be verifying if the string in targetWords can be obtained from a string in startWords by performing the operations. The strings in startWords do not actually change during this process.
 *
 *
 *
 * Example 1:
 *
 * Input: startWords = ["ant","act","tack"], targetWords = ["tack","act","acti"]
 * Output: 2
 * Explanation:
 * - In order to form targetWords[0] = "tack", we use startWords[1] = "act", append 'k' to it, and rearrange "actk" to "tack".
 * - There is no string in startWords that can be used to obtain targetWords[1] = "act".
 *   Note that "act" does exist in startWords, but we must append one letter to the string before rearranging it.
 * - In order to form targetWords[2] = "acti", we use startWords[1] = "act", append 'i' to it, and rearrange "acti" to "acti" itself.
 *
 *
 * Example 2:
 *
 * Input: startWords = ["ab","a"], targetWords = ["abc","abcd"]
 * Output: 1
 * Explanation:
 * - In order to form targetWords[0] = "abc", we use startWords[0] = "ab", add 'c' to it, and rearrange it to "abc".
 * - There is no string in startWords that can be used to obtain targetWords[1] = "abcd".
 *
 *
 * Constraints:
 *
 * 1 <= startWords.length, targetWords.length <= 5 * 104
 * 1 <= startWords[i].length, targetWords[j].length <= 26
 * Each string of startWords and targetWords consists of lowercase English letters only.
 * No letter occurs more than once in any string of startWords or targetWords.
 * */
public class Solution {

    /***
     * My solution
     * assuming N - startWords len, M - targetWords len, S - average len of the word
     * O(N*S + M*S + N*M) - time complexity
     * O(M+N) - space complexity
     *
     * The idea is to transform words to bit maps and then check if bitmaps differ only on one bit. Each bit represents a letter in bitmap.
     *
     * Brute force is to use sorting for each candidate word. Then compare strings
     *
     * */
    public int wordCount2222(String[] startWords, String[] targetWords) {
        int[] startMaps = convertToBitmap(startWords);
        int[] targetMaps = convertToBitmap(targetWords);
        boolean[] used = new boolean[targetWords.length];
        int res = 0;

        for (int i = 0; i < startMaps.length; i++) {
            int start = startMaps[i];
            for (int j = 0; j < targetMaps.length; j++) {
                int target = targetMaps[j];
                if (!used[j] &&
                        startWords[i].length() + 1 == targetWords[j].length()
                        && isExactlyOneBitSet(start ^ target)) {
                    res ++;
                    used[j] = true;
                }
            }
        }
        return res;
    }

    private boolean isExactlyOneBitSet(int num) {
        // Use bitwise AND operator to check if only one bit is set to 1
        // The expression (num & (num - 1)) will clear the least significant bit that is set to 1
        // If the result is 0, then only one bit was set to 1 in the original number
        return (num & (num - 1)) == 0 && num != 0;
    }

    private int[] convertToBitmap(String[] words) {
        int[] bitmaps = new int[words.length];
        int i = -1;
        for (String word : words) {
            int bitmap = 0;
            for (char ch : word.toCharArray()) {
                bitmap ^= 1 << (ch - 'a'); // shift 1 N letters to the left and assign to bitmap
            }
            bitmaps[++i] = bitmap;
        }
        return bitmaps;
    }



    /**
     * Not my solution
     * https://leetcode.com/problems/count-words-obtained-after-adding-a-letter/solutions/1809326/java-beats-95-best-question-to-understand-bitmask/
     *
     * Use bit mask to represent used characters.
     * We add masks of start words to the hash set. For a target word, we get a bit mask, remove one of the characters, and look it up in the hash set.
     *
     * O(N*S + M*S + M*S) = O(N*S + M*S) time
     * */
    public int wordCount(String[] startWords, String[] targetWords) {
        Set<Integer> s = new HashSet<>();
        int res = 0;
        for(String sw : startWords) s.add(bitMask(sw));

        for(String tw : targetWords) {
            int twBitmask = bitMask(tw);
            for(char c : tw.toCharArray()) {
                if(s.contains(twBitmask - (1 << c - 'a'))) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    private int bitMask(String s) {
        int res = 0;
        for(char c : s.toCharArray()) res += 1 << c - 'a';
        return res;
    }


    /**
     * Another 2 approaches
     * https://leetcode.com/problems/count-words-obtained-after-adding-a-letter/solutions/1676859/trie-vs-bitmask-vs-sorting/
     *
     * 1. We sort and store start words in the trie. When matching, we have an option to skip a letter from the target word.
     * This works because there are no repeated characters in target words.
     *
     * 2.For each start word, we generate all possible options (by adding each missing letter). Then we sort the result and store it in a hash set.
     * Then, we can obtain a target word if it is our hash set.
     * */
}
