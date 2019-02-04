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


 SOLUTION
 https://leetcode.com/articles/two-sum/

 WHAT to ask:
 1) could we have duplicate numbers, negative numbers?
 2) you may not use the same element twice!
* */
public class Solution {

    // Mine solution
    // O(N) time and space
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for(int i=0; i<nums.length; i++) {
            if(map.containsKey(nums[i])) {
                res[0] = map.get(nums[i]);
                res[1] = i;
                return res;
            }
            map.put(target - nums[i], i);
        }
        return res;
    }
}
