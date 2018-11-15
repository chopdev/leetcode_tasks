import java.util.Arrays;

/**
 https://leetcode.com/problems/longest-common-prefix/description/
 14. Longest Common Prefix

 Write a function to find the longest common prefix string amongst an array of strings.

 If there is no common prefix, return an empty string "".

 Example 1:

 Input: ["flower","flow","flight"]
 Output: "fl"
 Example 2:

 Input: ["dog","racecar","car"]
 Output: ""
 Explanation: There is no common prefix among the input strings.
 Note:

 All given inputs are in lowercase letters a-z.
 */
public class Solution {
    // Mine brute force solution
    // time O(N*K), N - number of strings, K  - length of the largest str
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";

        String common = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (j = 0; j < Math.min(common.length(), strs[i].length()); j++) {
                if(common.charAt(j) != strs[i].charAt(j))
                    break;
            }
            if(j == 0) return "";
            common = common.substring(0, j);
        }
        return common;
    }

    // My solution
    // Using Tie, detect common substring for all strs
    // time O(N*K), N - number of strings, K  - length of the largest str
    // O(S) space
    // similar to 
    public String longestCommonPrefix2222(String[] strs) {
        if(strs == null || strs.length == 0) return "";

        Trie trie = new Trie();
        for (String str : strs) {
            if(str.isEmpty()) return "";
            trie.insert(str);
        }

        return trie.getLongestPrefix();
    }


    // My solution
    // O(N*logN + K) time, faster than previous two in leetcode
    // We sort array and common part of first and last strings would be the answer
    public String longestCommonPrefix3333(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        if(strs.length == 1) return strs[0];

        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        int i = 0;
        for (i = 0; i < Math.min(first.length(), last.length()); i++) {
            if(first.charAt(i) != last.charAt(i))
                break;
        }

        return first.substring(0, i);
    }

    // Not mine - Vertical scanning
    // O(S) , where S is the sum of all characters in all strings
    //  In the worst case there will be nn equal strings with length mm and the algorithm performs S = m*nS=m∗n character comparisons.
    // Even though the worst case is still the same as Approach 1,
    // scans character by character in each str
    // https://leetcode.com/problems/longest-common-prefix/solution/
    public String longestCommonPrefix4444(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }


    // Not mine binary search solution
    // In the worst case we have n equal strings with length m
    // Time O(N*M*logN)
    // O(1) space
    public String longestCommonPrefix5555(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }
}
