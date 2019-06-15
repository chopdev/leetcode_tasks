/**
 280 Wiggle Sort
 Given an unsorted array nums, reorder it in-place, such that nums[0] <= nums[1] => nums[2] <= nums[3]...

 Example:

 Input: nums = [3, 5, 2, 1, 6, 4]
 Output: One possible answer is [3, 5, 1, 6, 2, 4]

 # Google onsite interview question (not mine)
 # closed on leetcode
 * */
public class Solution {

    // Mine O(N) solution
    public void wiggleSort(int[] nums) {
        if(nums == null) return;
        for (int i = 1; i < nums.length; i+=2) {
            if(nums[i] < nums[i-1])
                swap(nums, i, i-1);
            if(i+1 < nums.length && nums[i] < nums[i+1])
                swap(nums, i, i+1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
