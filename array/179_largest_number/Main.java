public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

	    String res1 = sol.largestNumber(new int[] {30, 305}); // 30530
	    String res2 = sol.largestNumber(new int[] {3,30,34,5,9}); // 9534330

		// edge cases on which I failed
		String res3 = sol.largestNumber(new int[] {12,121}); // 12,121
		String res4 = sol.largestNumber(new int[] {82,828});  // 828,82
		String res5 = sol.largestNumber(new int[] {0, 0});
    }
}
