import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    // My solution
    public int calculate(String s) {
        StringBuilder expr = new StringBuilder();
        // remove whitespaces and change - on +- for initial string
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') continue;
            if(s.charAt(i) == '-') expr.append("+-");
            else expr.append(s.charAt(i));
        }
        s = expr.toString();
        return calculatePluses(s.split("\\+")); // split by pluses and calculate
    }

    int calculatePluses(String[] pluses) {
        int val = 0;
        for (String operand : pluses) {
            if(operand.isEmpty()) continue;
            val += calculateOther(operand);
        }
        return val;
    }

    int calculateOther(String expr) {
        StringBuilder sb = new StringBuilder();

        Queue<Character> operations = new LinkedList<>();
        Queue<String> numbers = new LinkedList<>();

        for (int i = 0; i < expr.length(); i++) {
            char tmp = expr.charAt(i);
            if(tmp == '/' || tmp == '*') {
                operations.add(tmp);
                numbers.add(sb.toString());
                sb = new StringBuilder();
            }
            else
                sb.append(tmp);
        }

        numbers.add(sb.toString());
        int curr = Integer.parseInt(numbers.poll());
        for(String numb : numbers) {
            char operation = operations.poll();
            if(operation == '*')
                curr *= Integer.parseInt(numb);
            else
                curr /= Integer.parseInt(numb);
        }

        return curr;
    }



    // Not mine solution
    // https://leetcode.com/problems/basic-calculator-ii/discuss/63003/Share-my-java-solution
    public int calculate2222(String s) {
        Stack<Integer> operands = new Stack<>(); // stack with operands we need to add
        int currNumb = 0;
        char sign = '+';
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if(Character.isDigit(currChar)) {
                currNumb = currNumb * 10 + currChar - '0';
            }
            else if(currChar != ' ') {
                doOperation(operands, sign, currNumb);
                sign = currChar;  // remembering current sign
                currNumb = 0;  // clear current number
            }
        }

        doOperation(operands, sign, currNumb);
        int res = 0;
        for(int n: operands) res += n;

        return res;
    }

    private void doOperation(Stack<Integer> operands, char sign, int currNumb) {
        switch (sign) {
            case '+': operands.push(currNumb); break;
            case '-': operands.push(-currNumb); break;
            case '*': operands.push(operands.pop() * currNumb); break;
            case '/': operands.push(operands.pop() / currNumb); break;
        }
    }
}
