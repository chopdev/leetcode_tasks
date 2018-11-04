import java.util.Arrays;

/**
 https://leetcode.com/problems/increasing-triplet-subsequence/description/
 334. Increasing Triplet Subsequence

 Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 Formally the function should:

 Return true if there exists i, j, k
 such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.

 Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

 Example 1:
 Input: [1,2,3,4,5]
 Output: true

 Example 2:
 Input: [5,4,3,2,1]
 Output: false
* */
public class Solution {

    // Wrote it after looking on 300 longest increasing subsequence, O(N^2) solution
    public boolean increasingTriplet(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++)
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j] && dp[i] <= dp[j]) dp[i] = dp[j] + 1;
                if(dp[i] >= 3) return true;
            }

        return false;
    }

    // Not mine cool solution
    // O(N) time, O(1) space
    // https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79004/Concise-Java-solution-with-comments.
    public boolean increasingTriplet2222(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;

        for (int numb : nums) {
            if(numb <= first) first = numb;  // remember first two numbers of sequence
            else if(numb <= second) second = numb;  // second will be always bigger than first
            else return true;  // if there is a number that is bigger than first and second, we have triplet increasing sequance
        }
        return false;
    }
}
