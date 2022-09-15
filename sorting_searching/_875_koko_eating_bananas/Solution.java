import java.util.Arrays;

/**
 *875. Koko Eating Bananas
 * https://leetcode.com/problems/koko-eating-bananas/
 *
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 *
 * Example 2:
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 *
 * Example 3:
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * */
public class Solution {
    /**
     * My solution
     *
     * We know that the minimal possible speed is   sum of banans / h;
     * After that the idea is to use kind of binary search, but reversed. We are increasing speed twice and check
     * if we can eat bananas with this speed. If we can, do binary search between min and max. Else increase speed twice;
     *
     * Good question to ask:
     * 1) can "h" be smaller than piles.length? so we cannot eat all bananas with any speed (in the description of the task piles.length <= h <= 10^9)
     * 2) what is piles[i] max value? will it fit into int if we
     *
     *  Space O(1)
     *  Time O(N*log(Max(P))) , where Max(P) is a max count of bananas in piles array. N - count of piles
     * */
    public int minEatingSpeed(int[] piles, int h) {
        long totalBananas = 0;
        for (int bananas : piles) totalBananas += bananas;

        int temp = totalBananas % h > 0 ? 1 : 0;
        long minSpeed = totalBananas / h + temp;

        if (canEat(piles, h, minSpeed)) return (int) minSpeed;

        long maxSpeed = Arrays.stream(piles).max().getAsInt();

        long k = maxSpeed; // we know that we can eat all with this speed
        // do binary search between min and max
        long mid = (minSpeed + maxSpeed) / 2;
        while (minSpeed <= maxSpeed) {
            boolean canEatAll = canEat(piles, h, mid);
            if (canEatAll) {
                k = mid;
                maxSpeed = mid - 1;
            } else {
                minSpeed = mid + 1;
            }
            mid = (minSpeed + maxSpeed) / 2;
        }
        return (int) k;
    }

    private boolean canEat(int[] piles, int h, long speed) {
        int remaining = h;
        for (int bananas : piles) {
            int temp = bananas % speed > 0 ? 1 : 0; // java rounds to lower int, so we need to take one more day to eat the rest of bananas
            remaining -= bananas / speed + temp;
            if (remaining < 0) return false;
        }
        return true;
    }


    // Not my solution
    // Binary search between [1, 10^9] or [1, max(piles)] to find the result.
    // Time complexity: O(NlogM)= O(Nlog(MaxP))
    // Space O(1)
    // https://leetcode.com/problems/koko-eating-bananas/discuss/152324/JavaC%2B%2BPython-Binary-Search
    //
    // l and r denote min and max speed, binary search to find appropriate speed, so total hours == to initial H hour
    public int minEatingSpeed22222(int[] piles, int H) {
        int l = 1, r = 1000000000;
        while (l < r) {
            int m = (l + r) / 2, total = 0;
            for (int p : piles)
                total += (p + m - 1) / m; //   (p + m - 1) / m equal to ceil(p / m) , basically rounds to bigger int
            if (total > H)
                l = m + 1;
            else
                r = m;
        }
        return l;
    }
}
