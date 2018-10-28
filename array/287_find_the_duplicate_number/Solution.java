/**
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 * 287. Find the Duplicate Number

 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

 Example 1:
 Input: [1,3,4,2,2]
 Output: 2

 Example 2:
 Input: [3,1,3,4,2]
 Output: 3

 Note:

 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.

 */
public class Solution {
    // Not mine O(N*logN) solution
    //https://leetcode.com/problems/find-the-duplicate-number/discuss/72841/Java-O(1)space-using-Binary-Search
    //Other solution:  https://leetcode.com/problems/find-the-duplicate-number/discuss/72846/My-easy-understood-solution-with-O(n)-time-and-O(1)-space-without-modifying-the-array.-With-clear-explanation.?page=10
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;  // range from 1 to n
        while (low <= high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;  // look where the dense is bigger
            else high = mid - 1;
        }
        return low;
    }
}
