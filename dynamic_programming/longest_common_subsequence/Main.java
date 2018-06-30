public class Main {

    public static void main(String[] args) {
	    Solution s = new Solution();
        int res = s.LCS("ABC", "ACD");
        res = s.LCS("ABCDGH", "AEDFHR");
	    res = s.LCS("AGGTAB", "GXTXAYB");
        res = s.LCS("", "GXTXAYB");
        res = s.LCS(null, "GXTXAYB");

    }
}
