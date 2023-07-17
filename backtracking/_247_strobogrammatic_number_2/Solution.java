import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/strobogrammatic-number-ii/?envType=study-plan-v2&envId=premium-algo-100
 * 247. Strobogrammatic Number II
 *
 *
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: ["11","69","88","96"]
 *
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: ["0","1","8"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 14
 * */
public class Solution {

    /**
     * n= 1
     * 0, 1, 8
     *
     * n = 2
     * 11,69,88,96,00
     *
     * n = 3
     * 00 -> 010, 080, 000 - incalid numbers
     * 11 -> 111, 181, 101
     * 69 -> 619, 689, 609
     * 88 -> 888, 818, 808
     * 96 -> 916, 986, 906
     *
     * n=4
     * 1 + 11 + 1
     * 1 + 69 + 1
     * 1 + 88 + 1
     * 1 + 96 + 1
     * 1 + 00 + 1
     *
     * 0 + 11 + 0
     * 0 + 69 + 0
     * 0 + 88 + 0
     * 0 + 96 + 0
     * 0 + 00 + 0
     *
     * 8 + 11 + 8
     * 8 + 69 + 8
     * 8 + 88 + 8
     * 8 + 96 + 8
     * 8 + 00 + 8
     *
     * 6 + 11 + 9
     * 6 + 69 + 9
     * 6 + 88 + 9
     * 6 + 96 + 9
     * 6 + 00 + 9
     *
     * 9 + 11 + 6
     * 9 + 69 + 6
     * 9 + 88 + 6
     * 9 + 96 + 6
     * 9 + 00 + 6
     *
     * So the idea is to add  K + findStrobogrammatic(n - 2) + M   where K and M is one of 1, 0, 8, 6, 9
     *
     *
     * O(5^(N/2) * N)
     * In each recursive level, we iterate over all prevStroboNums strings and append 5 pairs of characters to each one of them;
     * thus, we increase currStroboNums array size by a factor of 5 with each level. Plus O(N) for string concat
     *
     * \*/
    public List<String> findStrobogrammatic(int n) {
        if (n <= 0) return null;
        List<String> res = getNumbersRecursive(n);
        if (n == 1) return res;

        return res.stream().filter(numb -> numb.charAt(0) != '0') // remove numbers starting on "0"
                .collect(Collectors.toList());
    }

    private final String[][] pairs = {{"1", "1"}, {"0", "0"}, {"8", "8"}, {"6", "9"}, {"9", "6"}};

    private List<String> getNumbersRecursive(int n) {
        if (n == 1)
            return Arrays.asList("0", "1", "8");
        if (n == 2)
            return Arrays.asList("11","69","88","96", "00");

        List<String> numbers = getNumbersRecursive(n - 2);

        List<String> res = new ArrayList<>();

        for (String[] pair : pairs) {
            for (String numb : numbers) {
                res.add(pair[0] + numb + pair[1]);
            }
        }
        return res;
    }





    /**
     * A bit more clear implementation
     *
     * */
    public List<String> findStrobogrammatic222222(int n) {
        return helper(n, n);
    }

    List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<String>(Arrays.asList(""));
        if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));

        List<String> list = helper(n - 2, m);

        List<String> res = new ArrayList<String>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            if (n != m) res.add("0" + s + "0");

            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }

        return res;
    }
}
