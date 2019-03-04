public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();
        int res = s.LCS3333("ABC", "ACD"); // 2
        res = s.LCS3333("ABCDGH", "AEDFHR");  // 3
	    res = s.LCS3333("AGGTAB", "GXTXAYB");  // 4
        res = s.LCS3333("", "GXTXAYB");         // 0
        res = s.LCS3333(null, "GXTXAYB");       // 0

    }
}
