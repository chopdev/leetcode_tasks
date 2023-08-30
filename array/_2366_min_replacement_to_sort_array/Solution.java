public class Solution {


    /**
     * https://leetcode.com/problems/minimum-replacements-to-sort-the-array/solutions/2388098/c-java-google-interview-question-o-n/
     *
     * what is reason behind finding prev by prev=nums[i]/numbOfTimes ?
     *
     * suppose a number is 10 and we have to divide it into 3 times
     * then our aim is to make minimum numbOfTimes from these three as maximum as possible/

     * suppose array is 3 10 4
     * then our answer is 2
     * because we divide 10 into 3 pieces i.e, 3 3 4
     * but what if i divide it into 2 4 4, ?
     * The answer is 3 now
     * */
    public long minimumReplacement(int[] nums) {
        int n = nums.length, prev = nums[n - 1];
        long ans = 0;
        for (int i = n - 2; i >= 0; i--) {
            int numbOfTimes = nums[i] / prev;
            if (nums[i] % prev != 0) {
                numbOfTimes++;
                prev = nums[i] / numbOfTimes;
            }
            ans += numbOfTimes - 1;
        }
        return ans;
    }

    /**
     * My partioally working solution

     next < curr > prev
     [5 8 6] -> [1 2 2 2 6 6] or [1 4 4 4 6] or [2 3 3 5 6]
     [3 8 6] -> [2 3 3 5 6]
     [2 8 6] -> [2 2 6 6]
     [1 8 6] -> [1 1 1 6 6]
     [3 9 3] -> [3,3,3,3,3]

     next > curr > pres        - not clear what is the efficient pattern to split curr
     [10 8 6] -> [2 2 2 2 2 2 6 6] or [2 4 4 4 4 6]
     [100, 50, 10] -> [10* 10, 5 * 10, 10]

     next = curr > pres
     [8 8 6] -> [2 2 2 2 2 6 6] or [4 4 4 4 6] or [2 3 3 3 5 6]
     */
    public long minimumReplacement2222(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        int count = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] <= nums[i + 1]) continue; // they are in order, no need to split

            if (i - 1 < 0) count ++; // the last split is needed as there is no next items

            int next = nums[i - 1];
            int curr = nums[i];
            int prev = nums[i + 1];

            if (next < curr) { // how many times we need to split curr number
                while (curr > prev) {
                    curr -= next;
                    count ++;
                }
            } else {
                // not clear what is the efficient pattern to split curr
            }
        }

        return count;
    }
}
