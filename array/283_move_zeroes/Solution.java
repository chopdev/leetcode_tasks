public class Solution {

    // Mine solution,  not efficient
    // if meet zero swap it till the end or last zero
    public  void moveZeroes(int[] nums) {
        if(nums == null) return;

        int last = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if(nums[i] == 0){
                last = i;
                while (last < nums.length - 1 && nums[last + 1] != 0) {
                    swap(last, last + 1, nums);
                    last++;
                }
            }
        }
    }

    // Not mine solution, O(1) space, O(N) time
    // if meet non zero, swap it with the last non zero
    public void moveZeroes2222(int[] nums) {
        int lastNonZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                swap(lastNonZero++, i, nums);
            }
        }
    }

    private void swap(int ind, int next, int[] nums) {
        int temp = nums[ind];
        nums[ind] = nums[next];
        nums[next] = temp;
    }
}
