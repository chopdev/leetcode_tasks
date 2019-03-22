public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
        int count1 = sol.numDecodings4444("226"); // 3
        int count2 = sol.numDecodings4444("12"); // 2

        //failed test cases

        int count3 = sol.numDecodings4444("0");  // 0
        int count4 = sol.numDecodings4444("10"); // 1
        int count5 = sol.numDecodings4444("00"); // 0
        int count6 = sol.numDecodings4444("01"); // 0
        int count7 = sol.numDecodings4444("101"); // 1
        int count8 = sol.numDecodings4444("100"); // 1
    }
}
