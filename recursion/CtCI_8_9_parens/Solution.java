import java.util.ArrayList;
import java.util.List;

/**
 * Parens: Implement an algorithm to print all valid (e.g., properly opened and closed) combinations
 of n pairs of parentheses.
 EXAMPLE
 Input: 3
 Output: ((())), (()()), (())(), ()(()), ()()()
 */
public class Solution {

    // Mine solution
    // Other approach is to get all possible strings and check which of them are valid using stack
    // Here we do not open close bracket until we opened open bracket
    // time O(2 * 2^N) = O(2^N)
    public List<String> getAllParentheses(int n) {
        List<String> res = new ArrayList<>();
        getAllParentheses(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    private void getAllParentheses(int n, int setOpen, int setClose, StringBuilder builder, List<String> res) {
        if(setOpen == n && setClose == n) { // base case
            res.add(builder.toString());
            return;
        }

        if(setOpen > setClose) {
            StringBuilder newBuilder = new StringBuilder(builder);
            newBuilder.append(")");
            getAllParentheses(n, setOpen, setClose + 1, newBuilder, res);
        }

        if(setOpen < n) {
            builder.append("(");
            getAllParentheses(n, setOpen + 1, setClose, builder, res);
        }
    }

    // This solution is from the book, but with mine interpretation
    // It is better, cause it uses char[] builder that is modified on each level of recursion
    public List<String> getAllParentheses2222(int n) {
        char[] builder = new char[n * 2];
        List<String> res = new ArrayList<>();
        getAllParentheses2222(0, n, n, builder, res);
        return res;
    }

    private void getAllParentheses2222(int index, int openRem, int closeRem, char[] builder, List<String> res) {
        if(openRem == 0 && closeRem == 0) { //base case
            res.add(String.copyValueOf(builder));
            return;
        }

        if(openRem < closeRem) {
            builder[index] = ')';
            getAllParentheses2222(index + 1, openRem, closeRem - 1, builder, res);
        }

        if(openRem > 0) {
            builder[index] = '(';
            getAllParentheses2222(index + 1, openRem - 1, closeRem, builder, res);
        }
    }
}
