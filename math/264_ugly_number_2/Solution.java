import java.util.PriorityQueue;

/**
 264. Ugly Number II
 https://leetcode.com/problems/ugly-number-ii/

 Write a program to find the n-th ugly number.
 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

 Example:

 Input: n = 10
 Output: 12
 Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 Note:

 1 is typically treated as an ugly number.
 n does not exceed 1690.


 EXPLANATION
 https://study.com/academy/lesson/what-is-a-prime-factor-lesson-for-kids.html
 factor - делитель. 32 = 4*8    4 and 8 are both factors
 prime - простое число  32 = 2* 16   2 is a prime factor of 32

 14 is not an ugly number because 14 = 2*7  2 and 7 are both prime factors

 */
public class Solution {
    /*
    Mine idea was to use sieve of eratosthenes
    in order to detect numbers that are divisible by other prime numbers
    like 7, 11 and so on
    * */


    // not mine O(NlogN) solution
    // https://leetcode.com/problems/ugly-number-ii/discuss/69372/Java-solution-using-PriorityQueue
    // we only put numbers that are divisible by one of 2, 3, or 5
    public int nthUglyNumber(int n) {
        if(n == 1) return 1;
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.add(1l);

        for (int i = 1; i < n; i++) {
            long tmp = heap.poll();
            // remove duplicates
            // for example 12 can be constructed like 2*6 3*4, so we will have two 12 in a heap
            while (!heap.isEmpty() && tmp == heap.peek()) tmp = heap.poll();

            heap.offer(tmp*2);
            heap.offer(tmp*3);
            heap.offer(tmp*5);
        }
        return heap.peek().intValue();
    }


    // https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution
    // similar to previous
    public int nthUglyNumber2222(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }
}
