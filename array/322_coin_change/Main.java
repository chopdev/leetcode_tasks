public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

        int res1 = sol.coinChange(new int[] {4, 5}, 16); // 4
        int res2 = sol.coinChange(new int[] {4, 5}, 14); // 3
        int res3 = sol.coinChange(new int[] {4, 5}, 13); // -1


        // failed test cases
        int res4 = sol.coinChange(new int[] {1}, 0);
        int res5 = sol.coinChange(new int[] { 186,419,83,408}, 6249);
    }
}
