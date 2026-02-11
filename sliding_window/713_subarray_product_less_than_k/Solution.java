/*
713. Subarray Product Less Than K
https://leetcode.com/problems/subarray-product-less-than-k/
*/



/*

[1, 1] k =2 -> res=3 because 1 and 1 and 1, 1

[1, 2, 3, 1, 1, 1], k=2 ->  3  , because 1, 1 and 1, 1, 1 and 1, 1

[1, 2, 3, 1, 1, 2], k= 3

1
1, 2
1, 2, 3 -> 2, 3 -> 3
3
3, 1
3, 1, 1,
3, 1, 1, 2 -> 1, 1, 2
1, 1, 2


while window is correct
---
After shrinking, your window is [begin..end] and its product is < k. 
Since all numbers are positive, any shorter suffix of this window is also < k. 
So the valid subarrays that end at end are:

start at end (length 1)
start at end-1 (length 2)
…
start at begin (length end-begin+1)

That’s end - begin + 1 subarrays, and none of them were counted before because earlier steps counted subarrays ending at earlier indices.
----

 */


class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        
        int begin = 0, end = 0;
        int total = 0;
        int product = 1;

        for (; end < nums.length; end ++) {
            product *= nums[end];

            while (product >= k) {
                product /= nums[begin];
                begin ++;
            }

            total += end - begin + 1; // length of the window is the count of subarrays from current end to begin
        }
        return total;
    }
}