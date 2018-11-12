/**
 https://leetcode.com/problems/rotate-array/description/
 189. Rotate Array

 Given an array, rotate the array to the right by k steps, where k is non-negative.

 Example 1:

 Input: [1,2,3,4,5,6,7] and k = 3
 Output: [5,6,7,1,2,3,4]
 Explanation:
 rotate 1 steps to the right: [7,1,2,3,4,5,6]
 rotate 2 steps to the right: [6,7,1,2,3,4,5]
 rotate 3 steps to the right: [5,6,7,1,2,3,4]
 Example 2:

 Input: [-1,-100,3,99] and k = 2
 Output: [3,99,-1,-100]
 Explanation:
 rotate 1 steps to the right: [99,-1,-100,3]
 rotate 2 steps to the right: [3,99,-1,-100]
 Note:

 Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 Could you do it in-place with O(1) extra space?


 SOLUTION:
 https://leetcode.com/problems/rotate-array/solution/

 DO NOT FORGET ABOUT
 % (modulus) or remainder
 */
public class Solution {
    // My solution O(N) time, O(N) space
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = (double)k/len > 1 ? k - len * (k / len) : k;

        int[] temp = new int[len];

        for (int i = 0; i < len; i++) {
            int toInd = i + k >= len ? i + k - len : i + k;
            temp[toInd] = nums[i];
        }

        for (int i = 0; i < len; i++)
            nums[i] = temp[i];
    }

    // My previous improved solution O(N) time, O(N) space
    public void rotateClear(int[] nums, int k) {
        int len = nums.length;
        k = k % len;

        int[] temp = new int[len];

        for (int i = 0; i < len; i++) {
            int toInd = (i + k) % len;
            temp[toInd] = nums[i];
        }

        for (int i = 0; i < len; i++)
            nums[i] = temp[i];
    }


    // My interpretation of solution #3 here
    // O(N) time, O(1) space
    // https://leetcode.com/problems/rotate-array/solution/
    // Why do we need shiftCount? Because switching depends from even or odd number of switches
    public void rotate2222(int[] nums, int k) {
        k = k % nums.length;
        int shiftCount = 0;
        for (int start = 0; start < k && shiftCount < nums.length; start ++) { // for first k numbers
            int curr = start;
            int currVal = nums[start];
            int next = (curr + k) % nums.length;
            while (next != start) {
                shiftCount++;
                currVal = change(nums, next, currVal); // remember changed number
                curr = next;
                next = (curr + k) % nums.length;
            }
            shiftCount++;
            change(nums, next, currVal);
        }
    }

    private int change(int[] nums, int next, int value) {
        int nextVal = nums[next];
        nums[next] = value;
        return nextVal;
    }



    // Best solution from #4 here
    // https://leetcode.com/problems/rotate-array/solution/
    // Short + time O(N), space O(1)
    // Firstly we reverse all the numbers
    // Then reverse first K numbers in order to get right order
    // Then reverse numbers with index bigger than K in order to get right order
    public void rotate3333(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start ++;
            end--;
        }
    }
}
