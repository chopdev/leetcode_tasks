/*
39. Combination Sum
https://leetcode.com/problems/combination-sum/

Given distinct positive candidates and a target, return all unique combinations
whose sum equals the target. Each candidate can be used any number of times.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    /*
     candidates = [2, 3, 6, 7], target = 16

     One result is [7, 7, 2]. Restricting subsequent choices to startIndex or
     later prevents also generating [2, 7, 7]. This works with any fixed order;
     descending order considers larger candidates first. After a branch exceeds
     the target, the loop can still consider smaller candidates.

     Let N = candidates.length, T = target, and M = minimum candidate.
     Time: exponential; a loose bound is O(N log N + N^(floor(T/M) + 1)
     + output size), including sorting, overshooting branches, and result copies.
     Auxiliary space: O(N + T/M) for sorting, recursion, and the current path,
     excluding output.
    */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        candidates = Arrays.stream(candidates)
                           .boxed()
                           .sorted(Collections.reverseOrder())
                           .mapToInt(Integer::intValue)
                           .toArray();
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
