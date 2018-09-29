/*
*Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
* Example 1:
* Input: a = 1, b = 2
Output: 3

Example 2:
Input: a = -2, b = 3
Output: 1

EXPLANATION here: https://leetcode.com/problems/sum-of-two-integers/discuss/84290/Java-simple-easy-understand-solution-with-explanation

* */
public class Solution {
    // Not mine solution
    // This will also work with negative numbers or one negative
    public int getSum(int a, int b) {
        int shift = 0;
        while (b != 0) {
            shift = (a&b) << 1;
            a = a ^ b;
            b = shift;
        }

        return a;
    }


    public int getSubtract(int a, int b) {
        while (b != 0) {
            int borrow = (~a) & b;
            a = a ^ b;
            b = borrow << 1;
        }

        return a;
    }


    public int getSumRecursive(int a, int b) {
        return (b == 0) ? a : getSumRecursive(a ^ b, (a & b) << 1);
    }

    public int getSubtractRecursive(int a, int b) {
        return (b == 0) ? a : getSubtractRecursive(a ^ b, (~a & b) << 1);
    }

    // Get negative number
    public int negate(int x) {
        return ~x + 1;
    }
}
