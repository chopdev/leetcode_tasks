public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    int res1 = sol.maxProfit2222(new int[] {7,1,5,3,6,4});  // 7
	    int res2 = sol.maxProfit2222(new int[] {1,2,3,4,5});  // 4
        int res3 = sol.maxProfit2222(new int[] {7,6,4,3,1}); // 0

        // my test cases
        int res4 = sol.maxProfit3333(new int[] {2, 5, 100, 99, 180, 10});  // 179
        int res5 = sol.maxProfit2222(new int[] {1, 5, 6, 3, 8});     // 10
        int res6 = sol.maxProfit2222(new int[] {1, 2}); // 1
        int res7 = sol.maxProfit2222(new int[] {2, 1}); //0

        // leetcode tests
        int res8 = sol.maxProfit(new int[] {2,1,2,0,1});
    }
}
