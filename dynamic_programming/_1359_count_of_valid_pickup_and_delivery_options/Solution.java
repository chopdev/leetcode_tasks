/***
 * 1359. Count All Valid Pickup and Delivery Options
 * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/description/?envType=daily-question&envId=2023-09-10
 *
 *
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
     My partially working DP solution
     Fails on n=18 due to overflow
     */
    public int countOrders2222(int n) {
        if (n == 1) return 1;

        long res = 1;
        long countOfSets = 1;
        for (int i = 2; i<= n; i++) {
            res = getFactorialSum(i * 2 - 1) * countOfSets;
            countOfSets = res;
        }
        return (int)(res % (Math.pow(10, 9) + 7));
    }

    private long getFactorialSum(int k) {
        long sum = 0;
        for (int i = 1; i <= k; i ++) {
            sum += i;
        }
        return sum;
    }


    // https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/solutions/516968/java-c-python-easy-and-concise/?envType=daily-question&envId=2023-09-10
    public int countOrders(int n) {
        long res = 1, mod = (long)1e9 + 7;
        for (int i = 1; i <= n; ++i)
            res = res * (i * 2 - 1) * i % mod;
        return (int)res;
    }
}
