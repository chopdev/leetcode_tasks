/**
 https://leetcode.com/problems/sqrtx/description/
 69. Sqrt(x)

 Implement int sqrt(int x).

 Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

 Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

 Example 1:

 Input: 4
 Output: 2


 Example 2:

 Input: 8
 Output: 2

 Explanation: The square root of 8 is 2.82842..., and since
 the decimal part is truncated, 2 is returned.
 */
public class Solution {

    // O(logX)
    // My solution Time Limit Exceeded
    // we are looking for critical point  where left^2 <= x < right^2  like   9 <= 15 < 16,  9<=9 < 16
    public int mySqrt(int x) {
        if(x <= 1) return x;
        int left = 0, right = x, middle = -1;

        while (left <= right) {
            middle = (left + right) / 2;
            int product = middle * middle;
            int productNext = (middle + 1) * (middle + 1);
            if(product == x || x > product && x < productNext) return middle;
            if(product > x)
                right = middle - 1;
            else
                left = middle + 1;
        }

        return middle;
    }

    // Not mine O(logX)
    // Solution is the same, but uses dividing - not product and as result we work with less numbers
    // https://leetcode.com/problems/sqrtx/discuss/25198/3-JAVA-solutions-with-explanation
    public int mySqrt2222(int x) {
        if(x <= 1) return x;
        int left = 0, right = x, middle = -1;

        while (left <= right) {
            middle = (left + right) / 2;
            if(x / middle >= middle && x / (middle + 1) < (middle + 1) ) return middle;
            if(middle > x / middle)
                right = middle - 1;
            else
                left = middle + 1;
        }

        return middle;
    }
}
