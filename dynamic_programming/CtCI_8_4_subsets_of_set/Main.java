import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();

        List<List<Integer>> res = s.subsets(new int[] {1, 2, 3});

        //List<List<Integer>> res = s.getAllSubsets(Arrays.asList(1, 2, 3, 4));
        List<List<Integer>> res2 = s.getAllSubsets(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<List<Integer>> res3 = s.getAllSubsets(Arrays.asList(1));
        System.out.println(res);
        System.out.println(res2);
        System.out.println(res3);
    }
}
