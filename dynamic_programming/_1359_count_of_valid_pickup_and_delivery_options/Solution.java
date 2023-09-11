/***
 * 1359. Count All Valid Pickup and Delivery Options
 * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/description/?envType=daily-question&envId=2023-09-10
 *
 * Given n orders, each order consist in pickup and delivery services.
 * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
 *
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: 6
 * Explanation: All possible orders:
 * (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
 * This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
 *
 *
 * Example 3:
 *
 * Input: n = 3
 * Output: 90
 *
 *
 * Constraints:
 *
 * 1 <= n <= 500
 * */
public class Solution {

    // [P1,P2,D1,D2] -> [P3,D3,P1,P2,D1,D2] [P3,P1,D3,P2,D1,D2] [P3,P1,P2,D3,D1,D2] [P3,P1,P2,D1,D3,D2] [P3, P1,P2,D1,D2,D3]
    //                  [P1,P3,D3,P2,D1,D2] [P1,P3,P2,D3,D1,D2] [P1,P3,P2,D1,D3,D2] [P1,P3,P2,D1,D2,D3]
    //                  [P1,P2,P3,D3,D1,D2] [P1,P2,P3,D1,D3,D2] [P1,P2,P3,D1,D2,D3]
    //                  [P1,P2,D1,P3,D3,D2] [P1,P2,D1,P3,D2,D3]
    //                  [P1,P2,D1,D2,P3,D3]



    // [P4,D4,P3,D3,P1,P2,D1,D2] [P4,P3,D4,D3,P1,P2,D1,D2] [P4,P3,D3,D4,P1,P2,D1,D2] [P4,P3,D3,P1,D4,P2,D1,D2] [P4,P3,D3,P1,P2,D4,D1,D2] [P4,P3,D3,P1,P2,D1,D4,D2] [P4,P3,D3,P1,P2,D1,D2,D4]

    // set_length = n * 2;
    // count_of_sets = f(N - 1)
    // f(N) = FACTORIAL_SUM(n* 2 - 1) * count_of_sets

    // f(1) = 1 * 1 = 1
    // f(2) = (3 + 2 + 1) * 1= 6
    // f(3) = (5 + 4 + 3 + 2 + 1) * 6 = 90


    /**\
     My DP solution
     */
    public int countOrders2222(int n) {
        if (n == 1) return 1;

        long mod = (long)1e9 + 7;
        long res = 1;
        long countOfSets = 1;
        for (int i = 2; i<= n; i++) {
            res = (getFactorialSum(i * 2 - 1) * countOfSets) % mod;
            countOfSets = res;
        }
        return (int)(res % (Math.pow(10, 9) + 7));
    }


    /**
     * this function can be changed to "sum of arithmetic progression" formula
     * ((a1 + an) / 2 ) * n      - (first element + last element / 2) * n
     * */
    private long getFactorialSum(int k) {
        long sum = 0;
        for (int i = 1; i <= k; i ++) {
            sum += i;
        }
        return sum;
    }



    /**\
     My simplified version of above solution
     */
    public int countOrders4444(int n) {
        if (n == 1) return 1;

        long mod = (long)1e9 + 7;
        long res = 1;
        long countOfSets = 1;
        for (int i = 2; i<= n; i++) {
            res = (getSumOfArithmeticProgression(i * 2 - 1) * countOfSets) % mod;
            countOfSets = res;
        }
        return (int)(res % (Math.pow(10, 9) + 7));
    }

    // ((a1 + an) / 2 ) * n      - (first element + last element / 2) * n
    private long getSumOfArithmeticProgression(long k) {
        return ((1 + k) / 2) * k;
    }


    //

    /**
     * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/solutions/516968/java-c-python-easy-and-concise/?envType=daily-question&envId=2023-09-10
     *
     * Assume we have already n - 1 PAIRS, now we need to insert the nth PAIR.
     * To insert the first element, there are n * 2 - 1 chioces of position。
     * To insert the second element, there are n * 2 chioces of position。
     * So there are (n * 2 - 1) * n * 2 permutations.
     * Considering that delivery(i) is always after of pickup(i), we need to divide 2.
     * So it's (n * 2 - 1) * n.
    *
    * **/
    public int countOrders(int n) {
        long res = 1, mod = (long)1e9 + 7;
        for (int i = 1; i <= n; ++i)
            res = res * (i * 2 - 1) * i % mod;
        return (int)res;
    }
}
