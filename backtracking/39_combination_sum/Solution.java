/*
39. Combination Sum
https://leetcode.com/problems/combination-sum/

Given distinct positive candidates and a target, return all unique combinations
whose sum equals the target. Each candidate can be used any number of times.
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    /*
     candidates = [2, 3, 6, 7], target = 16

     One result is [2, 7, 7]. Restricting subsequent choices to startIndex or
     later prevents also generating [7, 7, 2]. No sorting is needed.

     Let N = candidates.length, T = target, and M = minimum candidate.
     Time: exponential; a loose bound is O(N^(floor(T/M) + 1) + output size),
     including overshooting branches and result copies.
     Auxiliary space: O(T/M) for recursion and the current path, excluding output.
    */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, 0, 0, new ArrayList<>(), res, target);
        return res;
    }

    void backtrack(int[] candidates, int startIndex, int sum, List<Integer> curr, List<List<Integer>> res, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(curr));
            return;
        }

        if (sum > target) return;

        for (int i = startIndex; i < candidates.length; i++) {
            sum += candidates[i];
            curr.add(candidates[i]);

            backtrack(candidates, i, sum, curr, res, target);

            sum -= candidates[i];
            curr.remove(curr.size() - 1);
        }
    }
}
