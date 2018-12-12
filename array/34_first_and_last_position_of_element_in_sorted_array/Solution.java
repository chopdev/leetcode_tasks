import java.util.Arrays;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

 Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 Your algorithm's runtime complexity must be in the order of O(log n)
 If the target is not found in the array, return [-1, -1].

 Example 1:

 Input: nums = [5,7,7,8,8,10], target = 8
 Output: [3,4]

 Example 2:

 Input: nums = [5,7,7,8,8,10], target = 6
 Output: [-1,-1]


 Solution:
 https://leetcode.com/articles/find-first-and-last-position-element-sorted-array/
* */
public class Solution {

    // My O(logN) solution or O((logN)^2) ?
    // O(logN) space
    // using binary search
    public int[] searchRange(int[] nums, int target) {
        if(nums == null) return new int[] {-1, -1};
        int ind = Arrays.binarySearch(nums, target);
        if(ind < 0) return new int[] {-1, -1};

        int left = searchLeft(nums, target, 0, ind - 1);
        int right = searchRight(nums, target, ind + 1, nums.length - 1);
        int[] res = new int[] {ind, ind};
        if(left >= 0) res[0] = left;
        if(right >= 0) res[1] = right;

        return res;
    }

    private int searchLeft(int[] nums, int target, int lo, int hi) {
        if(lo > hi) return -1;
        int ind = Arrays.binarySearch(nums, lo, hi + 1, target);
        if(ind < 0) return -1;
        int left = searchLeft(nums, target, lo, ind - 1);
        return left >= 0 ? left : ind;
    }

    private int searchRight(int[] nums, int target, int lo, int hi) {
        if(lo > hi) return -1;
        int ind = Arrays.binarySearch(nums, lo, hi + 1, target);
        if(ind < 0) return -1;
        int right = searchRight(nums, target, ind + 1, hi);
        return right >= 0 ? right : ind;
    }


    // Not mine
    // strict O(logN)
    //https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14701/A-very-simple-Java-solution-with-only-one-binary-search-algorithm
    public int[] searchRange2222(int[] A, int target) {
        int start = Solution.firstGreaterEqual(A, target);
        if (start == A.length || A[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, Solution.firstGreaterEqual(A, target + 1) - 1};
    }

    //find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].
    //actually this is the same as lower_bound in C++ STL.
    private static int firstGreaterEqual(int[] A, int target) {
        int low = 0, high = A.length;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            //low <= mid < high
            if (A[mid] < target) {
                low = mid + 1;
            } else {
                //should not be mid-1 when A[mid]==target.
                //could be mid even if A[mid]>target because mid<high.
                high = mid;
            }
        }
        return low;
    }


    // Not mine
    // strict O(logN)
    public int[] searchRange3333(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }

    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }
}
