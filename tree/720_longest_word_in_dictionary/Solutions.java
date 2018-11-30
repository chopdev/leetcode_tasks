import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 https://leetcode.com/problems/longest-word-in-dictionary/description/
 720. Longest Word in Dictionary

 Given a list of strings words representing an English Dictionary, find the longest word in words that can
 be built one character at a time by other words in words. If there is more than one possible answer,
 return the longest word with the smallest lexicographical order.

 If there is no answer, return the empty string.
 Example 1:
 Input:
 words = ["w","wo","wor","worl", "world"]
 Output: "world"
 Explanation:
 The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 Example 2:
 Input:
 words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 Output: "apple"
 Explanation:
 Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically
 smaller than "apply".
 Note:

 All the strings in the input will only contain lowercase letters.
 The length of words will be in the range [1, 1000].
 The length of words[i] will be in the range [1, 30].


 SOLUTIONS
 https://leetcode.com/articles/longest-word-in-dictionary/
 **/
public class Solutions {
    // My almost working solution
    // Returns correct value but not always  in lexicographical order
    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        return trie.getLongest();
    }


    // My solution completely
    // Time O(N^2) if we not take into account string operations
    // Space O(N)
    // Idea is similar to 300 Longest increasing subsecuence - check it on habr
    // We are sorting array and for each element check if there is previous element that is prefix of current
    // and DP length of it > 0
    // initially words with length 1 has DP 1
    public String longestWord2222(String[] words) {
        if (words == null) return null;
        Arrays.sort(words);

        int[] dp = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 1) dp[i] = 1;
        }

        for (int i = 1; i < words.length; i++) {
            if (words[i].length() <= 1) continue;
            String prefix = words[i].substring(0, words[i].length() - 1);
            for (int j = 0; j < i; j++) {
                if (dp[j] > dp[i] && prefix.equals(words[j]))
                    dp[i] = dp[j] + 1;
            }
        }

        int max = 0;
        int index = -1;
        for (int i = 0; i < dp.length; i++) {
            if(dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }

        return index >= 0 ? words[index] : "";
    }


    // Not mine
    // Cool solution
    // pretty easy: For each word, check if all prefixes word[:k] are present. We can use a Set structure to check this quickly.
    public String longestWord3333(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        for (String word: words) wordset.add(word);
        for (String word: words) {
            if (word.length() > ans.length() ||
                    word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) ans = word;
            }
        }
        return ans;
    }
}
