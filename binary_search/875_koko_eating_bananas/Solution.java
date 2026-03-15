/*
875. Koko Eating Bananas
https://leetcode.com/problems/koko-eating-bananas/
*/

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for (int p : piles) {
            max = Math.max(max, p); // upper bound on speed
        }

        int min = 1; 

        while (min < max) {
            int mid = min + (max - min) / 2;
            long hoursToFinish = getHoursToFinish(mid, piles);
            if (hoursToFinish > h) {
                min = mid + 1;
            } else {
                max = mid; // shrink right side to find better candidate speed
            }
        }

        return min;
    }

    long getHoursToFinish(int speed, int[] piles) {
        long hours = 0; // use long to avoid overflow
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed; // ceil division
        }
        return hours;
    }
}
