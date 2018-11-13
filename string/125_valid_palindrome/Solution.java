/**
 https://leetcode.com/problems/valid-palindrome/description/
 125. Valid Palindrome

 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 Note: For the purpose of this problem, we define empty string as valid palindrome.

 Example 1:

 Input: "A man, a plan, a canal: Panama"
 Output: true

 Example 2:
 Input: "race a car"
 Output: false

 */
public class Solution {
    // My solution, beats 96%
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) return true;

        int start = 0, end = s.length() - 1;
        Character startCh, endCh;
        while (start < end) {
            startCh = s.charAt(start);
            endCh = s.charAt(end);

            if (startCh - '0' < 0 ||
                    (startCh - '9' > 0 && startCh - 'A' < 0) ||
                    (startCh - 'Z' > 0 && startCh - 'a' < 0) ||
                    startCh - 'z' > 0) {
                start ++;
                continue;
            }
            if (endCh - '0' < 0 ||
                    (endCh - '9' > 0 && endCh - 'A' < 0) ||
                    (endCh - 'Z' > 0 && endCh - 'a' < 0) ||
                    endCh - 'z' > 0) {
                end--;
                continue;
            }

            if(Character.toLowerCase(startCh) != Character.toLowerCase(endCh)) return false;
            start ++;
            end--;
        }
        return true;
    }

    // I didn't know about isLetterOrDigit method!
    // https://leetcode.com/problems/valid-palindrome/discuss/40029/Accepted-pretty-Java-solution(271ms)
    public boolean isPalindrome2222(String s) {
        if(s == null || s.length() == 0) return true;

        int start = 0, end = s.length() - 1;
        Character startCh, endCh;
        while (start < end) {
            startCh = s.charAt(start);
            endCh = s.charAt(end);

            if (!Character.isLetterOrDigit(startCh)) {
                start ++;
                continue;
            }
            if (!Character.isLetterOrDigit(endCh)) {
                end--;
                continue;
            }

            if(Character.toLowerCase(startCh) != Character.toLowerCase(endCh)) return false;
            start ++;
            end--;
        }
        return true;
    }
}
