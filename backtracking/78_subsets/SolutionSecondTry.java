import java.util.ArrayList;
import java.util.List;

/*
78. Subsets
https://leetcode.com/problems/subsets/

good explanation: https://www.youtube.com/watch?v=bGC2fNALbNU


IMPORTANT to ask!
1) Could we have duplicates numbers? Logic will differentiate significantly
2) could we have empty input?
* */
public class SolutionSecondTry {

    // My solution
    // O(2^N) time as we would have 2^N subsets - each time we take or do nat take an element,
    // O(2N)= O(N) space - tempList + N depth of recursion
    public List<List<Integer>> subsets(int[] nums) {
        if(nums == null || nums.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    public void backtrack(int[] nums, int i, List<Integer> curr, List<List<Integer>> res) {
        if(i >= nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[i]);
        backtrack(nums, i+1, curr, res);
        curr.remove(curr.size() - 1);
        backtrack(nums, i+1, curr, res);
    }
}
