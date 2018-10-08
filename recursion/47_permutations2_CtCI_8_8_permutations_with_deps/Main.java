import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> res1 = s.getPermutatations2222("AAA");
        List<String> res2 = s.getPermutatations2222("AABA");
        List<String> res3 = s.getPermutatations2222("AABBC");

        for (String str : res1)
            System.out.print(str + "  ");
        System.out.println();

        for (String str : res2)
            System.out.print(str + "  ");
        System.out.println();

        for (String str : res3)
            System.out.print(str + "  ");
        System.out.println();

        Leetcode leetcode = new Leetcode();
        List<List<Integer>> resLeet = leetcode.permuteUnique(new int[]{1, 2, 3, 1});
    }
}
