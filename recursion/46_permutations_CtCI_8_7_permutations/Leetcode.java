import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 Given a collection of distinct integers, return all possible permutations.

 Example:

 Input: [1,2,3]
 Output:
 [
     [1,2,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
 ]

 */
public class Leetcode {

    // Mine solution O(N!)
    // Did it as I would do it manually on the paper
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;

        List<Integer> initial = new LinkedList<>();
        for (int n : nums)
            initial.add(n);

        getPermutations(initial, res, new LinkedList<>());
        return res;
    }

    public void getPermutations(List<Integer> initial, List<List<Integer>> res, List<Integer> curr) {
        if(initial.size() <= 1) {
            curr.add(initial.get(0));
            res.add(curr);
            return;
        }

        for (int i = 0; i < initial.size(); i++) {
            List<Integer> temp = new LinkedList<>(initial);
            temp.remove(i);
            List<Integer> newCurr = new LinkedList<>(curr);
            newCurr.add(initial.get(i));
            getPermutations(temp, res, newCurr);
        }
    }



    // https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
    // backtracking solution
    // Backtracking is trying out all possibilities using recursion, exactly like bruteforce
    // You don’t have enough information to choose correctly. Each choice leads to another set of choices. One or more sequences of choices may (or may not) lead to a solution.

    public List<List<Integer>> permute2222(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
