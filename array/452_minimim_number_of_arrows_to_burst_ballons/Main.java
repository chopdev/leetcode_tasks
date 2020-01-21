public class Main {

    public static void main(String[] args) {
	    int[][] points = {{1, 2}, {3, 10}, {4, 5}, {5, 6}, {7, 12}, {7, 13}};
	    Solution sol = new Solution();
	    int count1 = sol.findMinArrowShots(points);


		int[][] points2 = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
		int count2 = sol.findMinArrowShots(points2);
	    System.out.println(count1);
	    System.out.println(count2);
    }
}
