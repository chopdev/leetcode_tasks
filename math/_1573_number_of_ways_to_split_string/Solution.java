/**
 * 1573. Number of Ways to Split a String
 * https://leetcode.com/problems/number-of-ways-to-split-a-string/
 *
 * Given a binary string s, you can split s into 3 non-empty strings s1, s2, and s3 where s1 + s2 + s3 = s.
 *
 * Return the number of ways s can be split such that the number of ones is the same in s1, s2, and s3. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
 * "1|010|1"
 * "1|01|01"
 * "10|10|1"
 * "10|1|01"
 *
 *
 * Example 2:
 *
 * Input: s = "1001"
 * Output: 0
 * Example 3:
 *
 * Input: s = "0000"
 *
 *
 * Output: 3
 * Explanation: There are three ways to split s in 3 parts.
 * "0|0|00"
 * "0|00|0"
 * "00|0|0"
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 105
 * s[i] is either '0' or '1'.
 * */
public class Solution {
    /**
     * Exapmple: 100101
     *
     * 1 001 01
     * 1 0010 1
     * 10 01 01
     * 10 010 1
     * 100 1 01
     * 100 10 1
     *
     *
     * Exapmple "1000101"
     *
     * 1000 1 01
     * 1000 10 1
     *
     * 100 01 01
     * 100 010 1
     *
     * 10 0010 1
     * 10 001 01
     *
     * 1 00010 1
     * 1 0001 01
     *
     *
     *
     * Example "00000"
     * 0 0000 0
     * 00 000 0
     * 000 00 0
     * 0000 0 0
     * 0 000 00
     * 0 00 000
     * 0 0 0000
     *
     * Basically ones should be divided on 3 parts, zeros between once in each of the segment are fixed. Because we cannot transfer
     * 1 to another side. Eg. "101|0000|10001|000|11"  101 is fixed thing as well as 10001 and 11.
     * We can move 0 to either one side or another between the segments. Count of zeros between the segments * 2 is an answer to the question
     *
     *
     * Failing
     * "1001001"
     * */
    public int numWays(String s) {
        int countOfOne = 0;
        for (Character ch : s.toCharArray()) {
            if (ch == '1') countOfOne++;
        }

        if (countOfOne % 3 != 0) return 0; // invalid state, we cannot split string of 3 blocks
        if (countOfOne == 0) return (s.length() - 2) * 2 - 1; // case with 0 only, remove two side 0 and -1 for center in the end
        if (countOfOne == s.length()) return 1;

        int countOfOneInSegment = countOfOne / 3;
        int currCount = 0;
        int zeroCountBetweenBlocks = 0;
        for (Character ch : s.toCharArray()) {
            if (countOfOneInSegment == currCount && ch == '1')
                currCount = 0;

            if (ch == '1') currCount++;

            if (countOfOneInSegment == currCount && ch == '0')
                zeroCountBetweenBlocks ++;
        }

        return zeroCountBetweenBlocks * 2;
    }
}
