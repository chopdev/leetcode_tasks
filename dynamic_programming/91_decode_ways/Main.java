public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
        int count1 = sol.numDecodings("226"); // 3
        int count2 = sol.numDecodings("12"); // 2

        //failed test cases
        // 10  - should return 1
        int count3 = sol.numDecodings("0");
        int count4 = sol.numDecodings("10");
        int count5 = sol.numDecodings("00");
        int count6 = sol.numDecodings("01");
    }
}
