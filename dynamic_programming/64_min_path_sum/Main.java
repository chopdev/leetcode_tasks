public class Main {

    public static void main(String[] args) {
	    Solution sol = new Solution();

	    int[][] grid = new int[3][];
	    grid[0] = new int[] {1, 3, 1};
	    grid[1] = new int[] {1, 5, 1};
	    grid[2] = new int[] {4, 2, 1};

	    int min1 = sol.minPathSum(grid);

	    grid = new int[1][];
	    grid[0] = new int[] {4};

        int min2 = sol.minPathSum(grid);
    }
}
