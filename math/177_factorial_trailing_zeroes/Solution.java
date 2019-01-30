/**
 172. Factorial Trailing Zeroes
 https://leetcode.com/problems/factorial-trailing-zeroes/

 Given an integer n, return the number of trailing zeroes in n!.

 Example 1:

 Input: 3
 Output: 0
 Explanation: 3! = 6, no trailing zero.
 Example 2:

 Input: 5
 Output: 1
 Explanation: 5! = 120, one trailing zero.


 Note: Your solution should be in logarithmic time complexity.

 */
public class Solution {

    // my linear solution
    public int trailingZeroes(int n) {
        long fact = 1; // get overflow here
        for(int i = 1; i <= n; i++) {
            fact *= i;
        }
        int count = 0;
        while(fact % 10 == 0) {
            count ++;
            fact /= 10;
        }

        return count;
    }

    // my linear solution
    public int trailingZeroes2222(int n) {
        long fact = 1;   // get overflow here on n=30
        int count = 0;
        for(int i = 1; i <= n; i++) {
            fact *= i;
            while (fact % 10 == 0) {
                count ++;
                fact /= 10;
            }
        }

        return count;
    }

    // https://leetcode.com/problems/factorial-trailing-zeroes/discuss/52432/3-lines-of-Java-O(logn)-time-O(1)-space
    // Because all trailing 0 is from factors 5 * 2.
    // But sometimes one number may have several 5 factors, for example, 25 have two 5 factors,
    // 125 have three 5 factors. In the n! operation, factors 2 is always ample.
    // So we just count how many 5 factors in all number from 1 to n.
    // If n=30, we would have 5, 10, 15, 20, 25, 30 of 5 factors => 1 + 1 + 1 +1 +2+1 = 7 of trailing zeroes
    // if n=7 , we would have only one 5, so 1 zero
    // Time complexity is O(logN), because we divide our input data on each iteration
    public int trailingZeroes3333(int n) {
        int count = 0;
        while (n>0)
            count += (n/=5);
        return count;
    }

    // https://leetcode.com/problems/factorial-trailing-zeroes/discuss/52371/My-one-line-solutions-in-3-languages
    public int trailingZeroes4444(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes4444(n / 5);
    }
}
