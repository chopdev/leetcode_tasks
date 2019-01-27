/*
384. Shuffle an Array
https://leetcode.com/problems/shuffle-an-array/

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();


QUESTIONS:
1) what numbers could we have? only positive?
2) what time complexity do we expect for both functions?
3) should we return new array of modify those in constructor?
* */
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Solution {
    private int[] nums;

    /*
    My solution
    Time O(N)
    Space O(N)
    * */
    public Solution(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        int[] res = nums.clone();
        return res;
    }

    /**
     Use hash set to remember used indexes
     * */
    public int[] shuffle() {
        int[] res = new int[nums.length];
        Set<Integer> set = new HashSet<>(nums.length);
        Random rand = new Random();
        int i = 0;
        while (set.size() != nums.length) {
            int ind = rand.nextInt(nums.length);
            if(set.contains(ind)) continue;

            set.add(ind);
            res[i++] = nums[ind];
        }

        return res;
    }
}
