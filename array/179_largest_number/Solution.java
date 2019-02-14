import java.util.Arrays;
import java.util.Comparator;

/**
 179. Largest Number
 https://leetcode.com/problems/largest-number/

 Given a list of non negative integers, arrange them such that they form the largest number.

 Example 1:

 Input: [10,2]
 Output: "210"


 Example 2:

 Input: [3,30,34,5,9]
 Output: "9534330"
 Note: The result may be very large, so you need to return a string instead of an integer.

 * */
public class Solution {

    // My solution
    // Idea is pretty good, but fails for long test case in leetcode (there is some small mistake)
    // Time O(K*N*logN) - K - avarage string length, N - number of elements
    // Space O(logN + N*K) = O(N*K) - logN sorting, N*K - array with strings
    public String largestNumber(int[] nums) {
        if(nums == null || nums.length == 0) return "0";

        String[] strings = new String[nums.length];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = Integer.toString(nums[i]);
        }

        Arrays.sort(strings, (n1, n2) -> compare(n1, n2));

        StringBuilder sb = new StringBuilder();
        for(String str : strings) {
            if(sb.length() == 0 && str.equals("0")) continue;
            sb.append(str);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    private int compare(String n1, String n2) {
        int p1 = 0, p2 = 0;

        while (p1 < n1.length() && p2 < n2.length()) {
            if(n1.charAt(p1) < n2.charAt(p2)) return 1;
            else if(n1.charAt(p1) > n2.charAt(p2)) return -1;
            p1++; p2++;
        }

        if(n1.length() == n2.length()) return 1;
        if(p1 < n1.length() && n1.charAt(p1) <= n1.charAt(0)) return 1;
        if(p2 < n2.length() && n2.charAt(p2) > n2.charAt(0)) return 1;
        return -1;
    }


    // Not mine solution
    // Time O(K*N*logN) - K - avarage string length, N - number of elements
    // Space O(logN + N*K) = O(N*K) - logN sorting, N*K - array with strings
    // Idea is similar
    // BUT comparing function idea is great!
    public String largestNumber2222(int[] num) {
        if(num == null || num.length == 0)
            return "";

        // Convert int array to String array, so we can sort later on
        String[] s_num = new String[num.length];
        for(int i = 0; i < num.length; i++)
            s_num[i] = String.valueOf(num[i]);

        // Comparator to decide which string should come first in concatenation
        Comparator<String> comp = new Comparator<String>(){
            @Override
            public int compare(String str1, String str2){
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s2.compareTo(s1); // reverse order here, so we can do append() later
            }
        };

        Arrays.sort(s_num, comp);
        // An extreme edge case by lc, say you have only a bunch of 0 in your int array
        if(s_num[0].charAt(0) == '0')
            return "0";

        StringBuilder sb = new StringBuilder();
        for(String s: s_num)
            sb.append(s);

        return sb.toString();

    }
}
