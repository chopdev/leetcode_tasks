/**
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * 10. Regular Expression Matching
 *
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 *
 *
 *
 *
 * */
public class Solution {


    /**
     * My implemenation, not my idea
     * https://leetcode.com/problems/regular-expression-matching/solutions/127565/regular-expression-matching/?orderBy=most_votes
     *
     * NOTES:
     *  - read requirements attentively. * - means zero or more of previous char and it's always present
    * */
    public boolean isMatch(String s, String p) {
        if (s.equals(p)) return true;
        if (s.isEmpty() || p.isEmpty()) return false;
        return isMatchDfs(s, p);
    }

    private boolean isMatchDfs(String s, String p) {
        if (p.isEmpty()) return s.isEmpty(); // true if both are finished
        if (s.isEmpty()) return p.length() == 2 && p.charAt(p.length() - 1) == '*';

        boolean firstMatched = s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';

        if (p.length() > 1 && p.charAt(1) == '*') {
            boolean matchedSkipStar = isMatchDfs(s, p.substring(2));
            boolean matchedWithStar = firstMatched && isMatchDfs(s.substring(1), p);
            return matchedSkipStar || matchedWithStar;
        }

        if (!firstMatched) return false;
        return isMatchDfs(s.substring(1), p.substring(1));
    }


    /**
     * My solution, same idea is above but with memoization
     *
     * Failing for this case
     * sol.isMatch22222("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*")
     * */
    public boolean isMatch22222(String s, String p) {
        if (s.equals(p)) return true;
        if (s.isEmpty() || p.isEmpty()) return false;
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return isMatchDfs(s, p, 0, 0, memo);
    }

    private boolean isMatchDfs(String text, String pattern, int i, int j, Boolean[][] memo) {
        if (j == pattern.length()) return i == text.length(); // true if both are finished
        if (i == text.length()) return j == pattern.length() - 2 && pattern.charAt(pattern.length() - 1) == '*'; // true if text finished and p is *
        if (memo[i][j] != null) return memo[i][j];

        boolean firstMatched = text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.';

        if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
            boolean matchedSkipStar = isMatchDfs(text, pattern, i, j + 2, memo);
            boolean matchedWithStar = firstMatched && isMatchDfs(text, pattern, i + 1, j, memo);

            memo[i][j] = matchedSkipStar || matchedWithStar;
            return memo[i][j] ;
        }

        memo[i][j] = firstMatched && isMatchDfs(text, pattern, i + 1, j + 1, memo);
        return memo[i][j];
    }



    /**
     * Not my, https://leetcode.com/problems/regular-expression-matching/solutions/127565/regular-expression-matching/?orderBy=most_votes
     * */
    public boolean isMatch33333(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }



    enum Result {
        TRUE, FALSE
    }
    Result[][] memo;

    public boolean isMatch44444(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                        first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }
}
