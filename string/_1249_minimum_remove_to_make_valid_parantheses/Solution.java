import java.util.Stack;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 *
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting
 * parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 *
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 *
 *
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either'(' , ')', or lowercase English letter.
 * */
public class Solution {
    /**
     * My solution. O(S) time and space
     *
     *  a(()))b -> a(())b
     *  )a)b(c) -> ab(c)
     *  ab(cdf -> abcdf
     *
     *  1. if begins with ) we need to remove it
     *  2. for opening parentheses we ( need to have a closing one or remove it
     *
     *
     * */
    public String minRemoveToMakeValid2222(String s) {
        StringBuilder sb = new StringBuilder();
        int openCount = 0;
        for (Character ch : s.toCharArray()) {
            if (ch == ')' && openCount <= 0) continue; // skip closing parentheses
            if (ch == '(') openCount++;
            if (ch == ')') openCount --;
            sb.append(ch);
        }

        Stack<Character> reversedSb = new Stack<>();
        for (int i = sb.length() - 1; i >= 0; i --) {
            if (sb.charAt(i) == '(' && openCount > 0)  {
                openCount --;
                continue; // skip open parentheses
            }
            reversedSb.push(sb.charAt(i));
        }

        StringBuilder res = new StringBuilder();
        while (!reversedSb.isEmpty())
            res.append(reversedSb.pop());

        return res.toString();
    }


    // not my solution
    // https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/discuss/419402/JavaC%2B%2B-Stack
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '(') st.add(i);
            if (sb.charAt(i) == ')') {
                if (!st.empty()) st.pop();
                else sb.setCharAt(i, '*');
            }
        }
        while (!st.empty())
            sb.setCharAt(st.pop(), '*');
        return sb.toString().replaceAll("\\*", "");
    }

}
