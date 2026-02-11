/**
 
239. Sliding Window Maximum
https://leetcode.com/problems/sliding-window-maximum/



Dequeue - contains indexes, with biggest items at the front and smallest in the back.

pollFirst - if index is below begin
pollLast - if nums[end] >= dq.peekLast()

 */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k <= 0) return new int[0];
        if (k == 1) return nums;

        int[] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        int begin = 0;

        for (int end = 0; end < nums.length; end ++) {
            
            while (!dq.isEmpty() && nums[end] >= nums[dq.peekLast()]) {
                dq.pollLast(); // remove smaller items that we met before in the window. They are not relevant anymore
            }

            dq.addLast(end);

            if (end - begin + 1 > k) {
                begin ++;
            } 

            while (dq.peekFirst() < begin) {
                dq.pollFirst(); // remove indexes out of window range
            }

            if (end - begin + 1 == k) {
                res[begin] = nums[dq.peekFirst()];
            } 
        }

        return res;
    }
}