/**
 693. Binary Number with Alternating Bits
 https://leetcode.com/problems/binary-number-with-alternating-bits/

 Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

 Example 1:
 Input: 5
 Output: True
 Explanation:
 The binary representation of 5 is: 101


 Example 2:

 Input: 7
 Output: False
 Explanation:
 The binary representation of 7 is: 111.


 Example 3:

 Input: 11
 Output: False
 Explanation:
 The binary representation of 11 is: 1011.


 Example 4:

 Input: 10
 Output: True
 Explanation:
 The binary representation of 10 is: 1010.
 */
public class Solution {

    // My O(1) solution
    public boolean hasAlternatingBits(int n) {
        int i = 31;
        for (; i>= 0; i--)
            if(((n>>i) & 1) == 1) break;  // detect first 1 from right

        int curr = 1;
        for (; i>= 0; i--) {
            if(((n>>i) & 1) != curr) return false;  // check if the bit is opposite to out curr value
            curr ^= 1;     // switch curr value to opposite
        }

        return true;
    }


    // Cool solution
    // https://leetcode.com/problems/binary-number-with-alternating-bits/discuss/113933/Java-super-simple-explanation-with-inline-example
    boolean hasAlternatingBits2222(int n) {
        /*
        n =         1 0 1 0 1 0 1 0
        n >> 1      0 1 0 1 0 1 0 1
        n ^ n>>1    1 1 1 1 1 1 1 1
        n           1 1 1 1 1 1 1 1
        n + 1     1 0 0 0 0 0 0 0 0
        n & (n+1)   0 0 0 0 0 0 0 0
        */

        n = n ^ (n>>1);   // n ^ (n>>1) should give all ones followed by zeroes, like that 0000011111
        return (n & n+1) == 0;
    }



    /*
    https://leetcode.com/problems/binary-number-with-alternating-bits/discuss/108427/Oneliners-(C%2B%2B-Java-Ruby-Python)
    Xor the number with itself shifted right twice and check whether everything after the leading 1-bit became/stayed 0.
     Xor is 0 iff the bits are equal, so we get 0-bits iff the pair of leading 1-bit and the 0-bit in front of it are
     repeated until the end.

       n =
        000101010
      ^ 000001010
      = 000100000

      return
      000100000  & 000011111 == 0
    * */
    boolean hasAlternatingBits3333(int n) {
        // divide by 2 is the same as >>1
        return ((n ^= n/4) & n-1) == 0;
    }
}
