import java.util.IntSummaryStatistics;

//*
// String Compression: Implement a method to perform basic string compression using the counts
//of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
//"compressed" string would not become smaller than the original string, your method should return
//the original string. You can assume the string has only uppercase and lowercase letters (a - z).
// /
public class Solution {

    // Mine solution Time: O(N), Space complexity: O(N)
    public String shorthand(String str) {
        if(str == null || str.length() == 0) return str;

        StringBuilder res = new StringBuilder();
        char curr = Character.MIN_VALUE;
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            char charI = Character.toLowerCase(str.charAt(i));
            if(count == 0) {
                count ++;
                curr = charI;
                continue;
            }

            if(curr != charI){
                updateStringBuilder(res, count, curr);

                curr = charI;
                count = 1;
            }
            else
                count ++;
        }

        updateStringBuilder(res, count, curr);

        return res.toString();
    }

    private void  updateStringBuilder(StringBuilder res, int count, char curr){
        if(count == 1)
            res.append(curr);
        else {
            res.append(count);
            res.append(curr);
        }
    }


    // Not working solution
    public int compress(char[] chars) {
        if(chars == null || chars.length == 0) return 0;

        int counter = 1;
        char prev = chars[0];
        int nextIndex = 1;

        for (int i = 1; i < chars.length; i++) {
            if(prev == chars[i]){
                counter ++;
                chars[i] = Character.MIN_VALUE;
            }
            else {
                if(counter == 1) {
                    if(i != nextIndex)
                        chars[nextIndex] = prev;
                    nextIndex++;
                }
                else
                    nextIndex += insertNumber(chars, nextIndex, counter);


                counter = 1;
                prev = chars[i];
            }
        }

        if(nextIndex != chars.length) {
            chars[nextIndex] = prev;
            chars[chars.length - 1] = Character.MIN_VALUE;
            nextIndex ++;

            if(counter > 1)
                insertNumber(chars, nextIndex, counter);
        }

        return chars.length;
    }

    int insertNumber(char[] arr, int i, int number) {
        String s = Integer.toString(number);
        for (int j = 0; j < s.length(); j++) {
            arr[i + j] = s.charAt(j);
        }

        return s.length();
    }
}
