import javax.print.DocFlavor;
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
}
