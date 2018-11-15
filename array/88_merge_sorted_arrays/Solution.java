/**
 https://leetcode.com/problems/merge-sorted-array/description/
 88. Merge Sorted Array

 Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

 Note:

 The number of elements initialized in nums1 and nums2 are m and n respectively.
 You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.

 Example:

 Input:
 nums1 = [1,2,3,0,0,0], m = 3
 nums2 = [2,5,6],       n = 3

 Output: [1,2,2,3,5,6]

 */
public class Solution {

    // My solution
    // O(max(m, n)) time, O(1) space
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums2 == null || nums2.length == 0) return;
        // put elements to end of array O(m)
        for (int i = m - 1, j = nums1.length - 1; i >=0 ; i--, j--) {
            nums1[j] = nums1[i];
            nums1[i] = 0;
        }

        int first = nums1.length - m;
        int second = 0;
        int i = 0;

        // put smallest elements at the beginning of nums1
        while (first < nums1.length && second < nums2.length) {
            if(nums1[first] < nums2[second]){
                nums1[i] = nums1[first];
                first++;
            }
            else {
                nums1[i] = nums2[second];
                second++;
            }
            i++;
        }

        // put the rest of the elements in nums1 from nums2
        while (second < nums2.length) {
            nums1[i] = nums2[second];
            i++;
            second++;
        }
    }

    // Solution from leetcode
    // they build it similarly, but compare max elements and put them in the end
    public void merge2222(int[] nums1, int m, int[] nums2, int n) {

        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

}
