public class Main {

    public static void main(String[] args) {
	    int[][] arr = new int[5][];

        arr[0] = new int[] {0,1};
        arr[1] = new int[] {2,1};
        arr[2] = new int[] {1,3};
        arr[3] = new int[] {3,4};
        arr[4] = new int[] {3,5};

        Solution sol = new Solution();
        int[] res = sol.findOrder(6, arr);


        int[] res2 = sol.findOrder(2, new int[0][]);
    }
}
