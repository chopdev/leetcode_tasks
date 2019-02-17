public class Solution {

    // My solution
    // but not works for "-00007" = -7
    public int myAtoi2222(String str) {
        int begin = 0, end = 0, sign = 1;
        for(begin = 0; begin <str.length(); begin++) {
            if(str.charAt(begin) == '-' || str.charAt(begin) == '+') break;
            if(str.charAt(begin) >= '1' && str.charAt(begin) <= '9') break;
            if(str.charAt(begin) != ' ' && str.charAt(begin) != '0') return 0;
        }

        for(end = begin + 1; end < str.length(); end++) {
            if(!(str.charAt(end) >= '0' && str.charAt(end) <= '9')) break;
        }

        if(begin == str.length()) return 0;
        if(str.charAt(begin) == '-' || str.charAt(begin) == '+') {
            if(str.charAt(begin) == '-') sign = -1;
            begin++;
        }
        String numb = str.substring(begin, end);

        if(numb.length() == 0) return 0;
        String maxVal = Integer.toString(Integer.MAX_VALUE);
        if(maxVal.length() < numb.length() || maxVal.length() == numb.length() && maxVal.compareTo(numb) < 0)
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        return Integer.parseInt(numb) * sign;
    }

    // https://leetcode.com/problems/string-to-integer-atoi/submissions/
    int myAtoi(String str) {
        int sign = 1, base = 0, i = 0;
        while (i < str.length() && str.charAt(i) == ' ') { i++; }
        if(i == str.length()) return 0;
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            sign = str.charAt(i++) == '-' ? -1 : 1;
        }
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            if (base >  Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) - '0' > 7)) {
                if (sign == 1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }
            base  = 10 * base + (str.charAt(i++) - '0');
        }
        return base * sign;
    }
}
