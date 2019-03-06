import java.util.Arrays;

/**
 945. Minimum Increment to Make Array Unique
 https://leetcode.com/problems/minimum-increment-to-make-array-unique/

 Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
 Return the least number of moves to make every value in A unique.


 Example 1:

 Input: [1,2,2]
 Output: 1
 Explanation:  After 1 move, the array could be [1, 2, 3].

 Example 2:

 Input: [3,2,1,2,1,7]
 Output: 6
 Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].

 It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

 Note:

 0 <= A.length <= 40000
 0 <= A[i] < 40000
 * */
public class Solution {

    // My solution O(N*logN) time, O(logN) space
    public int minIncrementForUnique(int[] A) {
        if(A == null || A.length == 0) return 0;
        Arrays.sort(A);
        int min = A[0];
        int count = 0;

        for (int i = 1; i < A.length; i++) {
            while (A[i] <= min) {
                A[i]++;
                count++;
            }
            min = A[i];
        }
        return count;
    }

    // Not mine, but idea the same
    public int minIncrementForUnique2222(int[] A) {
        Arrays.sort(A);
        int res = 0, need = 0;
        for (int a : A) {
            res += Math.max(need - a, 0);
            need = Math.max(a, need) + 1;
        }
        return res;
    }
}
