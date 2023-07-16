import java.util.Arrays;

/***
 * 280. Wiggle Sort
 * https://leetcode.com/problems/wiggle-sort/?envType=study-plan-v2&envId=premium-algo-100
 *
 * Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * You may assume the input array always has a valid answer.
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: [3,5,1,6,2,4]
 * Explanation: [1,6,2,5,3,4] is also accepted.
 *
 *
 * Example 2:
 *
 * Input: nums = [6,6,5,6,3,8]
 * Output: [6,6,5,6,3,8]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 104
 * It is guaranteed that there will be an answer for the given input nums.
 *
 *
 * Follow up: Could you solve the problem in O(n) time complexity?
 * */
public class Solution {

    /**
     * My solution
     * O(N*logN) time, O(N) space becase of sorting
     *
     *  The idea is to sort array and then swipe second and third item in every 3 numbers range eg
     *  [1 2 3 4 5 6]  -> 1 2 3, 3 4 5, 5 6
     *
     *  1 2 3 4 5 6 7 -> 1 3 2 5 4 7 6
     *  1 2 2 2 3 3 3 -> 1 2 2 3 2 3 3
     * */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 2) {
            int j = i + 1;
            if (j + 1 >= nums.length) break;
            swap(j, j + 1, nums);
        }
    }

    private void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    /**
     * Not my O(N) time solution
     * https://leetcode.com/problems/wiggle-sort/solutions/71692/java-o-n-solution/?envType=study-plan-v2&envId=premium-algo-100
     *
     * The idea is kinda similar, sorting is not needed
     * In every 3 numbers range we do 2 swipes always putting the biggest number in the middle
     * */
    public void wiggleSort2222(int[] nums) {
        for(int i=0;i<nums.length;i++)
            if(i%2==1)
                if(nums[i-1]>nums[i])
                    swap(nums, i);
            else if(i!=0 && nums[i-1]<nums[i])
                swap(nums, i);
    }
    public void swap(int[] nums, int i){
        int tmp=nums[i];
        nums[i]=nums[i-1];
        nums[i-1]=tmp;
    }
}
