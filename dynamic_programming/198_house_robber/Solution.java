import java.util.Arrays;

/**
 https://leetcode.com/problems/house-robber/description/
 198. House Robber

 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:

 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.
 Example 2:

 Input: [2,7,9,3,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.

 Mine example:
 10 1 10 20 1
 Total: 30


 SOLUTION:
 https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.

 IMPORTANT:

 When you have DP problem, you could come up with recursive up-down solution, or iterative bottom-up
 So steps:
 1) Try to understand recursive solution for K element of an array. (we could take element and add it to sum or skip it and take next)
 2) Try to remake it on up-down recursion, beginning from the end
 3) Apply DP

* */
public class Solution {

    // Mine working, but slow solution O(2^N)
    // Recursive bottom-up solution
    // We can't use memoization here, because we path state from the bottom to end
    public int rob(int[] nums) {
        return rob(nums, 0, 0);
    }

    private int rob(int[] nums, int index, int sum) {
        if(index >= nums.length)
            return sum;

        int res1 = rob(nums, index + 2, sum + nums[index]);
        int res2 = rob(nums, index + 1, sum);

        return Math.max(res1, res2);
    }


    // Not mine up-down solution
    // Could apply dp here
    // https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
    // O(2^N) time
    public int rob2222(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        return rob2222(nums, nums.length - 1);
    }

    private int rob2222(int[] nums, int index) {
        if(index < 0) return 0;

        int res1 = rob2222(nums, index - 2) + nums[index];
        int res2 = rob2222(nums, index - 1);

        return Math.max(res1, res2);
    }

    // Not mine up-down solution with memoization
    // https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
    // O(N) time
    public int rob3333(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return rob3333(nums, nums.length - 1, memo);
    }

    private int rob3333(int[] nums, int index, int[] memo ) {
        if(index < 0) return 0;
        if(memo[index] != -1) return memo[index];

        int res1 = rob3333(nums, index - 2, memo) + nums[index];
        int res2 = rob3333(nums, index - 1, memo);

        memo[index] = Math.max(res1, res2);
        return memo[index];
    }
}
