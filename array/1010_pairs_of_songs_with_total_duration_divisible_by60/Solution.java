/*
1010. Pairs of Songs With Total Durations Divisible by 60
https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

You are given a list of songs where the ith song has a duration of time[i] seconds.
Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

Example 1:

Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60

Example 2:

Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 
Constraints:

1 <= time.length <= 6 * 104
1 <= time[i] <= 500

*/


/*
We can count the number of songs with (length % 60) equal to r, and store that in an array of size 60.

0, 30 rests need to have pairs 
others like 29 + 31 give 60 as sum 


[60 60 60 60]
1 2
1 3
1 4 
2 3
2 4
3 4  = 6

[60 60 60 60 60]
4 + 3 + 2 + 1

[20 40 40 40 20]
1 2
1 3
1 4
5 2
5 3
5 4
*/
class Solution {

    // My solution O(N) time, O(1) space
    public int numPairsDivisibleBy60(int[] time) {
        int[] rest = new int[60];
        for (int i =0; i<time.length; i++) {
            rest[time[i] % 60] ++;
        }
        int count = 0;
        for (int val = 0; val<= 30; val++) {
            if (rest[val] == 0)
                continue;
            
            int other = 60 - val;
            if (val == 0) other = 0;

            while (rest[val] > 0 && rest[other] > 0) {
                count += val == other ? rest[other] - 1 : rest[other];
                rest[val] --;
            }
        }

        return count;
    }


    // Not mine, very easy to understand
    // compare with prev reminders
    public int numPairsDivisibleBy60_2222(int[] time) {
        int [] remainder = new int[60];
        int count = 0;
        
        for (int i = 0; i < time.length; i++) {
            int mod = time[i] % 60;
            if (mod == 0) {
                count += remainder[0];
            }
            else {
                count += remainder[60 - time[i] % 60]; 
            }
            remainder[mod]++;
        }
        return count;
    }

    // https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/discuss/256738/JavaC%2B%2BPython-Two-Sum-with-K-60
    public int numPairsDivisibleBy60_3333(int[] time) {
        int c[]  = new int[60], res = 0;
        for (int t : time) {
            res += c[(60 - t % 60) % 60];
            c[t % 60] += 1;
        }
        return res;
    }
}