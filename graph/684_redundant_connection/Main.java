public class Main {

    public static void main(String[] args) {
	    int[][] arr = new int[6][];
	    arr[0] = new int[] {1, 2};
        arr[1] = new int[] {2, 3};
        arr[2] = new int[] {3, 5};
        arr[3] = new int[] {4, 5};
        arr[4] = new int[] {3, 4};
        arr[5] = new int[] {5, 6};

        Solution sol = new Solution();
        int[] res = sol.findRedundantConnection(arr);
    }
}
