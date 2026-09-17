/*
78. Subsets
https://leetcode.com/problems/subsets/

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

!IMPORTANT
good explanation
https://www.youtube.com/watch?v=bGC2fNALbNU
* */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    /*
     [], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]

     For every element there are two states: include it or don't include it.
     Therefore, there are 2^N subsets.
     Time: O(N * 2^N), accounting for copying the subsets.
     Auxiliary space: O(N) for the current subset and recursion stack.
     Output space: O(N * 2^N).
    */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(
        int[] nums,
        int start,
        List<Integer> curr,
        List<List<Integer>> res
    ) {
        res.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            curr.add(nums[i]);
            backtrack(nums, i + 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    // Solution 2: previous implementation (not mine), sorts the input first.
    // Time: O(N * 2^N); auxiliary space: O(N); output space: O(N * 2^N).
    // https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
