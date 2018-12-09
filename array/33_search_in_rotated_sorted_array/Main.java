public class Main {

    public static void main(String[] args) {
	    Solution solution = new Solution();

        int res66=solution.search(new int[] {5, 1, 3}, 5);

	    int res1= solution.search(new int[] {0, 1, 2, 4, 5, 6, 7}, 2);
        int res2=solution.search(new int[] {2, 4, 5, 6, 7, 0, 1}, 2);
        int res3=solution.search(new int[] {7, 0, 1, 2, 4, 5, 6}, 2);
        int res4=solution.search(new int[] {1, 0}, 1);
        int res5=solution.search(new int[] {0, 1}, 1);
        int res6=solution.search(new int[] {1}, 1);
    }
}
