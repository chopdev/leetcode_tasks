/**
 338. Counting Bits
 Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

 Example 1:

 Input: 2
 Output: [0,1,1]

 Example 2:

 Input: 5
 Output: [0,1,1,2,1,2]


 Follow up:

 - It is very easy to come up with a solution with run time O(n*sizeof(integer)).
    But can you do it in linear time O(n) /possibly in a single pass?
 - Space complexity should be O(n).
 - Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in
    any other language.
* */
public class Solution {

    // My solution O(n*sizeof(integer))=O(N*log32) = O(N)
    public int[] countBits(int num) {
        int[] counts = new int[num+1];
        counts[0] = 0;
        for(int i=1;i<=num;i++) {
            counts[i] += getCount(i);
        }
        return counts;
    }

    private int getCount(int n) {
        int count = 0;
        for(int i=0; i<32; i++) {
            count += (n>>i) & 1;
        }
        return count;
    }


    // Not mine solution O(N) time
    // Great, classic DP solution.
    // First, think about the recursive way to solve it:
    // the total number of 1 bits = the last bit is 1 ? 1 : 0 + the number of the prefix 1 bits(exclude the last bit)
    // Then come up the DP solution, the last bit is i & 1, the number of prefix bits is represented by dp[i >> 1] and the base case dp[0] = 0.
    public int[] countBits2222(int num) {
        int[] f = new int[num + 1];
        for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);  // last bit is 1 ? 1 : 0   + count of prefix bits
        return f;
    }
}
