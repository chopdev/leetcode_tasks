/**
 486. Predict the Winner
 https://leetcode.com/problems/predict-the-winner/


 You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
 Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0.
 At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1])
 which reduces the size of the array by 1. The player adds the chosen number to their score. The game ends when there are no more elements in the array.
 Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner,
 and you should also return true. You may assume that both players are playing optimally.



 Example 1:

 Input: nums = [1,5,2]
 Output: false
 Explanation: Initially, player 1 can choose between 1 and 2.
 If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 Hence, player 1 will never be the winner and you need to return false.


 Example 2:

 Input: nums = [1,5,233,7]
 Output: true
 Explanation: Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.


 Constraints:

 1 <= nums.length <= 20
 0 <= nums[i] <= 107
 * */


public class Solution {

    // [1,5,2]
    // My initial incorrect solution. I was struggling to understand what is "best choice" of player 2
    // Question I would ask: Player two always greedely takes the best option? How to determine he is playing "optimally"?
    public boolean PredictTheWinner2222(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        int firstScore = getFirtsScore(nums, 0, nums.length - 1, true, 0);
        int secondScore = sum - firstScore;
        return firstScore >= secondScore;
    }

    public int getFirtsScore(int[] nums, int i, int j, boolean firstTakes, int score) {
        if (i > j) {
            return score;
        }

        if (firstTakes) {
            int left = getFirtsScore(nums, i + 1, j, false,score + nums[i]);
            int rigth = getFirtsScore(nums, i, j - 1, false,score + nums[j]);
            return left > rigth ? left : rigth;
        } else {
            if (nums[i] >= nums[j]) return getFirtsScore(nums, i+1, j, true, score);
            else return getFirtsScore(nums, i, j - 1, true, score);
        }
    }



    // Not my https://leetcode.com/problems/predict-the-winner/solution/
    // https://en.wikipedia.org/wiki/Minimax
    // Not optimal without memoization or DP
    public boolean PredictTheWinner(int[] nums) {
        return maxScore(nums, 0, nums.length - 1, 1) >= 0;
    }
    // Returns max score Player 1 can get. He tries to maximize his score while Player 2 tries to minimize it
    public int maxScore(int[] nums, int s, int e, int turn) {
        if (s == e)
            return turn * nums[s];
        int a = turn * nums[s] + maxScore(nums, s + 1, e, -turn);
        int b = turn * nums[e] + maxScore(nums, s, e - 1, -turn);
        return turn > 0 ? Math.max(a,b) : Math.min(a, b);
    }
}
