public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();
	    int[][] matrix = {
	            {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
        };
	    int res1 = sol.countSquares(matrix);

	    System.out.println(res1);
    }
}
