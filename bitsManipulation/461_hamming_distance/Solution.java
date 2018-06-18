/*
461. Hamming Distance
https://leetcode.com/problems/hamming-distance/#/description
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
Given two integers x and y, calculate the Hamming distance.
Note:
0 ≤ x, y < 231.
Example:
Input: x = 1, y = 4
Output: 2
Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
*/
// Integer.bitCount(x ^ y);
// https://discuss.leetcode.com/topic/72093/java-1-line-solution-d/2
// https://discuss.leetcode.com/topic/72089/java-3-line-solution

class Solution {
    // mine solution
    public int hammingDistance(int x, int y) {
        int result = x ^ y;
        return Integer.bitCount(result);
    }


    // leet code solution, cool count
    public int hammingDistance2222(int x, int y) {
        int xor = x ^ y, count = 0;
        for (int i=0;i<32;i++) count += (xor >> i) & 1;
        return count;
    }
}