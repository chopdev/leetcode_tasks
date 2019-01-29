public class Main {

    public static void main(String[] args) {
	    Solution solution = new Solution();
	    int res = solution.maxProfit(new int[] {7,1,5,3,6,4});
	    int res1 = solution.maxProfit(new int[] {7,6,4,3,1});
        int res2 = solution.maxProfit(new int[] {7});
        int res3 = solution.maxProfit(new int[0]);

        // leetcode test cases (failed on them from the first try)
        int res4 = solution.maxProfit(new int[] {1,2});
    }
}
