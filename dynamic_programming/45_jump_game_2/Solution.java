/**
 45. Jump Game II
 https://leetcode.com/problems/jump-game-ii/

 Given an array of non-negative integers, you are initially positioned at the first index of the array.
 Each element in the array represents your maximum jump length at that position.
 Your goal is to reach the last index in the minimum number of jumps.

 Example:

 Input: [2,3,1,1,4]
 Output: 2
 Explanation: The minimum number of jumps to reach the last index is 2.
 Jump 1 step from index 0 to 1, then 3 steps to the last index.

 Note:

 You can assume that you can always reach the last index.

* */
public class Solution {

    // My solution
    // O(N) time, but it's much slower than greedy solution, maybe I'm wrong here
    public int jump(int[] nums) {
        int[] memo = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            memo[i] = Integer.MAX_VALUE;
        }

        return getNumberOfJumps(nums, 0, memo);
    }

    private int getNumberOfJumps(int[] nums, int curr, int[] memo) {
        if(curr >= nums.length - 1) return 0;
        if(memo[curr] != Integer.MAX_VALUE) return memo[curr];

        for (int i = nums[curr]; i >= 1; i--) {
            int res = getNumberOfJumps(nums, curr + i, memo);
            if(res == Integer.MAX_VALUE) continue; // that's mean that from curr + i we can't reach the end, like from index 2: [2,3,0,1,4]
            memo[curr] = Math.min(memo[curr], res + 1);
        }

        return memo[curr];
    }

    // Not mine greedy solution
    // O(N) time
    //https://leetcode.com/problems/jump-game-ii/discuss/18014/Concise-O(n)-one-loop-JAVA-solution-based-on-Greedy
    // in this example regions are [3, 4, 1, 1, 1, 1, 1, 5]
    // 3   4, 1, 1      1, 1     1
    public int jump2222(int[] nums) {
        int jumps = 0, curEnd = 0, currMax = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            currMax = Math.max(currMax, i + nums[i]); // We should know how far can we reach in current region
            if (i == curEnd) {
                jumps++;
                curEnd = currMax;
            }
        }
        return jumps;
    }
}
