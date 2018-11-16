import java.util.Arrays;

/**
 https://leetcode.com/problems/plus-one/description/
 66. Plus one

 Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:

 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.


 Example 2:

 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.

 */
public class Solution {

    // My bad solution
    // it will not work with an overflow
    public int[] plusOne(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digits.length; i++)
            sb.append(digits[i]);

        String numbStr = sb.toString();
        int numb = Integer.parseInt(numbStr);  // it will not work with an overflow
        numb++;
        numbStr = Integer.toString(numb);
        int[] res = new int[numbStr.length()];
        for (int i = 0; i < numbStr.length(); i++) {
            res[i] = numbStr.charAt(i) - '0';
        }

        return res;
    }

    // My good solution
    // O(n) time, O(1) space
    public int[] plusOne2222(int[] digits) {
        int size = digits.length + 1;
        for (int i = 0; i < digits.length; i++)  // check if we would have one additional digit
            if(digits[i] != 9) {
                size = digits.length;
                break;
            }

        int[] res = new int[size];
        int rem = 1;
        for (int j = size - 1, i = digits.length - 1; j >= 0 && i>=0; j--, i--) {
            if(digits[i] + rem <= 9) {
                res[j] = digits[i] + rem;
                rem = 0;
            }
            else
                res[j] = 0;
        }

        if(size > digits.length) res[0] = rem;  // set 1 at the beginning if the number length was increased
        return res;
    }

    // Cool solution from leetcode
    // https://leetcode.com/problems/plus-one/discuss/24082/My-Simple-Java-Solution
    public int[] plusOne3333(int[] digits) {

        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }

}
