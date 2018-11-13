/**
 https://leetcode.com/problems/count-primes/description/
 204. Count Primes

 Count the number of prime numbers less than a non-negative number, n.

 Example:

 Input: 10
 Output: 4
 Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

 */
public class Solution {
    // My solution, Time Limit Exceeded
    public int countPrimes(int n) {
        int[] nums = new int[n];
        for (int i = 2; i <= n / 2; i++) {
            for (int j = i + 1; j < n; j++) {
                if(nums[j] == 1) continue;
                if(j % i == 0) nums[j] = 1;
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if(nums[i] == 0) count ++;
        }
        return count;
    }

    // My interpretation of this solution
    // https://leetcode.com/problems/count-primes/discuss/57636/My-JAVA-Solution
    public int countPrimes2222(int n) {
        if(n <= 1) return 0;

        boolean[] flags = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if(flags[i]) continue; // find next prime number

            count++;
            for (int j = i + i; j < n; j+= i) // cross all numbers that are divided by this prime. Important: j+= i
                flags[j] = true;              // for 3: 6, 9, 12, 15 ...
        }
        return count;
    }

    // My interpretation of solution from cracking coding
    public int countPrimes3333(int n) {
        if(n <= 1) return 0;

        boolean[] flags = new boolean[n];
        int count = 0;
        int prime = 2;

        while (prime < n) {
            count++;

            for (int i = prime + prime; i < n; i+= prime)  // cross numbers that are divided by this prime number
                flags[i] = true;

            int next = prime + 1;   // find next prime number
            while (next < n && flags[next]) next++;
            prime = next;
        }
        return count;
    }
}
