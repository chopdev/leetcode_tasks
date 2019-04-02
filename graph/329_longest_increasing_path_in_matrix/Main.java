public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

	    int[][] arr = new int[3][];
	    arr[0] = new int[] {3, 4, 5};
        arr[1] = new int[] {3, 2, 6};
        arr[2] = new int[] {2, 2, 1};
	    int len = sol.longestIncreasingPath(arr);
    }
}
