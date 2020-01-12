import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 1048. Longest String Chain
 https://leetcode.com/problems/longest-string-chain/

 Given a list of words, each word consists of English lowercase letters.
 Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere
 in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1,
 where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 Return the longest possible length of a word chain with words chosen from the given list of words.

 Example 1:

 Input: ["a","b","ba","bca","bda","bdca"]
 Output: 4
 Explanation: one of the longest word chain is "a","ba","bda","bdca".


 Note:

 1 <= words.length <= 1000
 1 <= words[i].length <= 16
 words[i] only consists of English lowercase letters.

 !!AWFUL description of the task, from the beginning  I though chain must be in order from beginning to end of the array
 https://leetcode.com/problems/longest-string-chain/discuss/296030/Incompletewrong-problem-description
 * */
public class Solution {

    // Time = O(NlogN + N^2*S)= O(S*N^2)
    // Space O(N*S)
    public int longestStrChain(String[] words) {
        if(words == null) return 0;
        int[] dp = new int[words.length];
        Arrays.sort(words,(o1, o2) -> o1.length() == o2.length() ? 0 : o1.length() < o2.length() ? -1 : 1);
        for (int i = 0; i < words.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if(dp[j] >= dp[i])
                    if(isPredecessor(words[j], words[i]))
                        dp[i] = dp[j] + 1;
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private boolean isPredecessor(String child, String parent) {
        if(child.length() + 1 != parent.length())
            return false;

        // before diff symbol
        int i = 0, j = 0;
        for (; i < child.length(); i++, j++) {
            if(child.charAt(i) != parent.charAt(j)) {
                j++;
                break;
            }
        }

        // after diff symbol
        for (; i < child.length(); i++, j++) {
            if(child.charAt(i) != parent.charAt(j))
                return false;
        }

        return true;
    }


    // not mine
    // Time O(N*S^2), space O(N*S)
    // https://leetcode.com/problems/longest-string-chain/discuss/294890/C%2B%2BJavaPython-DP-Solution
    // idea with sorting is similar, but they use hash map for DP and check if there were word without one letter previously
    public int longestStrChain2222(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b)->a.length() - b.length());
        int res = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); ++i) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(word, best);
            res = Math.max(res, best);
        }
        return res;
    }
}
