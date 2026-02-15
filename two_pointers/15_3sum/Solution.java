/**

[-4, -1, -1, -1, 0, 1, 1, 2]

 */

class Solution {
    // O(N^2) time and O(1) spacece apart from sorting which is O(logN)
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i ++) {
            if (i >= 1 && nums[i - 1] == nums[i]) {
                // skip duplicates
                continue;
            }

            int begin = i + 1;
            int end = nums.length - 1;
            int search = nums[i] * (-1);

            while (end > begin) {
                int twoSum = nums[begin] + nums[end];
                if (search == twoSum) {
                    res.add(Arrays.asList(new Integer[] {nums[i], nums[begin], nums[end]}));
                    int prev = nums[begin];
                    begin++;
                    while (prev == nums[begin] && begin < end) {
                        // skip duplicates
                        begin ++;
                    }
                } else if (search > twoSum) {
                    begin++;
                } else {
                    end--;
                }
            }
        }

        return res;
    }


    public List<List<Integer>> threeSum3333(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] > 0) break; // early exit

            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    int leftVal = nums[l], rightVal = nums[r];
                    while (l < r && nums[l] == leftVal) l++;
                    while (l < r && nums[r] == rightVal) r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

}