/*
22. Generate Parentheses
https://leetcode.com/problems/generate-parentheses/

Given n pairs of parentheses, return every valid arrangement of those pairs.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Track the total number of opening parentheses added. A closing parenthesis
     * can only be added when there is an unmatched opening parenthesis.
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(0, 0, n * 2, new StringBuilder(), res);
        return res;
    }

    public void backtrack(int opened, int i, int total, StringBuilder curr, List<String> res) {
        if (i == total) {
            res.add(curr.toString());
            return;
        }

        // Add an opening parenthesis if fewer than n have been added.
        if (opened < total / 2) {
            curr.append("(");
            backtrack(opened + 1, i + 1, total, curr, res);
            curr.deleteCharAt(curr.length() - 1);
        }

        // There are i - opened closing parentheses, so opened * 2 > i
        // means an unmatched opening parenthesis is available.
        if (opened * 2 > i) {
            curr.append(")");
            backtrack(opened, i + 1, total, curr, res);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}
