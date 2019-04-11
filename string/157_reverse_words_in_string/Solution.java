import java.util.ArrayList;
import java.util.List;

/**
 151. Reverse Words in a String
 https://leetcode.com/problems/reverse-words-in-a-string/

 Given an input string, reverse the string word by word.


 Example 1:

 Input: "the sky is blue"
 Output: "blue is sky the"
 Example 2:

 Input: "  hello world!  "
 Output: "world! hello"
 Explanation: Your reversed string should not contain leading or trailing spaces.
 Example 3:

 Input: "a good   example"
 Output: "example good a"
 Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


 Note:

 A word is defined as a sequence of non-space characters.
 Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 You need to reduce multiple spaces between two words to a single space in the reversed string.


 Follow up:

 For C programmers, try to solve it in-place in O(1) extra space.
 * */
public class Solution {
    // Mine solution
    // using string builder
    // O(N) space, O(N) time
    public String reverseWords(String s) {
        List<String> words = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') continue;
            while (i < s.length() && s.charAt(i) != ' ') {
                builder.append(s.charAt(i));
                i++;
            }

            if(builder.length() > 0) {
                words.add(builder.toString());
                builder = new StringBuilder();
            }
        }

        builder = new StringBuilder();
        for (int i = words.size() - 1; i >= 0 ; i--) {
            builder.append(words.get(i));
            if(i > 0) builder.append(" ");
        }
        return builder.toString();
    }


    // Not mine in-place solution
    // O(1) space, O(N) time
    public String reverseWords2222(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }

    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);                      // reverse the word
        }
    }

    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i);
    }

    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }
}
