/*
46. Permutations
https://leetcode.com/problems/permutations/

Given an array of distinct integers, return all possible permutations in any order.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    /*
     Each successive position has one fewer available number:
     N * (N - 1) * (N - 2) * ... * 1 = N! permutations.

     Time: O(N * N!), including copying each completed permutation.
     Auxiliary space: O(N) for the recursion stack, curr, and taken.
     Output space: O(N * N!).
    */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, new HashSet<>(), new ArrayList<>(), res);
        return res;
    }

    void backtrack(int[] nums, Set<Integer> taken, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (taken.contains(nums[i])) continue;

            taken.add(nums[i]);
            curr.add(nums[i]);

            backtrack(nums, taken, curr, res);

            taken.remove(nums[i]);
            curr.remove(curr.size() - 1);
        }
    }
}
