import java.lang.Integer;



/*
7. Reverse Integer
https://leetcode.com/problems/reverse-integer/
Reverse digits of an integer.
Example1: x = 123, return 321
Example2: x = -123, return -321
click to show spoilers.
Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer,
then the reverse of 1000000003 overflows. How should you handle such cases?
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
Update (2014-11-10):
Test cases had been added to Snake_3 the overflow behavior.
*/


// int 32 MAX: -2,147,483,648 to +2,147,483,647

public class Solution {

    // mine solution
    public int reverse(int x) {
        int sign = x >= 0 ? 1 : -1;
        int numb = x * sign;
        int result = 0;

        int preOverflowNumber = Integer.MAX_VALUE / 10;
        int overflowLast = Integer.MAX_VALUE % 10;

        while (numb>0)
        {
            int temp = numb % 10;

            if(result > preOverflowNumber || (result == preOverflowNumber && temp > overflowLast)) // overflow case
                return 0;

            result *= 10;

            numb /= 10;
            result += temp;
        }

//        int overflowNumber = Integer.MAX_VALUE + 1;
//        if((result & overflowNumber) == overflowNumber)
//            return 0;

        return result * sign;
    }


    // Good solution
    // https://discuss.leetcode.com/topic/6104/my-accepted-15-lines-of-code-for-java
    public int reverse1(int x) {
        int res = 0;
        int sign = (x<0) ? -1 : 1;
        x=x*sign;
        while (x>0) {
            int rem = x%10;
            x=x/10;
            double max = (Integer.MAX_VALUE-rem)/10;
            if (res > max) return 0;
            res = res*10 + rem;
        }
        return res*sign;
    }
}