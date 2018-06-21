/*
190. Reverse Bits
https://leetcode.com/problems/reverse-bits/
Reverse bits of a given 32 bits unsigned integer.
For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
return 964176192 (represented in binary as 00111001011110000010100101000000).
Follow up:
If this function is called many times, how would you optimize it?
*/
// Good explanation
// https://discuss.leetcode.com/topic/42572/sharing-my-2ms-java-solution-with-explanation
// https://discuss.leetcode.com/topic/9764/java-solution-and-optimization
public class Solution {

    public int reverseBits(int n) {
        System.out.println(Integer.toBinaryString(n));
        int res = 0;
        for (int i = 0; i < 32; i++) {
            System.out.println();
            System.out.println(String.format("n >> %d:", i) + Integer.toBinaryString(n>>i));

            res = res << 1;
            res += 1 & (n>>i);

            System.out.println(Integer.toBinaryString(res));
        }

        return  res;
    }
}