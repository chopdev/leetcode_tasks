/**
 * Insertion: You are given two 32-bit numbers, Nand M, and two bit positions, i and
 * j. Write a method to insert Minto N such that M starts at bit j and ends at bit i. You
 * can assume that the bits j through i have enough space to fit all of M. That is, if
 * M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for
 * example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 *
 * N=10000000000, M=1011, i=2 j=6
 * Output N = 10001001100
 *
 *  N = 1111 1111
 *  M = 1000 1001
 *  i=4 j=8
 *  result N = 1001 1111
 * */
public class Solution {

    // Mine solution O(1)
    public int insert(int N, int M, int i, int j) {
        if(j<i || i> 32 || j> 32 || i <0 || j<0) return 0;
        if(j==i) return N;
        if(j-i == 32) return M;

        int count = j-i; // count of bits need to copy
        int mask = (1<<count) - 1; // get the mask like 1<<4 - 1  = 0000 1111
        int insert = (M & mask) << i; // the value need to insert shifted on i position
        int clear = ~(mask << i); // clear bits for N from i to j
        N = (N & clear) | insert; // clear bits in N and then set values of M
        return N;
    }
}
