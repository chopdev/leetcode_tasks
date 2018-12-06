/**
 https://leetcode.com/problems/basic-calculator-ii/description/
 227. Basic Calculator II

 Implement a basic calculator to evaluate a simple expression string.

 The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

 Example 1:

 Input: "3+2*2"
 Output: 7
 Example 2:

 Input: " 3/2 "
 Output: 1
 Example 3:

 Input: " 3+5 / 2 "
 Output: 5
 Note:

 You may assume that the given expression is always valid.
 Do not use the eval built-in library function.

 My example:
 "3 + 2*2/4 *5 +6 -10/3/3" = 3+5+6-1=13
* */
public class Solution {
    public int calculate(String s) {
        StringBuilder expr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') continue;
            if(s.charAt(i) == '-') expr.append("+-");
            else expr.append(s.charAt(i));
        }
        s = expr.toString();
        return calculatePluses(s.split("\\+"));
    }

    int calculatePluses(String[] pluses) {
        int val = 0;
        for (String operand : pluses) {
            if(operand.isEmpty()) continue;
            val += calculateMult(operand.split("\\*"));
        }
        return val;
    }

    int calculateMult(String[] pluses) {
        int val = 1;
        for (String operand : pluses) {
            if(operand.isEmpty()) continue;
            val *= calculateDivide(operand.split("/"));
        }
        return val;
    }

    private int calculateDivide(String[] dev) {
        int val = Integer.parseInt(dev[0]);
        for (int i = 1; i < dev.length; i++) {
            val /= Integer.parseInt(dev[i]);
        }

        return val;
    }
}
