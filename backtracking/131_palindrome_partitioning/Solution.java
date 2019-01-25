import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/palindrome-partitioning/
131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

* */
public class Solution {

    // Not mine solution
    // https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java%3A-Backtracking-solution.
    /* Time complexity: O(n*(2^n))
    For a string with length n, there will be (n - 1) intervals between chars.
    For every interval, we can cut it or not cut it, so there will be 2^(n - 1) ways to partition the string.
    For every partition way, we need to check if it is palindrome, which is O(n).
    So the time complexity is O(n*(2^n)) */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }

    /*
    backtrack
    if the input is "aab", check if [0,0] "a" is palindrome. then check [0,1] "aa", then [0,2] "aab".
       While checking [0,0], the rest of string is "ab", use ab as input to make a recursive call.
       Every time a recursive call is made, the position of startIndex move right.
    * */
    private void dfs(String str, int startIndex, List<String> curr, List<List<String>> res) {
        if(startIndex == str.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = startIndex; i < str.length(); i++) {
            if(isPalindrome(str, startIndex, i)) {
                curr.add(str.substring(startIndex, i + 1));
                dfs(str, i + 1, curr, res);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str, int start, int end) {
        while (start <= end) {
            if(str.charAt(start) != str.charAt(end)) return false;

            start++;
            end--;
        }
        return true;
    }
}
