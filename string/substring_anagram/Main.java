public class Main {

    public static void main(String[] args) {
	    Solution solution = new Solution();
        boolean res1 = solution.isAnagramPresent("revepotr", "opt"); // true
        boolean res2 = solution.isAnagramPresent("otprever", "opt"); // true
        boolean res3 = solution.isAnagramPresent("opppreveot", "opt");  // false
        boolean res4 = solution.isAnagramPresent("opppreveotp", "opt");  // true
        boolean res5 = solution.isAnagramPresent("", ""); // false
        boolean res6 = solution.isAnagramPresent("ab", ""); // false
        boolean res7 = solution.isAnagramPresent("abc", "cba"); // true
    }
}
