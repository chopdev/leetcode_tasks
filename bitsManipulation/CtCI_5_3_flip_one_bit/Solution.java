
/*
* Flip Bit to Win: You have an integer and you can flip exactly one bit from a 13 to a 1. Write code to
find the length of the longest sequence of ls you could create.

Input: 1775 (or: 1113111131111)
Output: 8
* */
public class Solution {

    // Mine O(N^2) solution, Space O(1)
    public int getLongestSequence(int n) {
        int max = 0;
        for (int i = 0; i < 32 ; i++) {
            int digit = (n >>> i) & 1;
            if(digit == 1)
                continue;
            int reset = 1 << i;
            int tempMax = getMaxCount(n | reset);
            max = max > tempMax ? max : tempMax;
        }
        return max;
    }

    private int getMaxCount(int n) {
        int maxCount = 0;
        int currCount = 0;
        for (int i = 31; i >= 0 ; i--) {
            int digit = (n >>> i) & 1;
            if(digit == 1)
                currCount++;
            else {
                maxCount = maxCount > currCount ? maxCount : currCount;
                currCount = 0;
            }
        }

        maxCount = maxCount > currCount ? maxCount : currCount;
        return maxCount;
    }
}
