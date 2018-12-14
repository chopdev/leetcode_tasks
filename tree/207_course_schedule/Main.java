public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    int[][] arr = new int[3][];
	    arr[0] = new int[] {1, 0};
	    arr[1] = new int[] {0, 2};
	    arr[2] = new int[] {2, 1};

	    boolean res1 = sol.canFinish3333(3, arr); // false
	    arr[2] = new int[] {1, 2};
        boolean res2 = sol.canFinish3333(3, arr); // true
    }
}
