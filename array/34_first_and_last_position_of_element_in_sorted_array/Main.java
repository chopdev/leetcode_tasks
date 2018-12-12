public class Main {

    public static void main(String[] args) {
	    Solution solution = new Solution();
	    int[] res1 = solution.searchRange(new int[] {1, 2, 8, 8, 8, 8, 8, 8, 8, 9, 10, 12}, 8); // [2, 8]
        int[] res2 = solution.searchRange(new int[] {8, 8, 8, 8, 8, 8, 8}, 8); // [0, 6]
        int[] res3 = solution.searchRange(new int[] {1, 8, 10}, 8);  // [1, 1]
        int[] res4 = solution.searchRange(new int[] {1, 9, 10}, 8);  // [-1, -1]
    }
}
