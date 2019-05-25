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

    // My wrong solution
    // the logic is: on each step we skip or take the number
    // it doesn't take into account duplicate numbers
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        getCombinations(candidates, target, 0, res, new ArrayList<>());
        return res;
    }

    public void getCombinations(int[] candidates, int target, int i, List<List<Integer>> res, List<Integer> curr) {
        if(target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        if(i >= candidates.length || target < 0 || candidates[i] > target) return;

        getCombinations(candidates, target, i + 1, res, curr);
        
        curr.add(candidates[i]);
        getCombinations(candidates, target - candidates[i], i + 1, res, curr);
        curr.remove(curr.size() - 1);
    }


    // Not mine solution
    // Time complexity is O(2^n). Space complexity O(n). Basically, for each num you have two choices, pick it or not.
    // https://leetcode.com/problems/combination-sum-ii/discuss/16878/Combination-Sum-I-II-and-III-Java-solution-(see-the-similarities-yourself)
    // LOGIC: basically we try to define number for first position, on the second level of recursion - for the second position and so on
    // when we ignoring duplicates, it means that for the same position we do not consider numbers with the same value
    // example [1 1 1 2 3], target 4
    //  1 1 2
    //  1 3
    public List<List<Integer>> combinationSum2222(int[] candidates, int target) {
        Arrays.sort(candidates);    // important (for ignoring duplicates)
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, res, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<List<Integer>> res, List<Integer> curr) {
        if(target < 0) return;
        if(target == 0) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if(i != start && candidates[i] == candidates[i-1]) continue;  // ignore duplicates

            curr.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, res, curr);
            curr.remove(curr.size() - 1);
        }
    }
}
