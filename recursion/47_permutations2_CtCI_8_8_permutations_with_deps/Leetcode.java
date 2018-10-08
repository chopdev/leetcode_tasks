import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations-ii/description/

 Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 Example:
 Input: [1,1,2]
 Output:
 [
     [1,1,2],
     [1,2,1],
     [2,1,1]
 ]

 */
public class Leetcode {

    // Mine solution
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums == null) return null;

        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        getPermutations(map, res, new ArrayList<>(), nums.length);
        return res;
    }

    public void getPermutations(HashMap<Integer, Integer> map, List<List<Integer>> res, List<Integer> curr, int n) {
        if(curr.size() == n) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (Integer key : map.keySet()) {
            if(map.get(key) <= 0) continue;

            map.put(key, map.get(key) - 1);
            curr.add(key);
            getPermutations(map, res, curr, n);
            curr.remove(curr.size() - 1);
            map.put(key, map.get(key) + 1);
        }
    }
}
