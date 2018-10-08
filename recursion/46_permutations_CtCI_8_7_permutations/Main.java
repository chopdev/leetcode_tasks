import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> res = s.getPermutations2222("ABC");

        for(String str : res) {
            System.out.println(str);
        }


        Leetcode leet = new Leetcode();
        List<List<Integer>> res2 = leet.permute(new int[] {1, 2, 3});

        List<List<Integer>> res3 = leet.permute2222(new int[] {1, 2, 3});
    }
}