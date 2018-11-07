public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
        int[] res0 = new int[] {2, 0, 1};
	    int[] res1 = new int[] {2, 0, 2, 1, 1, 0};
        int[] res2 = new int[] {0, 0, 2, 0, 2, 2};
        int[] res3 = new int[] {1, 1, 0, 2, 0, 1, 1, 2, 2, 0};
        sol.sortColors(res0);
        sol.sortColors(res1);
        sol.sortColors(res2);
        sol.sortColors(res3);
    }
}
