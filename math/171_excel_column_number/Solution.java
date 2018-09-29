public class Solution {

    // Mine solution, time O(N) Space O(1)
    public int titleToNumber(String s) {
        if(s == null) return -1;

        int last = s.length() - 1;
        int res = s.charAt(last) - 'A' + 1;
        for (int index = last - 1, power = 1; index >= 0 ; index--, power ++) {
            res += (s.charAt(index) - 'A' + 1) * Math.pow(26, power);
        }

        return res;
    }
}
