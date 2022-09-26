import java.util.Stack;

/**
 * 2104. Sum of Subarray Ranges
 *
 * https://leetcode.com/problems/sum-of-subarray-ranges/
 *
 * You are given an integer array nums. The range of a subarray of nums is the difference between
 * the largest and smallest element in the subarray.
 * Return the sum of all subarray ranges of nums.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [2], range = 2 - 2 = 0
 * [3], range = 3 - 3 = 0
 * [1,2], range = 2 - 1 = 1
 * [2,3], range = 3 - 2 = 1
 * [1,2,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
 *
 *
 * Example 2:
 *
 * Input: nums = [1,3,3]
 * Output: 4
 * Explanation: The 6 subarrays of nums are the following:
 * [1], range = largest - smallest = 1 - 1 = 0
 * [3], range = 3 - 3 = 0
 * [3], range = 3 - 3 = 0
 * [1,3], range = 3 - 1 = 2
 * [3,3], range = 3 - 3 = 0
 * [1,3,3], range = 3 - 1 = 2
 * So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
 *
 *
 * Example 3:
 *
 * Input: nums = [4,-2,-3,4,1]
 * Output: 59
 * Explanation: The sum of all subarray ranges of nums is 59.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -109 <= nums[i] <= 109
 * */

public class Solution {

    /**
     * My brute force solution
     * O(N^2) time, O(1) space
     * */
    public long subArrayRanges2222(int[] nums) {
        long rangeSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < nums.length; j ++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                rangeSum += max - min;
            }
        }
        return rangeSum;
    }


    // My wrong approach solution using segmentTree
    // Assumption is wrong because not all the sub arrays are present in segment tree
    public long subArrayRanges33333(int[] nums) {
        int[][] tree = new int[4*nums.length][]; // internal array contains 3 elements [min, max, diff]
        buildSegmentTree(nums, tree, 1, 0, nums.length - 1); // build tree from 1 index for simplicity
        int totalSum = 0;
        for (int i = 0; i < tree.length; i ++) {
            if (tree[i] == null) continue;
            totalSum += tree[i][2];
        }
        return totalSum;
    }

    private void buildSegmentTree(int[] nums, int[][] tree, int treeIndex, int left, int right) {
        if (left == right) {
            tree[treeIndex] = new int[] {nums[left], nums[right], 0};
        } else {
            int mid = (left + right) / 2;
            buildSegmentTree(nums, tree, treeIndex * 2, left, mid);
            buildSegmentTree(nums, tree, treeIndex * 2 + 1, mid + 1, right);

            // merge subtrees
            int childMin = Math.min(tree[treeIndex * 2][0], tree[treeIndex * 2 + 1][0]);
            int childMax = Math.max(tree[treeIndex * 2][1], tree[treeIndex * 2 + 1][1]);
            tree[treeIndex] = new int[] {childMin, childMax, childMax - childMin};
        }
    }


    /**
     * O(N) time solution, O(N) space using  "monotonous increase stack"
     * https://leetcode.com/problems/sum-of-subarray-ranges/discuss/1626628/O(n)-solution-with-monotonous-stack-oror-Full-explaination
     *
     * code is taken here
     * https://leetcode.com/problems/sum-of-subarray-ranges/discuss/1624222/JavaC%2B%2BPython-O(n)-solution-detailed-explanation
     *
     * basically we are doing
     * rangeSum += max1 - min1 + max2 - min2 + ... max3 - min3; from the first solution but collect
     * */
    public long subArrayRanges(int[] A) {
        int n = A.length, j, k;
        long res = 0;

        Stack<Integer> s = new Stack<>();
        for (int i = 0; i <= n; i++) {
            while (!s.isEmpty() && (i == n || A[s.peek()] > A[i])) { // when A[j] > A[i]
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                // i-j - count of elements that are smaller than j on the right side from j
                // j-k - count of elements that are smaller than j on the left side from j
                res -= (long)A[j] * (i - j) * (j - k);

            }
            s.push(i);
        }

        s.clear();
        for (int i = 0; i <= n; i++) {
            while (!s.isEmpty() && (i == n || A[s.peek()] < A[i])) { // when A[j] < A[i]
                j = s.pop();
                k = s.isEmpty() ? -1 : s.peek();
                // i-j - count of elements that are bigger than j on the right side from j
                // j-k - count of elements that are bigger than j on the left side from j
                res += (long)A[j] * (i - j) * (j - k);

            }
            s.push(i);
        }
        return res;
    }
}
