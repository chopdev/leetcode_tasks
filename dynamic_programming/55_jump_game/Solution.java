/**
 55. Jump Game
 https://leetcode.com/problems/jump-game/

 Given an array of non-negative integers, you are initially positioned at the first index of the array.
 Each element in the array represents your maximum jump length at that position.
 Determine if you are able to reach the last index.

 Example 1:

 Input: [2,3,1,1,4]
 Output: true
 Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

 Example 2:

 Input: [3,2,1,0,4]
 Output: false
 Explanation: You will always arrive at index 3 no matter what. Its maximum
 jump length is 0, which makes it impossible to reach the last index.

 SIMILAR PROBLEM was overvied here
 https://www.youtube.com/watch?v=kHWy5nEfRIQ&t=549s
 
 * */
public class Solution {

    // My solution
    // Using DP
    // Time O(N), space O(N)
    // Actually, we are doing DFS here
    public boolean canJump(int[] nums) {
        return canJump(nums, 0, new Boolean[nums.length]);
    }

    private boolean canJump(int[] nums, int curr, Boolean[] memo) {
        if(curr >= nums.length - 1) return true;
        if(memo[curr] != null) return memo[curr];

        for (int i = nums[curr]; i >= 1; i--) {
            if(canJump(nums, curr + i, memo)) {
                memo[curr] = true;
                return true;
            }
        }

        memo[curr] = false;
        return false;
    }

    // My solution
    // using DP
    // Starting from pre last index check if we can get to the end or
    // if we can get to dp[i+j], from which we can reach the end
    public boolean canJump2222(int[] nums) {
        if(nums.length <= 1) return true;
        boolean[] dp = new boolean[nums.length];
        int last = nums.length - 1;

        for(int i = last - 1; i>= 0; i--) {
            for (int j = nums[i]; j >= 1 ; j--) {
                if(i+j >= last || dp[i+j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    // Not mine clever solution
    // It is similar to mine DP, but here we move lastIndex pointer to the beginning
    // Idea is to work backwards from the last index. Keep track of the smallest index that can "jump" to the last index.
    // Check whether the current index can jump to this smallest index.
    public boolean canJump3333(int[] nums) {
        int last=nums.length - 1;
        for(int i=last - 1; i>=0; i--){
            if(i+nums[i]>=last)last=i;
        }
        return last<=0;
    }
}
