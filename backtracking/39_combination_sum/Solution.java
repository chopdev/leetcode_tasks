package chopdev.combination_sum_39;

import java.util.*;

/*
39. Combination Sum
https://leetcode.com/problems/combination-sum/
Given a set (SET MEANS UNIQUE!!!! NO DUPLICATES!!!!) of candidate numbers (C) and a target number (T),
find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.
Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7,
*/

public class Solution {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList();
    if (candidates == null || candidates.length == 0 || target <= 0)
      return result;

    Arrays.sort(candidates);
    for (int i = 0; i < candidates.length; i++) {
      if (candidates[i] > target)
        break;

      getCombinationChain(candidates, i, target, new LinkedList<>(), result);
    }

    return result;
  }

  private void getCombinationChain(int[] candidates, int currentElementIndex, int target, List<Integer> chain, List<List<Integer>> result) {
    int currentElement = candidates[currentElementIndex];
    if (target == currentElement) {
      chain.add(target);
      result.add(chain);
      return;
    }

    chain.add(currentElement);
    int nextTarget = target - currentElement;

    // start from currentElementIndex to avoid duplicates
    for (int i = currentElementIndex; i < candidates.length; i++) {
      if (candidates[i] > nextTarget)
        break;

      getCombinationChain(candidates, i, nextTarget, new LinkedList<>(chain), result);
    }
  }
}