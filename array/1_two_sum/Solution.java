import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Handler;

/**
 https://leetcode.com/problems/two-sum/description/
 1. Two Sum

 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:

 Given nums = [2, 7, 11, 15], target = 9,
 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].

* */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int n : nums)
            hashMap.put(target - n, hashMap.getOrDefault(target - n, 0) + 1);

        for (int n : nums) {
            if(!hashMap.containsKey(n)) continue;
            int count = hashMap.get(n);
            if(count == 1 && n == target / 2) continue;
            return
        }

     }
}
