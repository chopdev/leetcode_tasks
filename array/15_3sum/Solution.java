import java.util.*;

/**
 15. 3Sum
 https://leetcode.com/problems/3sum/

 Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
 Find all unique triplets in the array which gives the sum of zero.

 Note:

 The solution set must not contain duplicate triplets.

 Example:

 Given array nums = [-1, 0, 1, 2, -1, -4],

 A solution set is:
 [
 [-1, 0, 1],
 [-1, -1, 2]
 ]

 * */
public class Solution {

    // My solution
    // Time O(N^2 * logN)
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null) return null;

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;  // to avoid duplicates
            for (int j = i + 1; j < nums.length - 1; j++) {
                if(j > i + 1 && nums[j] == nums[j - 1]) continue; // to avoid duplicates
                int k = Arrays.binarySearch(nums, j + 1, nums.length, -1 * (nums[i] + nums[j]));
                if(k >= 0)
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
            }
        }

        return res;
    }

    // Not mine solution
    // Time O(N^2)
    // https://leetcode.com/problems/3sum/discuss/7380/Concise-O(N2)-Java-solution
    //The idea is to sort an input array and then run through all indices of a possible first
    // element of a triplet. For each possible first element we make a standard bi-directional
    // 2Sum sweep of the remaining part of the array. Also we want to skip equal elements to avoid
    // duplicates in the answer without making a set or smth like that.
    public List<List<Integer>> threeSum2222(int[] nums) {
        if(nums == null) return null;

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) continue;  // to avoid duplicates
            int lo = i + 1, hi = nums.length - 1, sum = -nums[i];
            while (lo < hi) {
                if(nums[lo] + nums[hi] == sum) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo + 1] == nums[lo]) lo++;
                    while (lo < hi && nums[hi - 1] == nums[hi]) hi--;
                    lo++; hi--;
                }
                else if(nums[lo] + nums[hi] < sum) lo++;
                else hi--;
            }
        }

        return res;
    }
}
