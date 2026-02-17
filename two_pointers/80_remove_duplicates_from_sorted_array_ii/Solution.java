/*
80. Remove Duplicates from Sorted Array II
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
*/

/**

[0,0,0,1,1,1,1,2,3,3,4,4,4,4]
[0,0,-1,1,1,-1,-1,2,3,3,4,4,-1,-1]
[0,0,1,1,2,3,3,4,4]

 */
class Solution {

    /*
    nums[0 .. w-1] is the cleaned array so far (valid output prefix)
    nums[w .. n-1] is “don’t care” / leftover

    */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int w = 2;
        for (int i = 2; i < nums.length; i ++) {
            // if (nums[i] == nums[w - 2])  then we discard that item as duplicate, so we move i forward
            if (nums[i] != nums[w - 2]) {
                nums[w] = nums[i];
                w ++;
            }
        }

        return w;
    }

    public int removeDuplicates2222(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int curr = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] == curr) {
                count ++;
            } else {
                count = 1;
                curr = nums[i];
            }

            if (count > 2) {
                nums[i] = Integer.MIN_VALUE;
            }
        }

        int r = 0;
        int l = 0;
        for (; l < nums.length; l++) {
            if (nums[l] == Integer.MIN_VALUE) {
                if (r <= l) r = l + 1;
                while (r < nums.length && nums[r] == Integer.MIN_VALUE) {
                    r++;
                }

                if (r < nums.length) {
                    nums[l] = nums[r];
                    nums[r] = Integer.MIN_VALUE;
                } else {
                    break;
                }

            }
        }

        System.out.println("R=" + r);
        System.out.println("L=" + l);

        return l;
    }
}
