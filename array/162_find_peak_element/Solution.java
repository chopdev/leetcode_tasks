/**
 https://leetcode.com/problems/find-peak-element/description/
 162. Find Peak Element

 A peak element is an element that is greater than its neighbors.
 Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 You may imagine that nums[-1] = nums[n] = -∞.

 Example 1:
 Input: nums = [1,2,3,1]
 Output: 2
 Explanation: 3 is a peak element and your function should return the index number 2.

 Example 2:
 Input: nums = [1,2,1,3,5,6,4]
 Output: 1 or 5
 Explanation: Your function can return either index number 1 where the peak element is 2,
 or index number 5 where the peak element is 6.

 Note:

 Your solution should be in logarithmic complexity.

 */
public class Solution {

    // Mine solution O(logN) time, O(logN) space - deepness of recursion
    public int findPeakElement(int[] nums) {
        if(nums == null) return -1;
        return search(nums, 0, nums.length - 1);
    }

    // this works because nums[i] ≠ nums[i+1]
    // so we know if element is less than rigth, then peak number exists in the right part
    // if element is less than left, then peak exists in the left part of array
    private int search(int[] arr, int left, int right) {
        // base cases
        if(left == right) return left;
        if(left + 1 == right) {
            if(arr[left] > arr[right]) return left;
            return right;
        }

        int mid = (left + right) / 2;
        if(arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) return mid;  // element is peak

        if(arr[mid] < arr[mid + 1]) return search(arr, mid + 1, right);
        return search(arr, left, mid - 1);
    }


    // not mine solution
    public int findPeakElement2222(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
