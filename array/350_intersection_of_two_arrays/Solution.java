import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 350. Intersection of Two Arrays II
 https://leetcode.com/problems/intersection-of-two-arrays-ii/

 Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2,2]
 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [4,9]
 Note:

 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.

 Follow up:

 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?


 ANSWERS to follow up:
 https://leetcode.com/problems/intersection-of-two-arrays-ii/discuss/82243/Solution-to-3rd-follow-up-question
 If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
 If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.
 */
public class Solution {

    // My solution
    // Time O(A*logA + B*logB)
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Number> res = new ArrayList<>();
        int first = 0, sec = 0;
        while (first < nums1.length && sec < nums2.length) {
            if(nums1[first] == nums2[sec]) {
                res.add(nums1[first]);
                first++;
                sec++;
            }
            else if(nums1[first] > nums2[sec])
                sec++;
            else
                first++;
        }
        return res.stream().mapToInt(Number::intValue).toArray();
    }

    // My solution
    // Time O(A + B), space O(min(A, B))
    public int[] intersect2222(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> lettersToCount = new HashMap<>();
        int[] small = nums1.length < nums2.length ? nums1 : nums2;
        int[] big = nums1.length >= nums2.length ? nums1 : nums2;

        for(int numb : small) {
            if(lettersToCount.containsKey(numb))
                lettersToCount.put(numb, lettersToCount.get(numb) + 1);
            else
                lettersToCount.put(numb, 1);
        }

        List<Number> res = new ArrayList<>();
        for (int numb : big) {
            if(lettersToCount.size() == 0) break;
            if(lettersToCount.containsKey(numb)) {
                res.add(numb);
                if(lettersToCount.get(numb) == 1) lettersToCount.remove(numb);
                else lettersToCount.put(numb, lettersToCount.get(numb) - 1);
            }
        }

        return res.stream().mapToInt(Number::intValue).toArray();
    }

    // My solution
    // Time O(A + B), space O(min(A, B))
    public int[] intersect3333(int[] nums1, int[] nums2) {
        int[] small = nums1.length < nums2.length ? nums1 : nums2;
        int[] big = nums1.length >= nums2.length ? nums1 : nums2;
        Arrays.sort(small);

        List<Number> res = new ArrayList<>();
        for (int numb : big) {
            if(Arrays.binarySearch(small, numb) >= 0) {
                res.add(numb);
            }
        }

        return res.stream().mapToInt(Number::intValue).toArray();
    }
}
