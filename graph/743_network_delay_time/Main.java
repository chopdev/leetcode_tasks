public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    int[][] arr = new int[3][];
	    arr[0] = new int[] {2, 1, 1};
        arr[1] = new int[] {2, 3, 1};
        arr[2] = new int[] {3, 4, 1};
	    int res = sol.networkDelayTime(arr, 4, 2);
    }
}
