/**
 72. Edit Distance
 https://leetcode.com/problems/edit-distance/

 Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

 You have the following 3 operations permitted on a word:

 Insert a character
 Delete a character
 Replace a character


 Example 1:

 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')


 Example 2:

 Input: word1 = "intention", word2 = "execution"
 Output: 5
 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')


 EXPLANATION:
 https://www.baeldung.com/java-levenshtein-distance

 */
public class Solution {

    // Not mine solution
    // Levenshtein distance
    // https://www.baeldung.com/java-levenshtein-distance
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        return getMinDistance(word1, word2, 0, 0, memo);
    }

    private int getMinDistance(String word1, String word2, int i, int j, int[][] memo) {
        if(i >= word1.length()) return word2.length() - j; // return rest of the length of the other word
        if(j >= word2.length()) return word1.length() - i;
        if(memo[i][j] > 0) return memo[i][j];

        // try to insert the char from the second word into first word, so move pointer of the second word (as we used j char of it)
        // cost of operation = 1
        int insert = 1 + getMinDistance(word1, word2, i, j + 1, memo);
        // try to delete char from the first word. so consider only rest of it
        // cost of operation = 1
        int delete = 1 + getMinDistance(word1, word2, i + 1, j, memo);

        // try to replace char of the first word by char from the second one
        // if chars are the same (there are nothing to replace), just consider the rest of the words
        // if they are not the same, replace one char and consider rest of words
        int operationCost = word1.charAt(i) == word2.charAt(j) ? 0 : 1;
        int replace = operationCost + getMinDistance(word1, word2, i + 1, j + 1, memo);

        memo[i][j] = Math.min(insert, Math.min(delete, replace));
        return memo[i][j];
    }


    // Not mine solution
    // https://www.baeldung.com/java-levenshtein-distance
    public int minDistance2222(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];

        for (int i = 0; i < word1.length(); i++)
            dp[i][0] = i;

        for (int j = 0; j < word2.length(); j++)
            dp[0][j] = j;

        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                int cost = word1.charAt(i - 1) == word2.charAt(j-1) ? 0 : 1;
                dp[i][j] = Math.min(cost + dp[i-1][j-1], Math.min(1 + dp[i][j-1], 1 + dp[i-1][j]));
            }
        }

        return dp[word1.length() - 1][word2.length() - 1];
    }
}
