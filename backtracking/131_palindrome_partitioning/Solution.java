/*
131. Palindrome Partitioning
https://leetcode.com/problems/palindrome-partitioning/

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
import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(s, 0, new StringBuilder(), new ArrayList<>(), res);
        return res;
    }

    /**
     * At each character, either extend curr hoping to form a palindrome,
     * or start curr again if its previous contents form a palindrome.
     *
     * Let S be the length of s.
     * Time: O(S * 2^S), with at most two choices per character and O(S)
     * work per recursive call for palindrome checks, copies, and restoration.
     * Auxiliary space: O(S) for curr, partitions, and the recursion stack.
     * Output space: O(S * 2^S) in the worst case.
     */
    private void backtrack(String s,
        int i,
        StringBuilder curr,
        List<String> partitions,
        List<List<String>> res) {

        if (i == s.length()) {
            if (isPalindrome(curr)) {
                partitions.add(curr.toString());
                res.add(new ArrayList<>(partitions));
                partitions.remove(partitions.size() - 1);
            }
            return;
        }

        curr.append(s.charAt(i));
        backtrack(s, i + 1, curr, partitions, res);
        curr.deleteCharAt(curr.length() - 1);

        if (isPalindrome(curr)) {
            String temp = curr.toString();

            partitions.add(temp);
            curr.setLength(0);

            curr.append(s.charAt(i));
            backtrack(s, i + 1, curr, partitions, res);

            partitions.remove(partitions.size() - 1);
            curr.setLength(0);
            curr.append(temp);
        }
    }

    private boolean isPalindrome(StringBuilder sb) {
        if (sb == null || sb.length() == 0) return false;

        String curr = sb.toString();
        int len = curr.length();
        if (len == 1) return true;

        if (len % 2 == 1) { // odd number, we need to split by middle char
            String st1 = curr.substring(0, len / 2);
            String st2 = new StringBuilder(curr.substring(len / 2 + 1)).reverse().toString();

            return st1.equals(st2);
        } else {
            String st1 = curr.substring(0, len / 2);
            String st2 = new StringBuilder(curr.substring(len / 2)).reverse().toString();
            return st1.equals(st2);
        }
    }

    // Solution 2: previous implementation (not mine).
    // https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java%3A-Backtracking-solution.
    /* Time complexity: O(n*(2^n))
    For a string with length n, there will be (n - 1) intervals between chars.
    For every interval, we can cut it or not cut it, so there will be 2^(n - 1) ways to partition the string.
    For every partition way, we need to check if it is palindrome, which is O(n).
    So the time complexity is O(n*(2^n)) */
    public List<List<String>> partition2(String s) {
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

    // Solution 3: try each possible end index and recurse on palindromic prefixes.
    // Time: O(S * 2^S); auxiliary space: O(S), excluding output.
    public List<List<String>> partition3(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack3(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack3(String s,
        int start,
        List<String> partitions,
        List<List<String>> res) {

        if (start == s.length()) {
            res.add(new ArrayList<>(partitions));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome3(s, start, end)) {
                partitions.add(s.substring(start, end + 1));
                backtrack3(s, end + 1, partitions, res);
                partitions.remove(partitions.size() - 1);
            }
        }
    }

    private boolean isPalindrome3(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--))
                return false;
        }
        return true;
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
