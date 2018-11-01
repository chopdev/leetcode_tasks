public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    int[][] arr = new int[4][3];
        arr[0] = new int[] {0,1,0};
        arr[1] = new int[] {0,0,1};
        arr[2] = new int[] {1,1,1};
        arr[3] = new int[] {0,0,0};
	    sol.gameOfLife(arr);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
