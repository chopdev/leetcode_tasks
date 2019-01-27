import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Solution2 {
    private int[] nums;
    private int[] original;
    private Random random = new Random();

    /*
    NOT mine solution
        Fisher-Yates Algorithm
        https://leetcode.com/articles/shuffle-an-array/
    * */
    public Solution2(int[] nums) {
        this.original = nums.clone();
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        nums = original.clone();
        return nums;
    }

    /**
     Use hash set to remember used indexes
     * */
    public int[] shuffle() {
        for (int i = 0; i < nums.length; i++) {
            int index = random.nextInt(nums.length);
            swap(i, index);
        }
        return nums;
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
