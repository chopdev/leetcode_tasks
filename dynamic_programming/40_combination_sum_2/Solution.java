import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 40. Combination Sum II
 https://leetcode.com/problems/combination-sum-ii/

 Given a collection of candidate numbers (candidates) and a target number (target), find all unique
 combinations in candidates where the candidate numbers sums to target.

 Each number in candidates may only be used once in the combination.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [10,1,2,7,6,1,5], target = 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]

 Example 2:

 Input: candidates = [2,5,2,1,2], target = 5,
 A solution set is:
 [
 [1,2,2],
 [5]
 ]
 */
public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        getCombinations(candidates, target, 0, res, new ArrayList<>());
        return res;
    }

    public void getCombinations(int[] candidates, int target, int i, List<List<Integer>> res, List<Integer> curr) {
        if(i >= candidates.length || target < 0 || candidates[i] > target) return;
        if(target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        getCombinations(candidates, target, i + 1, res, curr);
        
        curr.add(candidates[i]);
        getCombinations(candidates, target - candidates[i], i + 1, res, curr);
        curr.remove(curr.size() - 1);
    }
}
