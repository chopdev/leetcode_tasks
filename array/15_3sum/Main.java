import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

	    List<List<Integer>> res = sol.threeSum2222(new int[] {-3, -3, -3, -3, -3, -2, 2, 5, 6});

	    for(List<Integer> inner : res) {
	        System.out.println("");
	        for (int n : inner) {
	            System.out.print(n + " ");
            }
        }
    }
}
