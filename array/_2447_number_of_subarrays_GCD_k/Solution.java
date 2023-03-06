/**
 * 2447. Number of Subarrays With GCD Equal to K
 *
 * Given an integer array nums and an integer k, return the number of subarrays of nums where the greatest common divisor of the subarray's elements is k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * The greatest common divisor of an array is the largest integer that evenly divides all the array elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [9,3,1,2,6,3], k = 3
 * Output: 4
 * Explanation: The subarrays of nums where 3 is the greatest common divisor of all the subarray's elements are:
 * - [9,(3),1,2,6,3]
 * - [9,3,1,2,6,(3)]
 * - [(9,3),1,2,6,3]
 * - [9,3,1,2,(6,3)]
 *
 *
 * Example 2:
 *
 * Input: nums = [4], k = 7
 * Output: 0
 * Explanation: There are no subarrays of nums where 7 is the greatest common divisor of all the subarray's elements.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i], k <= 10^9
 * */
public class Solution {

    /**
     * 18, 9, 6 - GCD is 6
     * 18, 9, 6, 3 - GCD is 3
     *
     * [18,9,9,3,1,2,6,3]
     * [3,18,9,9,3,1,2,6,3]
     *
     * Not my
     * https://leetcode.com/problems/number-of-subarrays-with-gcd-equal-to-k/solutions/2734442/brute-force-vs-count-gcds/?orderBy=most_votes
     *
     * 1. if curr == k, it's subarray
     * 2. If curr and j element has k as GCD, than all other elements between that are divisible by k also has k as GCD
     *
     * O(N^2) solution
     * */
    public int subarrayGCD(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            int curr = nums[i];
            for (int j = i; j < nums.length && nums[j] % k == 0; ++j) {
                curr = gcd(curr, nums[j]);
                res += curr == k ? 1 : 0;
            }
        }
        return res;
    }

    /*
    * The Euclidean Algorithm for finding GCD(A,B) is as follows:
    If A = 0 then GCD(A,B)=B, since the GCD(0,B)=B, and we can stop.
    If B = 0 then GCD(A,B)=A, since the GCD(A,0)=A, and we can stop.
    Write A in quotient remainder form (A = B⋅Q + R)
    Find GCD(B,R) using the Euclidean Algorithm since GCD(A,B) = GCD(B,R)
    * */
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }



    // O(n2log(n))
    // https://leetcode.com/problems/number-of-subarrays-with-gcd-equal-to-k/solutions/2734313/java-100-fastest-o-n2log-n-tc-o-1-sc-easy-brute-force/?orderBy=most_votes
}
