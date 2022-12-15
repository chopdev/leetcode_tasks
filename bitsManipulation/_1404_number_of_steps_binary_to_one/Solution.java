/**
 * 1404. Number of Steps to Reduce a Number in Binary Representation to One
 * Given the binary representation of an integer as a string s, return the number of steps to reduce it to 1 under the following rules:
 * If the current number is even, you have to divide it by 2.
 * If the current number is odd, you have to add 1 to it.
 * It is guaranteed that you can always reach one for all test cases.
 *
 * Example 1:
 *
 * Input: s = "1101"
 * Output: 6
 * Explanation: "1101" corressponds to number 13 in their decimal representation.
 * Step 1) 13 is odd, add 1 and obtain 14.
 * Step 2) 14 is even, divide by 2 and obtain 7.
 * Step 3) 7 is odd, add 1 and obtain 8.
 * Step 4) 8 is even, divide by 2 and obtain 4.
 * Step 5) 4 is even, divide by 2 and obtain 2.
 * Step 6) 2 is even, divide by 2 and obtain 1.
 *
 *
 * Example 2:
 *
 * Input: s = "10"
 * Output: 1
 * Explanation: "10" corressponds to number 2 in their decimal representation.
 * Step 1) 2 is even, divide by 2 and obtain 1.
 *
 *
 * Example 3:
 *
 * Input: s = "1"
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of characters '0' or '1'
 * s[0] == '1'
 * */
public class Solution {

    /**
     * Considerations:
     *  - long has 64 bits, so we cannot just convert the string to number
     *  - last bit helps us to detect the number is even or odd
     *  - divide is easy, just shifts bits one to the right (basically remove last bit)
     *  - adding 1 is more complex. WE need to change all 1th on 0 and append one
     *    e.g.    11 - > 100  ; 1100011 -> 1100100
     *
     * */

    /**
    * My solution
     * time amortized O(N). Why amortized? because line 76 has linear complexity
    * */
    public int numSteps(String s) {
        if (s == null || s.isEmpty() || s.equals("1")) return 0;

        StringBuilder sb = new StringBuilder(s);
        int count = 0;
        while (sb.length() != 1) {
            count ++;
            if (sb.charAt(sb.length() - 1) == '0') { // even
                sb.replace(sb.length() - 1, sb.length(), ""); // shift one bit right
            } else { // odd
                int i = sb.length() - 1;
                for (; i >= 0 ; i --) {
                    if (sb.charAt(i) == '1') {
                        sb.replace(i, i + 1, "0");
                    } else {
                        sb.replace(i, i + 1, "1");
                        break;
                    }
                }
                if (i == -1) sb.insert(0, '1');
            }
        }

        return count;
    }


}
