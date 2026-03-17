/*
162. Find Peak Element
https://leetcode.com/problems/find-peak-element/
*/

/**

[1, 2, 3, 4, 5]
[2, 1]
[1, 2, 3, 4, 5, 3, 2]
[4, 3, 1, 5]

explanation: there is always subarray which is increasing or decreasing in the array. We decrease the window size to increasing direction
 */
class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int l = 0;
        int r = n - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (mid == n - 1 ? nums[mid] > nums[mid - 1] : nums[mid] < nums[mid + 1]) { // mid can't be an answer as it's not the peak
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }


/**

You’re doing boundary search on the “slope”:

rising slope ⇒ a peak exists to the right
falling slope ⇒ a peak exists to the left (including mid)

"I’m not finding the peak directly — I’m walking uphill until I can’t"
 */
    public int findPeakElement2222(int[] nums) {
        int l = 0, r = nums.length - 1; // l can become "nums.length - 1" 

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
