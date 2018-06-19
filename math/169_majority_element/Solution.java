import java.util.HashMap;

/*
169. Majority Element
https://leetcode.com/problems/majority-element/
Given an array of size n, find the majority element. The majority element
is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element
always exist in the array.
*/

//https://leetcode.com/problems/majority-element/discuss/51611/java-solutions-sorting-hashmap-moore-voting-bit-manipulation
//https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm

class Solution {

    // Mine solution using Hashtable, time complexity O(N), Space complexity O(N)
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length == 0)  return 0;

        int val = nums.length /2;
        HashMap<Integer, Integer> numbToCount = new HashMap<>();

        for(int i = 0; i<nums.length; i++){
            int numb = nums[i];

            if(!numbToCount.containsKey(numb))  numbToCount.put(numb, 1);
            else numbToCount.put(numb, numbToCount.get(numb) + 1);

            if(numbToCount.get(numb) > val)
                return numb;
        }

        return 0;
    }


    // Not mine
    // Moore voting algorithm
    // Time complexity O(n), space complexity O(1).
    public int majorityElement2(int[] nums) {
        int count=0, ret = 0;
        for (int num: nums) {
            if (count==0)
                ret = num;
            if (num!=ret)
                count--;
            else
                count++;
        }
        return ret;
    }

    // Not mine
    // Bit manipulation
    // Time complexity O(N*C +C)= O(N), space complexity O(1).
    public int majorityElement3(int[] nums) {
        int[] bit = new int[32];
        for (int num: nums)
            for (int i=0; i<32; i++)
                if ((num>>(31-i) & 1) == 1)
                    bit[i]++;
        int ret=0;
        for (int i=0; i<32; i++) {
            bit[i]=bit[i]>nums.length/2?1:0;
            ret += bit[i]*(1<<(31-i));
        }
        return ret;
    }
}