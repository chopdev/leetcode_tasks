import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 2059. Minimum Operations to Convert Number
 * https://leetcode.com/problems/minimum-operations-to-convert-number/description/
 *
 * You are given a 0-indexed integer array nums containing distinct numbers, an integer start, and an integer goal. There is an integer x that is initially set to start, and you want to perform operations on x such that it is converted to goal. You can perform the following operation repeatedly on the number x:
 *
 * If 0 <= x <= 1000, then for any index i in the array (0 <= i < nums.length), you can set x to any of the following:
 *
 * x + nums[i]
 * x - nums[i]
 * x ^ nums[i] (bitwise-XOR)
 * Note that you can use each nums[i] any number of times in any order. Operations that set x to be out of the range 0 <= x <= 1000 are valid, but no more operations can be done afterward.
 *
 * Return the minimum number of operations needed to convert x = start into goal, and -1 if it is not possible.
 *
 * Example 1:
 *
 * Input: nums = [2,4,12], start = 2, goal = 12
 * Output: 2
 * Explanation: We can go from 2 → 14 → 12 with the following 2 operations.
 * - 2 + 12 = 14
 * - 14 - 2 = 12
 *
 *
 * Example 2:
 *
 * Input: nums = [3,5,7], start = 0, goal = -4
 * Output: 2
 * Explanation: We can go from 0 → 3 → -4 with the following 2 operations.
 * - 0 + 3 = 3
 * - 3 - 7 = -4
 * Note that the last operation sets x out of the range 0 <= x <= 1000, which is valid.
 *
 *
 * Example 3:
 *
 * Input: nums = [2,8,16], start = 0, goal = 1
 * Output: -1
 * Explanation: There is no way to convert 0 into 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -10^9 <= nums[i], goal <= 10^9
 * 0 <= start <= 1000
 * start != goal
 * All the integers in nums are distinct.
 *
 *
 * */
public class Solution {

    // My solution
    // O(N^3) time?
    // Exceeds time limit, double ended BFS might be an option
    public int minimumOperations22222(int[] nums, int start, int goal) {
        if (start == goal) return 0;
        HashMap<Integer, Integer> dp = new HashMap<>(); // number to count of operations needed
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        dp.put(start, 0);

        for (int i = 0; i < 10000; i++) {
            Integer curr = queue.poll();
            Integer countOfOperations = dp.get(curr);

            for (int num : nums) {
                int next1 = curr - num;
                int next2 = curr + num;
                int next3 = curr ^ num;

                if (next1 == goal || next2 == goal || next3 == goal)
                    return countOfOperations + 1;

                if (!dp.containsKey(next1)) {
                    dp.put(next1, countOfOperations + 1);
                    queue.add(next1);
                }

                if (!dp.containsKey(next2)) {
                    dp.put(next2, countOfOperations + 1);
                    queue.add(next2);
                }

                if (!dp.containsKey(next3)) {
                    dp.put(next3, countOfOperations + 1);
                    queue.add(next3);
                }
            }
        }

        return -1;
    }


    // Not my https://leetcode.com/problems/minimum-operations-to-convert-number/solutions/1550030/java-bfs-set-easy-implementation/
    // Just do simple BFS and maintain a visited set to keep track of numbers that we have seen already.
    //Here keypoint is numbers can be from 0 to 1000 so make visited array and once we have seen that number we don't have to include that number again.
    //Time Complexity:-O(n*m) where n is size of the array and m is the range.
    public int minimumOperations(int[] nums, int start, int goal) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        Set<Integer> set = new HashSet<>();

        int res = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){
                int val = q.poll();

                if(val == goal) return res;

                if((!(val >= 0 && val <= 1000)) || set.contains(val)){
                    continue;
                }

                if(!set.contains(val)) set.add(val);

                for(int num : nums){
                    q.offer(val + num);
                    q.offer(val - num);
                    q.offer(val ^ num);
                }
            }

            res++;
        }

        return -1;
    }
}
