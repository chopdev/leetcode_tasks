/*
1011. Capacity To Ship Packages Within D Days
https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
*/

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int maxWeight = 0;
        int sum = 0;
        for (int weight : weights) {
            maxWeight = Math.max(maxWeight, weight);
            sum += weight;
        } 

        int lo = maxWeight; // minimal capacity must be at least the heaviest package
        int hi = sum;       // maximal capacity could ship everything in one day

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (!canFit(mid, weights, days)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    boolean canFit(int capacity, int[] weights, int days) {
        int temp = capacity;
        int remainingDays = days - 1; // use one day immediately
        for (int weight : weights) {
            if (temp >= weight) {
                temp -= weight;
            } else {
                if (remainingDays == 0) return false;
                temp = capacity - weight;
                remainingDays--;
            }
        }

        return true;
    }
}
