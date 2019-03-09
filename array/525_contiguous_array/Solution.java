import java.util.ArrayList;
import java.util.List;

/**
 525. Contiguous Array
 https://leetcode.com/problems/contiguous-array/

 Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

 Example 1:

 Input: [0,1]
 Output: 2
 Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

 Example 2:

 Input: [0,1,0]
 Output: 2
 Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

 Note: The length of the given binary array will not exceed 50,000.

 */
public class Solution {
    // My solution, but i didn't understand the problem
    // I thought we need to find max subarray with equal number of 1 and 0 where numbers are adjacent
    // 101110001 - 6 (3 ones, 3 zeroes)
    public int findMaxLength(int[] nums) {
        if(nums == null) return 0;
        int max = 0;
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < nums.length; ) { // important not to increase i here, as we do it inside the loop
            int j = i + 1;
            int count = 1;
            for (; j < nums.length && nums[j] == nums[i]; j++)
                count++;
            counts.add(count);
            i = j;
        }

        for (int i = 0; i < counts.size() - 1; i++) {
            max = Math.max(max, Math.min(counts.get(i), counts.get(i + 1)));
        }

        return max * 2;
    }

    // My solution, but i didn't understand the problem
    // I thought we need to find max subarray with equal number of 1 and 0 where numbers are adjacent
    // 101110001 - 6 (3 ones, 3 zeroes)
    public int findMaxLength2222(int[] nums) {
        if(nums == null) return 0;
        int currCount = 0, prevCount = 0, max = 0;
        for (int i = 0; i < nums.length; ) { // important not to increase i here, as we do it inside the loop
            int j = i + 1;
            currCount = 1;
            for (; j < nums.length && nums[j] == nums[i]; j++)
                currCount++;
            max = Math.max(max, Math.min(currCount, prevCount));
            prevCount = currCount;
            i = j;
        }

        return max * 2;
    }
}
