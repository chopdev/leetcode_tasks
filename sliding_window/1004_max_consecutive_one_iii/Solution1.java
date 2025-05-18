/* 
https://leetcode.com/problems/max-consecutive-ones-iii/description/?envType=study-plan-v2&envId=leetcode-75
Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 

Constraints:

1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
 */

class Solution {

    /* 
        O(N^2) time in the worst case, O(1) space
     */
    public int longestOnes(int[] nums, int k) {
        int longest = 0;
        int curr = 0;
        for (int i =0; i<nums.length; i++) {
            if (nums[i] == 0) {
                int allowedSubstitutes = k;
                for (int j =i; j<nums.length; j++) {
                    if (nums[j] == 0 && allowedSubstitutes <= 0) 
                        break;
                    
                    if (nums[j] == 0) 
                        allowedSubstitutes --;
                    curr++;
                }
                if (longest < curr) 
                    longest = curr;
                curr = 0;
            } else {
                curr ++;
                if (longest < curr) longest = curr; 
            }
        }

        return longest;
    }


    /* 
        Sliding window solution

    -  Grow the window by moving right.
    -  Count how many 0s are in the window.
    - If you have more than k zeros, shrink the window from the left.
    - Track the maximum window length where the count of zeros is ≤ k.


    ### How to think about Sliding Window problem?
Ask yourself:
- What am I looking for? (e.g., longest subarray with at most k zeros)
- What happens when I expand the window? (move right)
- What condition breaks the rule? (e.g., more than k zeros)
- How do I fix it? (move left until the condition is valid again)
- What do I track? (e.g., current window size)
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int longest = 0;
        int zeros = 0;
        for (int right = 0; right<nums.length; right++) {
            if (nums[right] == 0) 
                zeros ++;
            
            // shrink left side
            while (zeros > k) {
                if (nums[left] == 0)
                    zeros --;
                left ++;
            }

            longest = Math.max(longest, right - left + 1);
        }

        return longest;
    }
}