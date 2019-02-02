import java.util.HashSet;
import java.util.List;

/**
 139. Word Break
 https://leetcode.com/problems/word-break/

 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.

 Example 1:

 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".


 Example 2:

 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.


 Example 3:

 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false


 Questions:
 1) could strings have some special symbols?
 2) how big is the array, fit in memory?
 3) could we reuse the words from the dictionary?
 * */
public class Solution {

    // My solution, time limit exception
    // backtracking without dp
    // Time complexity
    // S - length of word, N - count of words in dict, W - words length
    // time O(S! + N) ??, space O(N*W+S) where S deepness of recursion, N for hashset
    public boolean wordBreak2222(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        return dfs(s, 0, dict);
    }

    // check if string exist in dictionary, if it is, cut the string and make recursive call
    // if recursive call returns false, check if there is longer string in dictionary
    private boolean dfs(String s, int ind, HashSet<String> dict) {
        for (int i = ind; i < s.length(); i++) {
            if(dict.contains(s.substring(ind, i + 1))) {
                if(i == s.length() - 1) return true;
                boolean res = dfs(s, i + 1, dict);
                if(res) return true;
            }
        }
        return false;
    }


    // My solution
    // backtracking + dp
    // logic is the same but we use dynamic programming to preserve previous reccursion branches
    // Time complexity
    // S - length of word, N - count of words in dict, W - words length
    // time O(S + N) ??, space O(N*W+S) where S deepness of recursion, N for hashset
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        return dfs(s, 0, dict, new int[s.length()]);
    }

    private boolean dfs(String s, int ind, HashSet<String> dict, int[] dp) {
        if(dp[ind] == 1) return false;
        if(dp[ind] == 2) return true;

        for (int i = ind; i < s.length(); i++) {
            if(dict.contains(s.substring(ind, i + 1))) {
                if(i == s.length() - 1) return true;
                boolean res = dfs(s, i + 1, dict, dp);
                dp[ind] = res ? 2 : 1;
                if(res) return true;
            }
        }
        return false;
    }
	
	
	
	
	// Not mine solution
	// https://leetcode.com/problems/word-break/discuss/44054/Java-DP-solution 
	public boolean wordBreak(String s, Set<String> dict) {
	  if (s == null || s.length() == 0) return false;
	  
	  int n = s.length();
	  
	  // dp[i] represents whether s[0...i] can be formed by dict
	  boolean[] dp = new boolean[n];
	  
	  for (int i = 0; i < n; i++) {
		for (int j = 0; j <= i; j++) {
		  String sub = s.substring(j, i + 1);
		  
		  if (dict.contains(sub) && (j == 0 || dp[j - 1])) {
			dp[i] = true;
			break;
		  }
		}
	  }
	  
	  return dp[n - 1];
	}
	
	// Not mine solution
	// https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways
	public boolean wordBreak(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];        
        f[0] = true;

        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && dict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        
        return f[s.length()];
    }
}
