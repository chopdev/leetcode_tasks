
/*
* Flip Bit to Win: You have an integer and you can flip exactly one bit from a 13 to a 1. Write code to
find the length of the longest sequence of ls you could create.

Input: 1775 (or: 1113111131111)
Output: 8
* */
public class Solution {
    public int getLongestSequence(int n) {
        int maxCount = 0, prevCount = 0, currCount = 0, zeroCount = 0;

        for (int i = 31; i >= 0 ; i++) {
            int digit = (n >> i) & 1;
            if(digit == 1) {
                currCount ++;
                if(zeroCount > 1) {
                    prevCount = 0;
                    zeroCount = 0;
                }
                else if(zeroCount == 1 && prevCount == 0)
                    zeroCount = 0;
            }
            else {
                zeroCount ++;
                if(zeroCount > 1) continue;
                if(prevCount > 0 && zeroCount == 1)
                    maxCount = maxCount > currCount + 1 + prevCount ? maxCount : currCount + 1 + prevCount;
                else
                    maxCount = maxCount > currCount + 1 ? maxCount : currCount + 1;
                prevCount = currCount;
            }
        }

        return maxCount;
    }
}
