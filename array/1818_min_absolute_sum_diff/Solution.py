# 1818. Minimum Absolute Sum Difference

# You are given two positive integer arrays nums1 and nums2, both of length n.
# The absolute sum difference of arrays nums1 and nums2 is defined as the sum of |nums1[i] - nums2[i]| for each 0 <= i < n (0-indexed).
# You can replace at most one element of nums1 with any other element in nums1 to minimize the absolute sum difference.
# Return the minimum absolute sum difference after replacing at most one element in the array nums1. Since the answer may be large, return it modulo 109 + 7.

# |x| is defined as:
# x if x >= 0, or
# -x if x < 0.
 
# Example 1:

# Input: nums1 = [1,7,5], nums2 = [2,3,5]
# Output: 3
# Explanation: There are two possible optimal solutions:
# - Replace the second element with the first: [1,7,5] => [1,1,5], or
# - Replace the second element with the third: [1,7,5] => [1,5,5].
# Both will yield an absolute sum difference of |1-2| + (|1-3| or |5-3|) + |5-5| = 3.
# Example 2:

# Input: nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
# Output: 0
# Explanation: nums1 is equal to nums2 so no replacement is needed. This will result in an 
# absolute sum difference of 0.
# Example 3:

# Input: nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
# Output: 20
# Explanation: Replace the first element with the second: [1,10,4,4,2,7] => [10,10,4,4,2,7].
# This yields an absolute sum difference of |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 
# Constraints:

# n == nums1.length
# n == nums2.length
# 1 <= n <= 10^5
# 1 <= nums1[i], nums2[i] <= 10^5

from typing import List
import bisect
import sys

# My O(N^2) time, O(1) space solution
class Solution:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        min_absolute = sys.maxsize
        for i in range(len(nums1)):
            sum_of_others = self.sum_rest(i, nums1, nums2)
            current_index_min = self.min_diff(i, nums1, nums2)
            min_absolute = min(min_absolute, sum_of_others + current_index_min)
        return min_absolute % (10 ** 9 + 7)

    def sum_rest(self, exclude: int, nums1: List[int], nums2: List[int]) -> int:
        sum = 0
        for i in range(len(nums1)):
            if i == exclude: continue
            sum += abs(nums1[i] - nums2[i])
        return sum

    def min_diff(self, current: int, nums1: List[int], nums2: List[int]) -> int:
        min_diff = sys.maxsize
        for i in range(len(nums1)):
            min_diff = min(min_diff, abs(nums1[i] - nums2[current]))
        return min_diff 

## Not my O(N*logN) time, O(N) space solution 
## https://leetcode.com/problems/minimum-absolute-sum-difference/discuss/1141362/JavaPython-3-Sort-and-binary-search-time-O(nlogn).
## https://leetcode.com/problems/minimum-absolute-sum-difference/discuss/1141382/Clean-Python-3-binary-search
class Solution2:
    def minAbsoluteSumDiff(self, nums1: List[int], nums2: List[int]) -> int:
        sum = 0
        min_sum = sys.maxsize
        sorted_nums1 = sorted(nums1)
        n = len(nums1)
        for i in range(n):
            sum += abs(nums1[i] - nums2[i])
        for i in range(n):
            closest_idx = bisect.bisect_left(sorted_nums1, nums2[i])
            diff = abs(nums1[i] -  nums2[i])
            if closest_idx < n:
                min_sum = min(min_sum, sum - diff + abs(sorted_nums1[closest_idx] - nums2[i]))
            if closest_idx > 0:
                min_sum = min(min_sum, sum - diff + abs(sorted_nums1[closest_idx - 1] - nums2[i]))
        return min_sum % (10 ** 9 + 7)


sol = Solution2()
nums1 = [1,7,5]
nums2 = [2,3,5]
print(sol.minAbsoluteSumDiff(nums1, nums2))
print(sol.minAbsoluteSumDiff(nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]))
print(sol.minAbsoluteSumDiff(nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]))
