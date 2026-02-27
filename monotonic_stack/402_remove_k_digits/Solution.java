/*
402. Remove K Digits
https://leetcode.com/problems/remove-k-digits/description/
*/

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";
        Deque<Integer> incStack = new ArrayDeque<>();  
        Set<Integer> toRemove = new HashSet<>();

        for (int i = 0; i <= num.length(); i ++) {
            int curr = i == num.length() ? -1 : getInt(num, i); // to flush last item

            while (!incStack.isEmpty() && curr < getInt(num, incStack.peek())) {
                toRemove.add(incStack.pop());
                if (toRemove.size() == k) break;
            }
            if (toRemove.size() == k) break;
            incStack.push(i);
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < num.length(); i ++) {
            if (toRemove.contains(i)) continue;
            if (res.isEmpty() && num.charAt(i) == '0') continue;

            res.append(num.charAt(i));
        }

        return res.isEmpty() ? "0" : res.toString();
    }

    public int getInt(String num, int i) {
        return num.charAt(i) - '0';
    }


    // canonical solution
    /*
    Whenever you see a digit that is smaller than the previous one, it’s beneficial to remove that previous larger digit, because:
The earlier a digit is in the number, the more it contributes to the value.

Replacing a big digit at a high position with a smaller one gives the biggest decrease.
So you scan left-to-right and maintain a stack of digits that is non-decreasing (monotonic increasing).
*/
    public String removeKdigits22222(String num, int k) {
        int n = num.length();
        if (k >= n) return "0";

        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);

            // Pop bigger digits while we still can remove digits
            while (!stack.isEmpty() && k > 0 && stack.peekLast() > c) {
                stack.pollLast();
                k--;
            }

            stack.addLast(c);
        }

        // If we still have k > 0, remove from the end (largest impact is earlier, so leftover removals are from right)
        while (k > 0 && !stack.isEmpty()) {
            stack.pollLast();
            k--;
        }

        // Build the result and strip leading zeros
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (char c : stack) {
            if (leadingZero && c == '0') {
                continue;
            }
            leadingZero = false;
            sb.append(c);
        }

        // If everything was zero or stripped out
        if (sb.length() == 0) return "0";

        return sb.toString();
    }

}
