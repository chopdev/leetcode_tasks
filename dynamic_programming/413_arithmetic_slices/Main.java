public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
        int res1 = sol.numberOfArithmeticSlices(new int[] {1, 2, 3, 4});
        int res2 = sol.numberOfArithmeticSlices(new int[] {1, 3, 5, 7, 9});
        int res3 = sol.numberOfArithmeticSlices(new int[] {3, -1, -5, -9});
        int res4 = sol.numberOfArithmeticSlices(new int[] {1, 1, 2, 5, 7});


        int res0 = sol.numberOfArithmeticSlices(new int[] {1, 2, 3, 4, 5, 6});

        // 1, 2, 3    1, 2, 3,4   1, 2, 3, 4, 5  1,2,3,4,5,6
        // 2,3,4   2,3,4,5  2,3,4,5,6
        // 3,4,5   3,4,5,6
        // 4,5,6
    }
}
