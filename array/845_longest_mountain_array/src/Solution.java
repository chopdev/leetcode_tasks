import java.util.ArrayList;
import java.util.List;

/**
 * You may recall that an array arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 *
 * Input: arr = [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 104
 *
 *
 * Follow up:
 *
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 * */

public class Solution {

    /*
        My not working solution
        it will consider  0 1 2 3 as a mountain
    */
    public int longestMountain(int[] arr) {
        int maxMount = 0;
        List<Integer> mountain = new ArrayList<>();
        for (int i =0; i <arr.length - 2; i++) {
            mountain = new ArrayList<>();
            mountain.add(arr[i]);
            mountain.add(arr[i + 1]);
            mountain.add(arr[i + 2]);

            while (isMountain(mountain)) {
                maxMount = Math.max(maxMount, mountain.size());
                int nextIndex = i + mountain.size();
                if(nextIndex >= arr.length) {
                    break;
                }
                mountain.add(arr[nextIndex]);
            }

            if (mountain.size() != 3)
                i += mountain.size() - 1;
        }

        return maxMount;
    }

    public boolean isMountain(List<Integer> sub) {
        if (sub.size() < 3)
            return false;

        boolean goingUp = true;
        int curr = sub.get(0), next = 0;
        for(int i=1; i < sub.size(); i++) {
            next = sub.get(i);

            if (next == curr)
                return false;

            if (goingUp) {
                if (curr > next)
                    goingUp = false;

            } else {
                if (curr < next)
                    return false;
            }

            curr = next;
        }

        return true;
    }



    /**
     * Not mine solution
     * https://leetcode.com/problems/longest-mountain-in-array/solution/
     *
     * Time Complexity: O(N), where N is the length of A.
     * Space Complexity: O(1).
     * */
    public int longestMountain2222(int[] A) {
        int N = A.length;
        int ans = 0, base = 0;
        while (base < N) {
            int end = base;
            // if base is a left-boundary
            if (end + 1 < N && A[end] < A[end + 1]) {
                // set end to the peak of this potential mountain
                while (end + 1 < N && A[end] < A[end + 1]) end++;

                // if end is really a peak..
                if (end + 1 < N && A[end] > A[end + 1]) {
                    // set end to the right-boundary of mountain
                    while (end + 1 < N && A[end] > A[end + 1]) end++;
                    // record candidate answer
                    ans = Math.max(ans, end - base + 1);
                }
            }

            base = Math.max(end, base + 1);
        }

        return ans;
    }


    /**
     * Not mine
     * https://leetcode.com/problems/longest-mountain-in-array/discuss/937617/Java-Short-one-pass-O(N)-time-O(1)-space-with-comments
     * */
    public int longestMountain3333(int[] A) {
        int max = 0, inc = 0, dec = 0;

        for(int i=1; i<A.length; i++){
            if(A[i] > A[i-1]){       // Current number greater than the previous
                if(dec > 0) inc = 0; // Reset inc if we had a decreasing sequence until the previous
                inc++;               // Increment inc
                dec = 0;             // Reset dec
            }
            else if(A[i] < A[i-1]){  // Current number smaller than the previous
                if(inc > 0){         // No need to do anything if we did not have an increasing sequence
                    dec++;           // Increment dec
                    max = Math.max(max, inc + dec + 1);  // Determine max for the current mountain
                }
            }
            else inc = dec = 0;      // Current number same as the previous, reset inc and dec
        }

        return max;
    }
}
