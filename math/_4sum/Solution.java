import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 18. 4Sum
 * https://leetcode.com/problems/4sum/
 *
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 *
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 *
 * Example 2:
 *
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * */
public class Solution {

    class CompositeKey {
        public List<Integer> values = new ArrayList<>();

        public CompositeKey(Integer... val) {
            values.addAll(Arrays.asList(val));
            values.sort(Integer::compare);
        }

        @Override
        public int hashCode() {
            int prime = 31;
            int sum = 0;
            for (Integer val : values) {
                sum += prime * val;
            }
            return sum;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof CompositeKey))
                return false;

            CompositeKey otherKey = (CompositeKey)obj;
            if (otherKey.values.size() != this.values.size())
                return false;
            for (int i = 0; i < values.size(); i ++) {
                if (!values.get(i).equals(otherKey.values.get(i)))
                    return false;
            }

            return true;
        }
    }

    /**
     * My solution, Time limit exceeded
     *
     * O(N^2) ??? - CompositeKey implementation in constant because it's always 2 or 4 items
     *
     *
     * Brute force solution is to have 4 loops O(N^4)
     * */
    public List<List<Integer>> fourSum2222(int[] nums, int target) {
        HashMap<Integer, HashSet<CompositeKey>> twoSums = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int finalI = i;
                int finalJ = j;
                twoSums.compute(nums[i] + nums[j], (sum, set) -> {
                    if (set != null) {
                        set.add(new CompositeKey(finalI, finalJ));
                    } else {
                        set = new HashSet<>();
                        set.add(new CompositeKey(finalI, finalJ));
                    }
                    return set;
                });
            }
        }

        HashSet<CompositeKey> res = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int diff = target - nums[i] - nums[j];
                if (twoSums.containsKey(diff)) {
                    for (CompositeKey otherPair : twoSums.get(diff)) {
                            int k = otherPair.values.get(0);
                            int h = otherPair.values.get(1);
                            if (k != i && h != i && k != j && h != j)
                                res.add(new CompositeKey(nums[i], nums[j], nums[k], nums[h]));
                    }
                }
            }
        }

        return res.stream().map(compositeKey -> {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(compositeKey.values);
            return temp;
        }).collect(Collectors.toList());
    }



    /**
     * Not my solution, but my implementation
     * Time: O(n ^ 3)
     * Space O(1)
     *
     * https://leetcode.com/problems/4sum/solutions/2682974/java-2-approaches-brute-and-optimal/
     * https://leetcode.com/problems/4sum/solutions/2689946/easy-java-solution/
     * */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums); // sort numbers, so same numbers are sequential to each other and bigger are on the right
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long subSum = (nums[i] + nums[j]);
                long diff = target - subSum; // a + b = target - c - d
                int low = j + 1;
                int high = nums.length - 1;
                while (low < high) {
                    if (nums[low] + nums[high] == diff) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        int a = nums[low];
                        int b = nums[high];
                        while (low < high && nums[low] == a) low ++;
                        while (low < high && nums[high] == b) high --;
                    } else if (nums[low] + nums[high] < diff) {
                        low ++;
                    } else if (nums[low] + nums[high] > diff) {
                        high --;
                    }
                }

                while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
                while (j < nums.length - 1 && nums[j] == nums[j + 1]) j++;
            }
        }
        return res;
    }


    public List<List<Integer>> fourSum1111(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 3; i++) {
            for(int j = i + 1; j < nums.length - 2; j++) {
                long res = (nums[i] + nums[j]);
                long remSum = target - res;
                int front = j + 1, back = nums.length - 1;
                while(front < back) {
                    long twoSum = nums[front] + nums[back];
                    if(twoSum < remSum) front++;
                    else if(twoSum > remSum) back--;
                    else {
                        List<Integer> sum = new ArrayList<>();
                        sum.add(nums[i]);
                        sum.add(nums[j]);
                        sum.add(nums[front]);
                        sum.add(nums[back]);
                        ans.add(sum);

                        while(front < back && nums[front] == sum.get(2)) front++;
                        while(front < back && nums[back] == sum.get(3)) back--;
                    }
                }
                while(i < nums.length - 1 && nums[i + 1] == nums[i]) i++;
                while(j < nums.length - 1 && nums[j + 1] == nums[j]) j++;
            }
        }
        return ans;
    }
}
