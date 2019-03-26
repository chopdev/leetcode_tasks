import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 239. Sliding Window Maximum
 https://leetcode.com/problems/sliding-window-maximum/


 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

 Example:

 Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 Output: [3,3,5,5,6,7]
 Explanation:

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Note:
 You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?

 */
public class Solution {

    // Mine O(N^2) solution
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        int ind = 0;
        int start = 0, end = k - 1;
        while (end < nums.length) {
            int max = nums[start];
            for (int i = start; i <= end; i++) {
                if(max < nums[i])
                    max = nums[i];
            }
            res[ind++] = max;
            start++;
            end++;
        }

        return res;
    }


    // Not mine amortized O(N) solution
    // Uses Deque - "double ended queue" and is usually pronounced "deck" -
    // https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
    // https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
    public int[] maxSlidingWindow2222(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        int ind = 0;
        Deque<Integer> deque = new ArrayDeque<>();  // use deque to remember indexes of current window max elements
        for (int i = 0; i < nums.length; i++) {

            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1)  // remove indexes that are out of the window
                deque.pollFirst();

            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) // remove smaller elements from the end
                deque.pollLast();

            deque.offerLast(i);  // add to the end, the element with the biggest value is at the beginning
            if(i >= k - 1)
                res[ind++] = nums[deque.peekFirst()];  // put the biggest element of the window
        }

        return res;
    }
}
