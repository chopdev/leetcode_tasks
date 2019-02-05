/**
 326. Power of Three
 https://leetcode.com/problems/power-of-three/

 Given an integer, write a function to determine if it is a power of three.

 Example 1:

 Input: 27
 Output: true
 Example 2:

 Input: 0
 Output: false
 Example 3:

 Input: 9
 Output: true
 Example 4:

 Input: 45
 Output: false


 Follow up:
 Could you do it without using any loop / recursion?

 SOLUTIONS
 https://leetcode.com/articles/power-of-three/
 */
public class Solution {

    // My solution
    //  O(logN) - because each time we divide our input data
    // 1 is also power of 3
    // negative numbers couldn't be power of three
    public boolean isPowerOfThree(int n) {
        if(n <= 0 || (n & 1) != 1) return false;

        while(n > 1) {
            if(n % 3 != 0) return false;
            n /= 3;
        }

        return true;
    }

    // Not mine O(logN), cleaner solution
    public boolean isPowerOfThree2222(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    //https://leetcode.com/problems/power-of-three/discuss/77876/**-A-summary-of-all-solutions-(new-method-included-at-15%3A30pm-Jan-8th)
    public boolean isPowerOfThree4444(int n) {
        if(n>1)
            while(n%3==0) n /= 3;
        return n==1;
    }

    // Not mine
    // O(1) solution
    // approach 4:  https://leetcode.com/articles/power-of-three/
    // 1162261467 - is a maximum power of 3 that less than Integer.MAX_VALUE
    // 3^(log3(Integer.MAX_VALUE)) = 3^19.56= 3^19 = 1162261467
    public boolean isPowerOfThree3333(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
