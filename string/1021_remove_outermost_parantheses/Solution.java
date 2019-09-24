/**
 1021. Remove Outermost Parentheses

 A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings,
 and + represents string concatenation.

 For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.

 A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B,
 with A and B nonempty valid parentheses strings.

 Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
 Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.

 Example 1:

 Input: "(()())(())"
 Output: "()()()"
 Explanation:
 The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 After removing outer parentheses of each part, this is "()()" + "()" = "()()()".

 Example 2:

 Input: "(()())(())(()(()))"
 Output: "()()()()(())"
 Explanation:
 The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".


 Example 3:

 Input: "()()"
 Output: ""
 Explanation:
 The input string is "()()", with primitive decomposition "()" + "()".
 After removing outer parentheses of each part, this is "" + "" = "".


 Note:

 S.length <= 10000
 S[i] is "(" or ")"
 S is a valid parentheses string
 * */
public class Solution {
    // My solution
    public String removeOuterParentheses(String S) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<S.length(); i++) {
            if(count > 1 || count == 1 && S.charAt(i) != ')') sb.append(S.charAt(i));

            if(S.charAt(i) == '(') count++;
            else count--;
        }
        return sb.toString();
    }

    // Not mine solution, similar
    // https://leetcode.com/problems/remove-outermost-parentheses/discuss/270022/JavaC%2B%2BPython-Count-Opened-Parenthesis
    public String removeOuterParentheses2222(String S) {
        StringBuilder s = new StringBuilder();
        int opened = 0;
        for (char c : S.toCharArray()) {
            if (c == '(' && opened++ > 0) s.append(c);
            if (c == ')' && opened-- > 1) s.append(c);
        }
        return s.toString();
    }

    // cool solution
    // https://leetcode.com/problems/remove-outermost-parentheses/discuss/270566/My-Java-3ms-Straight-Forward-Solution-or-Beats-100
    public String removeOuterParentheses3333(String S) {
        StringBuilder sb = new StringBuilder();
        int open=0, close=0, start=0;
        for(int i=0; i<S.length(); i++) {
            if(S.charAt(i) == '(') {
                open++;
            } else if(S.charAt(i) == ')') {
                close++;
            }
            if(open==close) {
                sb.append(S.substring(start+1, i));
                start=i+1;
            }
        }
        return sb.toString();
    }
}
