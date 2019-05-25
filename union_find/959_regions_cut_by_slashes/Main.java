public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
        int res1 = sol.regionsBySlashes(new String[] {" /", "/ "});  // 2
        int res2 = sol.regionsBySlashes(new String[] {" /", "  "}); // 1
        int res3 = sol.regionsBySlashes(new String[] { "/\\", "\\/"}); // 5
    }
}
