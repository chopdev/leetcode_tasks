import java.util.Arrays;

/*
646. Maximum Length of Pair Chain
https://leetcode.com/problems/maximum-length-of-pair-chain/

You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in
 this fashion.
Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs.
 You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]

Note:
The number of given pairs will be in the range [1, 1000].

* */

public class Solution {

    // My not working solution
    // for this case [[1,2], [2,3], [3,4]]
    public int findLongestChain(int[][] pairs) {
        if(pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int[] dp = new int[pairs.length];
        dp[0] = 1;
        for (int i = 1; i < pairs.length; i++) {
            if(pairs[i][0] > pairs[i - 1][1])
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = dp[i - 1];
        }

        return dp[pairs.length - 1];
    }


    // Mine working solution
    // O(N^2) time, O(N) space
    public int findLongestChain2222(int[][] pairs) {
        if(pairs == null || pairs.length == 0) return 0;
        Arrays.sort(pairs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int[] dp = new int[pairs.length];
        dp[0] = 1;
        for (int i = 1; i < pairs.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(pairs[i][0] > pairs[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 1;
        for (int i = 0; i < dp.length; i++)
            max = Math.max(max, dp[i]);

        return max;
    }

    // Not mine O(N*logN) time O(logN) space solution - because of sorting
    public int findLongestChain3333(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> Integer.compare(o1[1], o2[1])); // important to sort by 2 value
        int cur = Integer.MIN_VALUE; // remember the smallest second number
        int res = 0;
        for(int[] pair : pairs) {
            if(cur < pair[0]) {
                cur = pair[1];
                res++;
            }
        }
        return res;
    }
}
