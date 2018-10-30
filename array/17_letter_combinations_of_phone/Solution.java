import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

2 "abc", 3 "def", 4 "ghi", 5 "jkl", 6 "mno", 7 "pqrs", 8 "tuv", 9 "wxyz"

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

* */
public class Solution {

    // Mine good recursive solution
    // O(N*K) time N - number of digits, K - length of code like "pqrs",
    // Space O(N) - deepness of recursion O(N), additional list O(N)
    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) return new ArrayList<>();
        String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> letters = new ArrayList<>(),
                res = new ArrayList<>();

        for (int i = 0; i < digits.length(); i++)
            letters.add(map[digits.charAt(i) - '0']);

        combinations(letters, 0, new StringBuilder(), res);
        return res;
    }

    private void combinations(List<String> letters, int ind, StringBuilder sb, List<String> res) {
        if(ind >= letters.size()) {
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < letters.get(ind).length(); i++) {
            sb.append(letters.get(ind).charAt(i));
            combinations(letters, ind + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    // Not mine iterative solution
    // https://leetcode.com/problems/letter-combinations-of-a-phone-number/discuss/8064/My-java-solution-with-FIFO-queue
    // They process first digit and then go to second + not use StringBuilder
    public List<String> letterCombinations2222(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while(ans.peek().length()!=digits.length()){  // while top element lenght is not the same as number of digits
            String remove = ans.remove(); // remove from the beginning O(1) for linked list
            String map = mapping[digits.charAt(remove.length())-'0'];
            for(char c: map.toCharArray()){
                ans.addLast(remove+c);
            }
        }
        return ans;
    }

    // Other good recursive solution, without string builder
    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public List<String> letterCombinations3333(String digits) {
        List<String> ret = new LinkedList<String>();
        combination("", digits, 0, ret);
        return ret;
    }

    private void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset >= digits.length()) {
            ret.add(prefix);
            return;
        }
        String letters = KEYS[(digits.charAt(offset) - '0')];
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, offset + 1, ret);
        }
    }
}
