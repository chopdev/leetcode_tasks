import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();

        List<List<Integer>> res1 = sol.combinationSum2222(new int[] {1, 2, 3}, 3);  // 2


        List<List<Integer>> res = sol.combinationSum2222(new int[] {10,1,2,7,6,1,5}, 8);  // 4
        List<List<Integer>> res2 = sol.combinationSum2222(new int[] {2,5,2,1,2}, 5);     // 2
    }
}
