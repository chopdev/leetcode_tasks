import  java.lang.Math;

/*
416. Partition Equal Subset Sum
https://leetcode.com/problems/partition-equal-subset-sum/description/
Given a non-empty array containing only positive integers, find if the array
can be partitioned into two subsets such that the sum of elements in both subsets is equal.
Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/

// explanation of 0/1 Knapsack problem - https://www.youtube.com/watch?v=8LusJS5-AGo&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
//example of solution - https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation

public class Solutuion {

    // Mine bad solution Runtime: O(2^n) Space complexity: O(N) (because of callstack length,
    // at specific moment the length of it not bigger than N)
    public boolean canPartition2(int[] nums) {
        if(nums == null)
            return  false;

        return canPartitionInternal(nums, -1, 0, 0);
    }

    public boolean canPartitionInternal(int[] nums, int i, int sum1, int sum2) {
        i++;
        if(nums.length <= i)
            return sum1 - sum2 == 0;

        return canPartitionInternal(nums, i, sum1 + nums[i], sum2) ||
                canPartitionInternal(nums, i, sum1 , sum2 + nums[i]);
    }

    // Mine solution after watching of 0-1 knapsack problem explanation
    public boolean canPartition(int[] nums) {
        if(nums == null)
            return  false;

        int n = nums.length;
        int sum = 0;
        for (int i = 0; i <n; i++) {
            sum += nums[i];
        }

        if(sum % 2!= 0)
            return  false; // if sum can't be divided, we can't partition it on 2 subsets

        // look if we can get half of the sum from initial numbers (if can - other part of numbers is another half)
        sum = sum / 2;
        // use dynamic programming, 0-1 knapsack
        // rows are initial number, columns - sum values till the sum value that we need
        // add +1 to column and row to avoid index out of range exception, so zero row and column are 0 numbers
        int[][] knapsack = new int[n + 1][sum + 1];

        for (int i = 1; i < n + 1; i++) { // start from 1
            for (int j = 1; j < sum + 1; j++) { // j - current sum value
                int value = nums[i - 1];
                if(value > j) // we can't take this number, cause it is bigger than available sum
                              // (we can't put it in knapsack, cause its weight is bigger than available),
                    knapsack[i][j] = knapsack[i-1][j];
                else {
                    // if our value is less or equal then sum
                    // we should decide include this number or not
                    // if value + max(sum - value) of previous elements is bigger than just skipping, we take this number
                    knapsack[i][j] = Math.max(value + knapsack[i-1][j-value], knapsack[i-1][j]);
                }

                if(knapsack[i][j] == sum) // if there are numbers when knapsack is full
                    return true;
            }
        }

        return false;
    }
}
