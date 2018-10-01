/*
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal
to the product of all the elements of nums except nums[i].

Example:
Input:  [1,2,3,4]
Output: [24,12,8,6]

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)



1) With dividing it would be easy - multiply all values in array.
Then in second loop divide multiplication on each item in array

2) Other solution is O(N^2), loop in loop. create [1, 1, 1.., 1] array.
Iterate through all values of initial array. And in inner loop multiply value of an element on all values of result array except index element
*/

public class Solution {

    // Not mine smart solution O(N) time, O(1) space
    // The idea is that in the first loop we write in array multiplication of all previous values
    // the same we do for backward loop
    // [2, 3, 4, 5]
    // [1, 2, 6, 24] - after first loop, multiply all prev values, for first item there are no prev values, so set it to one
    // [60, 40, 30, 24] - last item has no prev items, so leave it
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int temp = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = temp;
            temp *= nums[i];
        }

        temp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= nums[i];
        }

        return res;
    }
}
