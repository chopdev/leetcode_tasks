import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
* 8.4 Power Set: Write a method to return all subsets of a set.
* */
public class Solution {
    // Mine solution Complexity O(n*2^n)
    // In book solution is prettier, but complexity the same
    public List<List<Integer>> getAllSubsets(List<Integer> list) {
        if (list == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        HashSet<String> seen = new HashSet<>();

        getAllSubsets(list, res, seen);
        List<Integer> empty = new ArrayList<>();
        res.add(empty);
        return res;
    }

    public void getAllSubsets(List<Integer> list, List<List<Integer>> res, HashSet<String> seen) {
        String hash = join(list);
        if(list.size() == 0 || seen.contains(hash)) return;

        res.add(list);
        seen.add(hash);
        for (int i = 0; i < list.size(); i++) {
            List<Integer> newArr = new ArrayList<>(list);
            newArr.remove(i);
            getAllSubsets(newArr, res, seen);
        }
    }

    private String join(List<Integer> list) {
        StringBuilder b = new StringBuilder();
        for (Integer val : list) {
            b.append(val);
            b.append("_");
        }

        return b.toString();
    }




    // Not mine solution
    // https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
    public List<List<Integer>> subsets(int[] nums) {
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
