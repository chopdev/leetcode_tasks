/**
 978. Longest Turbulent Subarray
 https://leetcode.com/problems/longest-turbulent-subarray/

 A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

 For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

 Return the length of a maximum size turbulent subarray of A.


 Example 1:

 Input: [9,4,2,10,7,8,8,1,9]
 Output: 5
 Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])


 Example 2:

 Input: [4,8,12,16]
 Output: 2


 Example 3:

 Input: [100]
 Output: 1


 Note:

 1 <= A.length <= 40000
 0 <= A[i] <= 10^9

 * */
public class Solution {


    // My good solution with 2 pointers
    // O(N) time, O(1) space
    public int maxTurbulenceSize(int[] A) {
        if(A.length <= 1) return A.length;
        int max1 = longestCombintation(A, true);
        int max2 = longestCombintation(A, false);
        return max1 > max2 ? max1 : max2;
    }

    private int longestCombintation(int[] arr, boolean bigger) {
        int first = 0, last = 1, max = 0;
        while (last < arr.length) {
            while (last < arr.length && arr[last] != arr[last - 1] &&
                    (arr[last] > arr[last - 1]) == bigger) {
                bigger = !bigger;
                last++;
            }
            max = Math.max(max, last - first);
            first = last;
            last++;
            bigger = !bigger;
        }
        return max;
    }


    // Not mine cool solution
    // O(N) time with one pass
    // https://leetcode.com/problems/longest-turbulent-subarray/discuss/221935/Java-O(N)-time-O(1)-space
    public int maxTurbulenceSize2222(int[] A) {
        int inc = 1, dec = 1, result = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                dec = inc + 1;
                inc = 1;
            } else if (A[i] > A[i - 1]) {
                inc = dec + 1;
                dec = 1;
            } else {
                inc = 1;
                dec = 1;
            }
            result = Math.max(result, Math.max(dec, inc));
        }
        return result;
    }
}
