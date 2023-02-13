import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 740. Delete and Earn
 * https://leetcode.com/problems/delete-and-earn/
 *
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 *
 *
 * Example 2:
 *
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 * */
public class Solution {

    /**
     * My top down solution
     *
     * Sort nums backwards:
     * 4, 3, 3, 3, 2, 2
     *
     * F(K) = MAX(K + F(K - 2), F(K-1))    where K is current number. So we basically either take current number or skip it
     *
     * */
    public int deleteAndEarn2222(int[] nums) {
        Arrays.sort(nums);
        reverseArray(nums);
        return deleteAndEarnRecursive(nums, nums[0], 0, new HashMap<>());
    }

    private static void reverseArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;

            left++;
            right--;
        }
    }

    public int deleteAndEarnRecursive(int[] nums, int k, int i, Map<Integer, Integer> memo) {
        if (i >= nums.length) return 0;
        if (memo.containsKey(k)) return memo.get(k);
        if (k < nums[i]) return deleteAndEarnRecursive(nums, k, i + 1, memo); // skip number higher than k

        int currSum = 0;
        for (; i < nums.length; i ++) {
            if (k != nums[i]) break;
            currSum += nums[i];
        }

        int takeCurrentSum = currSum + deleteAndEarnRecursive(nums, k - 2, i, memo);
        int skipCurrentSum = deleteAndEarnRecursive(nums, k - 1, i, memo);
        memo.put(k, Math.max(takeCurrentSum, skipCurrentSum));
        return memo.get(k);
    }



    /**
     * not my bottom up
     * https://leetcode.com/problems/delete-and-earn/solutions/1818112/delete-and-earn/
     *
     * great idea to fill maxPoints[1,2,3...maxNumber]
     * */
    public int deleteAndEarn(int[] nums) {
        HashMap<Integer, Integer> points = new HashMap<>();
        int maxNumber = 0;

        // Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }

        // Declare our array along with base cases
        int[] maxPoints = new int[maxNumber + 1];
        maxPoints[1] = points.getOrDefault(1, 0);

        for (int num = 2; num < maxPoints.length; num++) {
            // Apply recurrence relation
            int gain = points.getOrDefault(num, 0);
            maxPoints[num] = Math.max(maxPoints[num - 2] + gain, maxPoints[num - 1]);
        }

        return maxPoints[maxNumber];
    }

    /**
     * Same as above but a bit more clean
     * https://leetcode.com/problems/delete-and-earn/solutions/109895/java-c-clean-code-with-explanation/?orderBy=most_votes
     *
     * dp is filled for all possible values even if they are not present in nums
     * */
    public int deleteAndEarn3333(int[] nums) {
        int[] buckets = new int[10001];
        for (int num : nums) {
            buckets[num] += num;
        }
        int[] dp = new int[10001];
        dp[0] = buckets[0];
        dp[1] = buckets[1];
        for (int i = 2; i < buckets.length; i++) {
            dp[i] = Math.max(buckets[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[10000];
    }
}
