public class Main {

    public static void main(String[] args) {
	    int[][] matrix = new int[5][];
	    int[] temp1 = new int[] {1,   4,  7, 11, 15};
        int[] temp2 = new int[] {2,   5,  8, 12, 19};
        int[] temp3 = new int[] {3,   6,  9, 16, 22};
        int[] temp4 = new int[] {10, 13, 14, 17, 24};
        int[] temp5 = new int[] {18, 21, 23, 26, 30};
        matrix[0] = temp1;
        matrix[1] = temp2;
        matrix[2] = temp3;
        matrix[3] = temp4;
        matrix[4] = temp5;

        Solution sol = new Solution();
        boolean res1 = sol.searchMatrix3333(matrix, 5);
        boolean res2 = sol.searchMatrix3333(matrix, 20);
    }
}
