import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1386. Cinema Seat Allocation
 *
 * https://leetcode.com/problems/cinema-seat-allocation/
 *
 *
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.
 * Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i] = [3,8]
 * means the seat located in row 3 and labelled with 8 is already reserved.
 * Return the maximum number of four-person groups you can assign on the cinema seats. A four-person group occupies four adjacent seats in one single row. Seats across an
 * aisle (such as [3,3] and [3,4]) are not considered to be adjacent, but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle
 * split a four-person group in the middle, which means to have two people on each side.
 *
 *
 * Example 1:
 *
 * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * Output: 4
 * Explanation: The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
 *
 *
 * Example 2:
 *
 * Input: n = 2, reservedSeats = [[2,1],[1,8],[2,6]]
 * Output: 2
 *
 *
 * Example 3:
 *
 * Input: n = 4, reservedSeats = [[4,3],[1,4],[4,6],[1,7]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^9
 * 1 <= reservedSeats.length <= min(10*n, 10^4)
 * reservedSeats[i].length == 2
 * 1 <= reservedSeats[i][0] <= n
 * 1 <= reservedSeats[i][1] <= 10
 * All reservedSeats[i] are distinct.
 *
 * */
public class Solution {
    /**
     * Considerations:
     * - We probably want to sort "reservedSeats" by row and then label, it will cost us O(R*logR) time, O(R) space.
     * - Another option is to transform int[][] to Map<row, Set<label>>() it costs O(R) space and time
     *
     * - On each row we can place max 2 groups (split on the left and split on the right side)
     * - Another option is four sits in the middle
     * */

    /**
     * My solution
     *
     * O(R + N) time, O(R) space
     * */
    public int maxNumberOfFamilies2222(int n, int[][] reservedSeats) {
        if (reservedSeats == null || reservedSeats.length == 0) return n * 2;

        Map<Integer, Set<Integer>> reservedMap = new HashMap<>();
        for (int[] rowLabel : reservedSeats) {
            reservedMap.putIfAbsent(rowLabel[0], new HashSet<>());
            reservedMap.get(rowLabel[0]).add(rowLabel[1]);
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (reservedMap.get(i) == null) {
                res += 2;
                continue;
            }
            boolean checkMiddle = true;
            if (canPutOnLeftSide(reservedMap.get(i))) {
                res ++;
                checkMiddle = false;
            }

            if (canPutOnRightSide(reservedMap.get(i))) {
                res ++;
                checkMiddle = false;
            }

            if (checkMiddle && canPutInMiddle(reservedMap.get(i))) {
                res ++;
            }
        }
        return res;
    }

    private boolean canPutOnLeftSide(Set<Integer> row) {
        return !row.contains(2) && !row.contains(3) && !row.contains(4) && !row.contains(5);
    }

    private boolean canPutOnRightSide(Set<Integer> row) {
        return !row.contains(6) && !row.contains(7) && !row.contains(8) && !row.contains(9);
    }

    private boolean canPutInMiddle(Set<Integer> row) {
        return !row.contains(4) && !row.contains(5) && !row.contains(6) && !row.contains(7);
    }



    /**
     * Considerations:
     * How to make it quicker? We can assume that by default we can place two groups on one row.
     * And then just check "reservedSeats" if place is located in needed location, then just remove 1 from total result.
     * We need to remember though what part Row(left side, right side, middle) we have removed, we can use int[0, 0, 0] with 1 as a flag. Or just bitwise
     * */


    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        if (reservedSeats == null || reservedSeats.length == 0) return n * 2;

        Map<Integer, Integer> rowToCache = new HashMap<>();
        for (int[] rowLabel : reservedSeats) {
            int row = rowLabel[0];
            int label =  rowLabel[1];
            if (label == 1 || label == 10) continue;

            Integer cached = rowToCache.get(row);
            if (cached == null) cached = 0;
            if (label == 2 || label == 3 || label == 4 || label == 5) cached |= 4; // we can't place group on the left side; set 100 in bitwise, OR because cache might have other bits set
            if (label == 4 || label == 5 || label == 6 || label == 7) cached |= 2; // like 010 bitwise
            if (label == 6 || label == 7 || label == 8 || label == 9) cached |= 1; // like 001 bitwise

            rowToCache.put(row, cached);
        }

        int total = n * 2;
        for (int cache : rowToCache.values()) {
            if ((cache & 7) == 7) total -= 2;
            else total --;
        }

        return total;
    }
}
