import java.util.ArrayList;
import java.util.List;

/**
 6. Zigzag Conversion
 https://leetcode.com/problems/zigzag-conversion/

 The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"

 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string s, int numRows);


 Example 1:

 Input: s = "PAYPALISHIRING", numRows = 3
 Output: "PAHNAPLSIIGYIR"
 Example 2:

 Input: s = "PAYPALISHIRING", numRows = 4
 Output: "PINALSIGYAHRPI"
 Explanation:
 P     I    N
 A   L S  I G
 Y A   H R
 P     I
 Example 3:

 Input: s = "A", numRows = 1
 Output: "A"


 Constraints:

 1 <= s.length <= 1000
 s consists of English letters (lower-case and upper-case), ',' and '.'.
 1 <= numRows <= 1000
* */

public class Solution {
    // My solution
    // O(S) time and space
    public String convert(String s, int numRows) {
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        for (int i = 0; i<s.length();) {
            i= readCol(rows, s, i);
            i= readDiagonal(rows, s, i);
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <numRows ; i++) {
            res.append(rows.get(i));
        }
        return res.toString();
    }

    private int readCol(List<StringBuilder> rows, String s, int i) {
        for (int j = 0; j < rows.size() && i<s.length(); j++, i ++) {
            rows.get(j).append(s.charAt(i));
        }
        return i;
    }

    private int readDiagonal(List<StringBuilder> rows, String s, int i) {
        for (int j = rows.size() - 2; j >= 1 && i<s.length(); j--, i ++) {
            rows.get(j).append(s.charAt(i));
        }
        return i;
    }
}
