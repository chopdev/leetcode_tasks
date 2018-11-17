import java.util.HashMap;
import java.util.Stack;

/**
 https://leetcode.com/problems/valid-parentheses/description/
 20. Valid Parentheses

 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:

 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:

 Input: "()"
 Output: true
 Example 2:

 Input: "()[]{}"
 Output: true
 Example 3:

 Input: "(]"
 Output: false
 Example 4:

 Input: "([)]"
 Output: false
 Example 5:

 Input: "{[]}"
 Output: true


 SOLUTION
 https://leetcode.com/articles/valid-parentheses/
 */
public class Solution {

    // My O(N) solution, O(N) space
    public boolean isValid(String s) {
        HashMap<Character, Character> openClose = new HashMap<>();
        openClose.put('(', ')');
        openClose.put('[', ']');
        openClose.put('{', '}');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character curr = s.charAt(i);
            if(openClose.containsKey(curr))
                stack.push(curr);
            else {
                if(stack.isEmpty()) return false;
                Character top = stack.pop();
                Character closeBracket = openClose.get(top);
                if(curr != closeBracket) return false;
            }
        }

        if(!stack.isEmpty()) return false;
        return true;
    }
}
