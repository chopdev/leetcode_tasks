public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
        int res0 = sol.change(3, new int[] {1, 2});

        int res1 = sol.change2222(5, new int[] {1, 2, 5}); // 4
        int res2 = sol.change2222(5, new int[] {4}); // 0
        int res3 = sol.change2222(4, new int[] {4}); // 1
    }
}
