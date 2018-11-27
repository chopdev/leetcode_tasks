public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    int[][] arr = new int[3][];
	    arr[0] = new int[] {2, 1, 1};
        arr[1] = new int[] {2, 3, 1};
        arr[2] = new int[] {3, 4, 1};
	    int res = sol.networkDelayTime2222(arr, 4, 2);


	    DejkstrasAlgorithm da = new DejkstrasAlgorithm();
	    int[][] arr1 = new int[5][];
        arr1[0] = new int[] {1, 2, 1};
        arr1[1] = new int[] {2, 1, 1};
        arr1[2] = new int[] {2, 3, 7};
        arr1[3] = new int[] {1, 3, 4};
        arr1[4] = new int[] {3, 4, 3};
	    da.getPath(arr1, 1);
    }
}
