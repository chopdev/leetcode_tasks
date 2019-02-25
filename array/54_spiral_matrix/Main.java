public class Main {

    public static void main(String[] args) {
	    int[][] arr = new int[5][];
	    arr[0] = new int[] {1, 2, 3, 4};
        arr[1] = new int[] {5, 6, 7, 8};
        arr[2] = new int[] {9, 10, 11, 12};
        arr[3] = new int[] {13, 14, 15, 16};
        arr[4] = new int[] {17, 18, 19, 20};

        Solution solution = new Solution();
        solution.spiralOrder(arr);
    }
}
