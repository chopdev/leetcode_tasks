package chopdev.combination_sum_39;
import java.lang.*;
import java.util.List;

public class Main {

    public static final void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> res = s.combinationSum(new int[] {1, 2, 3, 4, 5, 9, 11}, 5);
        List<List<Integer>> res1 = s.combinationSum(new int[] {2,3,5}, 8);
        
        
        System.out.println(res);
        System.out.println(res1);        
    }
}
