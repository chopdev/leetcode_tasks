import java.util.ArrayList;
import java.util.List;

/**
 https://leetcode.com/problems/find-all-duplicates-in-an-array/
 442. Find All Duplicates in an Array

 Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 Find all the elements that appear twice in this array.
 Could you do it without extra space and in O(n) runtime?

 Example:

 Input:
 [4,3,2,7,8,2,3,1]

 Output:
 [2,3]

 * */
public class Solution {
    /**
        My idea was to use hash table in order to detect duplicates,
     but it requires O(N) time
     Then I thought to do something with sum of numbers from 1 to n
     sum = ((1+n)/2)  * n. Didn't get any idea
    * */


    // Not mine O(N) time O(1)space solution
    // change the sign of the number which is present in the index location of current number,
    // so if only one copy of current number is there, the index value should be negative( as we are chaning the sign only once), but if the value is toggled two times, the value will be positive and we will know that number is duplicate:
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/discuss/92387/Java-Simple-Solution
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int curr = Math.abs(nums[i]);
            if(nums[curr - 1] < 0)
                res.add(curr);
            else
                nums[curr - 1] = -nums[curr - 1];
        }
        return res;
    }


    // My interpretation of solution
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/discuss/92411/Java-O(1)-space-O(n)-time-solution-with-swapping
    // But here we change the array
    //Basic idea is to put each element to the corresponding position,
    // so that a[0] = 1, a[1] = 2, a[2] = 3 ... etc. (1<=a[i]<=n).
    public List<Integer> findDuplicates2222(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int curr = nums[i];

            if(curr == -1 || i == curr - 1)  {
                i++;
                continue;
            }

            if(curr == nums[curr - 1]) { // found duplicate
                nums[i] = -1;       //  mark it in order to not add the same number twice (we could use hashset for res)
                res.add(curr);
                i++;
            }
            else
                swap(nums, i, curr - 1);
        }

        return res;
    }

    public void swap(int nums[], int i ,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
