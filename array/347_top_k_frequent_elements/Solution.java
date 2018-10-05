import java.util.*;

/**
 Given a non-empty array of integers, return the k most frequent elements.
 Example 1:
 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]

 Example 2:
 Input: nums = [1], k = 1
 Output: [1]

 Note:

 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class Solution {
    // Mine solution
    // Time complexity O(N*logN)
    // Space O(N)
    // TRADE-OFF: we could make general sorting O(N*logN) with O(1) space, but we will loose in time when array has many identical elements
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequences = new HashMap<>();
        PriorityQueue<Item> maxheap = new PriorityQueue<>();

        // O(N)
        for (int i = 0; i < nums.length; i++) {
            if(frequences.containsKey(nums[i]))
                frequences.put(nums[i], frequences.get(nums[i]) + 1);
            else
                frequences.put(nums[i], 1);
        }

        // O(N*logN) - in the worst case where elements are all distinct
        // small improvement will be to make return before if  frequences.size() == nums.length
        for (Map.Entry<Integer,Integer> item : frequences.entrySet()) {
            maxheap.add(new Item(item.getKey(), item.getValue()));
        }

        // O(K*logK)
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(maxheap.poll().numb);
        }

        return res;
    }
}
