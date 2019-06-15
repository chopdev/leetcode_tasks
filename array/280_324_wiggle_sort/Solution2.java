import java.util.Arrays;

/**
 324. Wiggle Sort II
 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example 1:

 Input: nums = [1, 5, 1, 1, 6, 4]
 Output: One possible answer is [1, 4, 1, 5, 1, 6].
 Example 2:

 Input: nums = [1, 3, 2, 2, 3, 1]
 Output: One possible answer is [2, 3, 1, 3, 1, 2].
 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?

 // Solution
 https://evelynn.gitbooks.io/google-interview/wiggle_sort_ii.html
 */
public class Solution2 {

    // Mine O(N*log(N)) solution
    // the problem here -  if we have many same numbers followed one by one
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length / 2; i+=2) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }
    }

    // Not mine O(N*logN) but easier
    public void wiggleSort2222(int[] nums) {
        Arrays.sort(nums);
        for(int i = 2; i < nums.length; i+=2){
            int tmp = nums[i-1];
            nums[i-1] = nums[i];
            nums[i] = tmp;
        }
    }


    // O(N) solution is pretty complex
    // https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
    // https://evelynn.gitbooks.io/google-interview/wiggle_sort_ii.html
}
