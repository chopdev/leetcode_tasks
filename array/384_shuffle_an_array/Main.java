public class Main {

    public static void main(String[] args) {
	    int[] arr = new int[] {1, 2, 3};

	    Solution2 sol = new Solution2(arr);
	    int[] res1 = sol.shuffle();
	    int[] res2 = sol.reset();
	    int[] res3 = sol.shuffle();
    }
}
