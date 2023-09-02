
/**
 * 186. Reverse Words in a String II
 *
 * Given a character array s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * Example 2:
 *
 * Input: s = ["a"]
 * Output: ["a"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is an English letter (uppercase or lowercase), digit, or space ' '.
 * There is at least one word in s.
 * s does not contain leading or trailing spaces.
 * All the words in s are guaranteed to be separated by a single space.
 *
 * */
public class Solution {

    /**
     * My solution
     *
     * reverse whole string, and then reverse word by word
     * */
    public void reverseWords(char[] arr) {
        reverseArray(arr, 0, arr.length - 1);

        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != ' ') continue;

            int end = i - 1;
            reverseArray(arr, start, end);
            start = i + 1;
        }

        reverseArray(arr, start, arr.length - 1); // reverse the last word
    }

    private void reverseArray(char[] arr, int i, int j) {
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i ++;
            j --;
        }
    }
}

