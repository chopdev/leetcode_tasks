/**
 413. Arithmetic Slices
 https://leetcode.com/problems/arithmetic-slices/

 A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

 For example, these are arithmetic sequence:

 1, 3, 5, 7, 9
 7, 7, 7, 7
 3, -1, -5, -9
 The following sequence is not arithmetic.

 1, 1, 2, 5, 7

 A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

 A slice (P, Q) of array A is called arithmetic if the sequence:
 A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

 The function should return the number of arithmetic slices in the array A.


 Example:

 A = [1, 2, 3, 4]

 return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */
public class Solution {

    // My two pointer solution O(N^2) time, O(1) space
    // the idea is to move starting point and extend the range
    // if the range is correct - increase count
    // if not move to next starting point
    // did from the 2 time when considered bigger example
    // [1,2,3,4,5,6]
    // 1,2,3   1,2,3,4   1,2,3,4,5  1,2,3,4,5,6
    // 2,3,4   2,3,4,5  2,3,4,5,6
    // 3,4,5   3,4,5,6
    // 4,5,6
    public int numberOfArithmeticSlices(int[] A) {
        if(A == null || A.length < 3) return 0;

        int end = 0, diff = 0, res = 0;
        for (int i = 0; i < A.length - 2; i++) {
            diff = A[i+1] - A[i];
            end = i + 2;
            while (end < A.length && A[end] - A[end - 1] == diff) {
                res++;
                end++;
            }
        }

        return res;
    }

    // My  NOT WORKING solution (1 approach, tried to do it with 2 pointers - extend anc count, than shorthand adn count)
    // the problem is with determination of next start point
    public int numberOfArithmeticSlices2222(int[] A) {
        if(A == null || A.length < 3) return 0;

        int start = 0, end = 1, diff = 0, res = 0;
        while (end < A.length) {
            diff = A[end] - A[start];
            while (end < A.length) { // extend
                end++;
                if(end >= A.length || A[end] - A[end - 1] != diff) {
                    end--;
                    break;
                }

                res++;
            }

            while (end - start > 2) {
                start++;
                res++;
            }

            end++;
            start = end - 1;
        }

        return res;
    }

    // Great explanation of DP, O(N) time and space
    //https://leetcode.com/problems/arithmetic-slices/discuss/215861/Detailed-Explanation%3A-Two-DP-Solutions
    // assume dp[i] is the number of arithmetic slices which are end with A[i]. then we have:
    // dp[i]=(A[i]-A[i-1] == A[i-1]-A[i-2])? 1+dp[i-1] : 0, the code:
    public int numberOfArithmeticSlices3333(int[] A) {
        int n=A.length;
        if(n<3) return 0;
        int[] dp=new int[n];
        dp[0]=0;
        dp[1]=0;
        int sum=0;
        for(int i=2;i<n;i++){
            if((A[i]-A[i-1])==(A[i-1]-A[i-2])){
                dp[i]=dp[i-1]+1;
            }else{
                dp[i]=0;
            }
            sum+=dp[i];
        }
        return sum;
    }

    // Not mine, similar solution but with one loop O(N) time
    // up to now, time complexity is O(n), but space complexity is also O(n). In fact, we only need a curr to memorize
    // the num of arithmetic slices which end with current A[i] and a sum to memorize num of all curr.
    // that is @icl7722's solution, which time complexity is O(n) and space complexity is O(1).
    // https://leetcode.com/problems/arithmetic-slices/discuss/90058/Simple-Java-solution-9-lines-2ms
    public int numberOfArithmeticSlices4444(int[] A) {
        int curr = 0, sum = 0;
        for (int i=2; i<A.length; i++)
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }
}
